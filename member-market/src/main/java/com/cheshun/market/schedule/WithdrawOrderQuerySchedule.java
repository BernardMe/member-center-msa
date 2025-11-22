package com.cheshun.market.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentWithdrawOrder;
import com.cheshun.market.domain.entity.enums.WithdrawStatus;
import com.cheshun.market.service.YunZhangHuService;
import com.cheshun.market.config.ScheduleCronConfig;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.domain.dao.AgentWithdrawOrderDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务:查询提现订单信息
 *
 * @author 阿隆
 * Created on 2021/8/3 5:31 下午.
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@AllArgsConstructor
public class WithdrawOrderQuerySchedule implements Runnable, SchedulingConfigurer {
    private final RedissonClient redissonClient;
    private final YunZhangHuService yunZhangHuService;
    private final AgentDao agentDao;
    private final AgentWithdrawOrderDao agentWithdrawOrderDao;
    private final ScheduleCronConfig scheduleCronConfig;
    private final ThreadPoolTaskScheduler withdrawOrderQueryExecutor;
    private static final String LOCK_NAME = "WITHDRAW_ORDER_QUERY";
    private static final String AGENT_LOCK_NAME = "WITHDRAW_LOCK_%d";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(withdrawOrderQueryExecutor);
        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getWithdrawOrderQuery()).nextExecutionTime(triggerContext));
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString().replace("-", "");
        log.debug("定时任务:查询提现订单信息:" + id);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME);
        try {
            // 加锁
            rLock.lock(scheduleCronConfig.getWithdrawOrderQueryLockTime().getSeconds(), TimeUnit.SECONDS);
            // 加锁成功
            log.info("定时任务:查询提现订单信息(已获取锁)." + id);
            try {
                handle();
            } catch (Exception e) {
                log.error("定时任务:查询提现订单信息执行异常", e);
            }
            // 释放锁
            rLock.unlock();
            log.info("定时任务:查询提现订单信息(已释放锁)." + id);
        } catch (Exception e) {
            log.error("定时任务:查询提现订单信息执行异常", e);
        }
    }

    /**
     * 处理订单
     */
    private void handle() {
        // 1.分页查询订单
        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcAgentWithdrawOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(
                ClsMarketEtcAgentWithdrawOrder::getStatus,
                WithdrawStatus.ACCEPTED, WithdrawStatus.PENDING_PAUSE, WithdrawStatus.PENDING_UNKNOWN, WithdrawStatus.PENDING);
        queryWrapper.orderByAsc(ClsMarketEtcAgentWithdrawOrder::getId);
        IPage<ClsMarketEtcAgentWithdrawOrder> iPage = agentWithdrawOrderDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgentWithdrawOrder> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            // 2.循环查询每一笔交易记录
            for (ClsMarketEtcAgentWithdrawOrder order : records) {
                // 获取代理商信息
                ClsMarketEtcAgent agent = agentDao.findOne(order.getAgentId());
                if (agent == null) {
                    continue;
                }
                // 当其中某一笔交易记录同步失败时,不能阻断后面的交易记录处理
                // 获取分布式锁
                RLock rLock = redissonClient.getLock(AGENT_LOCK_NAME + agent.getId());
                try {
                    // 加锁
                    rLock.lock(60, TimeUnit.SECONDS);
                    // 加锁成功
                    // 查询该订单状态
                    Map<String, String> map = yunZhangHuService.orderQuery(order.getSn());
                    // order_id 商户订单号 String 商户订单号，原值返回
                    // pay 打款金额 String 单位：元，支持小数点后两位
                    // broker_id 综合服务主体 ID String 综合服务主体 ID
                    // dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
                    // real_name 姓名 String 姓名
                    // card_no 收款人账号 String 收款人账号
                    // id_card 身份证号 String 身份证号
                    // phone_no 手机号 String 手机号
                    // status 订单状态码 String 详见附录 6.1 订单状态码
                    // status_detail 订单详细状态码 String 详见附录 6.2 订单详细状态码
                    // status_message 订单状态码描述 String 详见附录 6.1 订单状态码
                    // status_detail_message 订单详细状态码描述 String 详见附录 6.2 订单详细状态码
                    // broker_amount 综合服务主体打款金额 String 等于 pay 金额 单位：元，支持小数点后两位
                    // ref 综合服务平台流水号 String 综合服务平台流水号，唯一
                    // broker_bank_bill 打款交易流水号 String 综合服务平台打款交易流水号
                    // withdraw_platform 提现平台 String bankpay：银行卡 alipay：支付宝 wxpay：微信
                    // created_at 订单接收时间 String 精确到秒
                    // finished_time 订单完成时间 String 精确到秒
                    // broker_fee 综合服务主体服务费 String 单位：元，支持小数点后两位
                    // broker_real_fee 余额账户支出服务费 String 单位：元，支持小数点后两位
                    // broker_deduct_fee 抵扣账户支出服务费 String 单位：元，支持小数点后两位
                    // pay_remark 打款备注 String 原值返回
                    // user_fee 用户服务费 String 单位：元，支持小数点后两位
                    // bank_name 银行名称 String 银行名称
                    // encry_data 加密数据 String 当 且 仅 当 data_type 为 encryption 时，返回且仅返回该加密数据字段
                    if (map.containsKey("ref")) {
                        order.setRef(map.get("ref"));
                    }
                    if (map.containsKey("broker_bank_bill")) {
                        order.setBrokerBankBill(map.get("broker_bank_bill"));
                    }
                    if (map.containsKey("withdraw_platform")) {
                        order.setWithdrawPlatform(map.get("withdraw_platform"));
                    }
                    if (map.containsKey("broker_amount") && StringUtils.hasText(map.get("broker_amount"))) {
                        order.setBrokerAmount(new BigDecimal(map.get("broker_amount")));
                    }
                    if (map.containsKey("broker_fee") && StringUtils.hasText(map.get("broker_fee"))) {
                        order.setBrokerFee(new BigDecimal(map.get("broker_fee")));
                    }
                    if (map.containsKey("status") && StringUtils.hasText(map.get("status"))) {
                        order.setStatus(WithdrawStatus.valueByCode(map.get("status")));
                    }
                    if (map.containsKey("status_detail")) {
                        order.setStatusDetail(map.get("status_detail"));
                    }
                    if (map.containsKey("status_detail_message")) {
                        order.setStatusDetailMessage(map.get("status_detail_message"));
                    }
                    if (map.containsKey("finished_time") && StringUtils.hasText(map.get("finished_time"))) {
                        LocalDateTime finishedTime = LocalDateTime.parse(
                                map.get("finished_time"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        order.setFinishedTime(Date.from(finishedTime.toInstant(ZoneOffset.of("+8"))));
                    }
                    if (map.containsKey("pay_remark")) {
                        order.setRemark(map.get("pay_remark"));
                    }
                    agentWithdrawOrderDao.save(agent, order);
//                    // 查询已累计提现金额
//                    BigDecimal totalWithdrawAmount = agentWithdrawOrderDao.totalAmount(agent.getId());
//                    // 更新代理商已累计提现金额
//                    agent.setTotalWithdrawAmount(totalWithdrawAmount);
                    if (order.getStatus() == WithdrawStatus.FAILED ||
                            order.getStatus() == WithdrawStatus.REFUND ||
                            order.getStatus() == WithdrawStatus.CANCELED) {
                        // 打款失败
                        // 扣减累计提现金额
                        if (agentDao.getMapper().increaseWithdrawAmount(agent.getId(), BigDecimal.ZERO.subtract(order.getBrokerAmount())) != 1) {
                            log.error("扣减累计提现金额失败,{},{}", order, agent);
                        } else {
                            // 重新加载代理商信息到缓存
                            agentDao.reloadToCache(agent.getId());
                        }
                    }
                } catch (Exception e) {
                    log.error("查询提现订单信息异常", e);
                } finally {
                    if (rLock.isHeldByCurrentThread()) {
                        // 释放锁
                        rLock.unlock();
                    }
                }
            }
            pageIndex++;
            iPage = agentWithdrawOrderDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }
}
