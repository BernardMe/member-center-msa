package com.zzjdyf.market.service.app;

import cn.hutool.core.lang.UUID;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.GlobalConfig;
import com.zzjdyf.market.domain.dao.AgentBankCardDao;
import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.dao.AgentWithdrawOrderDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentBankCard;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentWithdrawOrder;
import com.zzjdyf.market.domain.entity.enums.AgentRole;
import com.zzjdyf.market.domain.entity.enums.WithdrawStatus;
import com.zzjdyf.market.service.YunZhangHuService;
import com.zzjdyf.market.vo.command.AgentWithdrawCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/8/4 3:09 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentWithdrawAppService {
    private final AgentDao agentDao;
    private final AgentBankCardDao agentBankCardDao;
    private final AgentWithdrawOrderDao agentWithdrawOrderDao;
    private final YunZhangHuService yunZhangHuService;
    private final TransactionDefinition transactionDefinition;
    private final PlatformTransactionManager platformTransactionManager;
    private final GlobalConfig globalConfig;
    private final RedissonClient redissonClient;
    private static final String LOCK_NAME = "WITHDRAW_LOCK_%d";

    /**
     * 提现
     *
     * @param id      代理商Id
     * @param command 命令 {@link AgentWithdrawCommand}
     */
    public void withdraw(Long id, AgentWithdrawCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                // 员工的奖励不走云账户提现,直接随工资发放
                .filter(t -> t.getRole() != AgentRole.STAFF)
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (!agent.isEnableWithdraw()) {
            throw new RRException("您无提现权限,请联系您的商务经理");
        }
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            // 不是一级代理商,无法提现
            throw new RRException("您无提现权限,请联系您的商务经理");
        }
        // 校验代理商是否已绑定该卡
        List<ClsMarketEtcAgentBankCard> bankCardList = agentBankCardDao.findAllByAgent(agent.getId());
        if (bankCardList.isEmpty()) {
            throw new RRException("提现失败,您还未绑定银行卡");
        }
        ClsMarketEtcAgentBankCard bankCard = bankCardList.stream()
                .filter(t -> t.getId().equals(command.getBankCardId()))
                .findFirst()
                .orElseThrow(() -> new RRException("提现失败,不是您的银行卡"));
        // 校验可用余额是否足够
        // 检测可提现金额是否足够
        if (command.getAmount().compareTo(agent.getBalance()) > 0) {
            throw new RRException("提现失败,可用余额不足", agent.toString());
        }
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME + id);
        try {
            // 加锁
            rLock.lock(60, TimeUnit.SECONDS);
            // 加锁成功
            log.info("提现(已获取锁)." + id);
            // 检测该代理商是否有正在提现处理中的订单
            if (agentWithdrawOrderDao.existAcceptedWithdrawOrder(agent.getId())) {
                throw new RRException("您的上一笔提现申请正在处理中,请耐心等待");
            }
            // 获取该代理商今日已提现次数
            int todayWithdrawTimes = agentWithdrawOrderDao.todayWithdrawTimes(agent.getId());
            if (todayWithdrawTimes >= globalConfig.getWithdrawMaxTimesOfDay()) {
                throw new RRException("提现失败,每天最多允许提现" + globalConfig.getWithdrawMaxTimesOfDay() + "次");
            }
            // 获取该代理商今日已提现金额
            BigDecimal todayWithdrawAmount = agentWithdrawOrderDao.todayWithdrawAmount(agent.getId());
            if (todayWithdrawAmount.add(command.getAmount()).compareTo(BigDecimal.valueOf(globalConfig.getWithdrawSumAmountOfDay())) >= 0) {
                throw new RRException("提现失败,每天最多允许提现" + globalConfig.getWithdrawSumAmountOfDay() + "元");
            }
            // 从提现明细中查询已累计提现金额
            try (HintManager hintManager = HintManager.getInstance()) {
                // 从主库查询
                hintManager.setPrimaryRouteOnly();
                // 更新代理商已累计提现金额
                agent.setTotalWithdrawAmount(agentWithdrawOrderDao.totalAmount(agent.getId()));
            } catch (Exception e) {
                log.error("从提现明细中查询已累计提现金额异常", e);
                throw new RRException("提现失败,系统异常");
            }
            // 检测可提现金额是否足够
            if (command.getAmount().compareTo(agent.getBalance()) > 0) {
                throw new RRException("提现失败,可用余额不足", agent.toString());
            }
            // 可用余额足够
            // 创建提现订单
            ClsMarketEtcAgentWithdrawOrder order = new ClsMarketEtcAgentWithdrawOrder();
            order.setAgentId(agent.getId());
            order.setSn(UUID.fastUUID().toString(true));
            order.setWithdrawPlatform("银行卡");
            order.setRealName(bankCard.getRealName());
            order.setBankName(bankCard.getBankName());
            order.setCardNo(bankCard.getCardNo());
            order.setIdCard(bankCard.getIdCard());
            order.setPhoneNo(null);
            order.setBrokerAmount(command.getAmount());
            order.setBrokerFee(BigDecimal.ZERO);
            order.setStatus(WithdrawStatus.ACCEPTED);
            order.setRemark("提现");
            // 提现请求发送结果
            Map<String, String> result;
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 保存提现订单到数据库(禁止写入缓存)
                order = agentWithdrawOrderDao.save(order, false);
                if (order == null) {
                    throw new RRException("保存提现订单到数据库失败");
                }
                // 增加累计提现金额
                if (agentDao.getMapper().increaseWithdrawAmount(agent.getId(), command.getAmount()) != 1) {
                    throw new RRException("增加累计提现金额失败");
                }
                // 调用云账户提现API
                result = yunZhangHuService.bankCardOrder(
                        order.getSn(),
                        order.getRealName(),
                        order.getCardNo(),
                        order.getIdCard(),
                        null,
                        order.getBrokerAmount(),
                        order.getRemark());
                // order_id 商户订单号 String 商户订单号，原值返回
                // ref 综合服务平台流水号 String 综合服务平台流水号，唯一
                // pay 打款金额 String 单位：元，支持小数点后两位
                // 提现请求发送正常,提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
            // 更新提现订单到数据库(可写入缓存和ES)
            order.setRef(result.get("ref"));
            agentWithdrawOrderDao.save(agent, order);
            // 重新加载代理商信息到缓存
            agentDao.reloadToCache(agent.getId());
        } catch (RRException e) {
            throw e;
        } catch (Exception e) {
            throw new RRException("提现失败,系统异常", e);
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                // 释放锁
                rLock.unlock();
                log.info("提现(已释放锁)." + id);
            }
        }
    }

    /**
     * 提现结果通知
     *
     * @param data      通知参数
     * @param mess      通知参数
     * @param timestamp 通知参数
     * @param sign      通知参数
     * @param sign_type 通知参数
     * @param response  {@link HttpServletResponse}
     */
    public void notify(String data, String mess, String timestamp, String sign, String sign_type, HttpServletResponse response) {
        yunZhangHuService.orderNotify(data, mess, timestamp, sign, sign_type, response);
//        if (map == null) {
//            return;
//        }
//        // order_id 商户订单号 String 商户订单号，原值返回
//        // ref 综合服务平台流水号 String 综合服务平台流水号，唯一
//        // pay 打款金额 String 单位：元，支持小数点后两位
//        // broker_id 综合服务主体 ID String 综合服务主体 ID
//        // dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
//        // real_name 姓名 String 姓名
//        // card_no 收款人账号 String 收款人账号
//        // id_card 身份证号 String 身份证号
//        // phone_no 手机号 String 手机号
//        // status 订单状态码 String 详见附录 6.1 订单状态码
//        // status_detail 订单详细状态码 String 详见附录 6.2 订单详细状态码
//        // status_message 订单状态码描述 String 详见附录 6.1 订单状态码
//        // status_detail_message 订单详细状态码描述 String 详见附录 6.2 订单详细状态码
//        // broker_amount 综合服务主体打款金额 String 等于
//        // pay 金额 单位：元，支持小数点后两位
//        // ref 综合服务平台流水号 String 综合服务平台流水号，唯一
//        // broker_bank_bill 打款交易流水号 String 综合服务平台打款交易流水号
//        // withdraw_platform 提现平台 String bankpay：银行卡 alipay：支付宝 wxpay：微信
//        // created_at 订单接收时间 String 精确到秒
//        // finished_time 订单完成时间 String 精确到秒
//        // broker_fee 综合服务主体服务费 String 单位：元，支持小数点后两位
//        // broker_real_fee 余额账户支出服务费 String 单位：元，支持小数点后两位
//        // broker_deduct_fee 抵扣账户支出服务费 String 单位：元，支持小数点后两位
//        // pay_remark 打款备注 String 原值返回
//        // user_fee 用户服务费 String 单位：元，支持小数点后两位
//        // bank_name 银行名称 String 银行名称
//        String orderId = map.get("order_id");
//        // 获取提现订单
//        ClsMarketEtcAgentWithdrawOrder order = agentWithdrawOrderDao.findOneBySn(orderId);
//        if (order == null) {
//            log.error("提现通知处理异常,订单不存在:" + JSONUtil.toJsonStr(map));
//            return;
//        }
//        ClsMarketEtcAgent agent = agentDao.findOne(order.getAgentId());
//        if (agent == null) {
//            log.error("提现通知处理异常,代理商不存在:" + JSONUtil.toJsonStr(map));
//            return;
//        }
//        WithdrawStatus status = WithdrawStatus.valueByCode(Integer.parseInt(map.get("status")));
//        if (status == null) {
//            log.error("提现通知处理异常,订单状态解析异常." + JSONUtil.toJsonStr(map));
//            return;
//        }
//        if (order.getStatus() == status) {
//            // 订单状态无变化,跳过处理
//            return;
//        }
//        order.setStatus(status);
//        // 更新订单信息
//        if (map.containsKey("broker_bank_bill")) {
//            order.setBrokerBankBill(map.get("broker_bank_bill"));
//        }
//        if (map.containsKey("withdraw_platform")) {
//            order.setWithdrawPlatform(map.get("withdraw_platform"));
//        }
////        if (map.containsKey("broker_amount") && StringUtils.hasText(map.get("broker_amount"))) {
////            order.setBrokerAmount(new BigDecimal(map.get("broker_amount")));
////        }
//        if (map.containsKey("broker_fee") && StringUtils.hasText(map.get("broker_fee"))) {
//            order.setBrokerFee(new BigDecimal(map.get("broker_fee")));
//        }
//        if (map.containsKey("status_detail")) {
//            order.setStatusDetail(map.get("status_detail"));
//        }
//        if (map.containsKey("status_detail_message")) {
//            order.setStatusDetailMessage(map.get("status_detail_message"));
//        }
//        if (map.containsKey("finished_time") && StringUtils.hasText(map.get("finished_time"))) {
//            LocalDateTime finishedTime = LocalDateTime.parse(
//                    map.get("finished_time"),
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            order.setFinishedTime(Date.from(finishedTime.toInstant(ZoneOffset.of("+8"))));
//        }
//        if (map.containsKey("pay_remark")) {
//            order.setRemark(map.get("pay_remark"));
//        }
//        try {
//            // 保存订单信息
//            order = agentWithdrawOrderDao.save(agent, order);
//            if (order != null) {
//                if (order.getStatus() == WithdrawStatus.FAILED ||
//                        order.getStatus() == WithdrawStatus.REFUND ||
//                        order.getStatus() == WithdrawStatus.CANCELED) {
//                    // 打款失败
//                    // 扣减累计提现金额
//                    if (agentDao.getMapper().increaseWithdrawAmount(agent.getId(), BigDecimal.ZERO.subtract(order.getBrokerAmount())) != 1) {
//                        log.error("提现通知处理异常:扣减累计提现金额失败,{},{}", order, agent);
//                    } else {
//                        // 重新加载代理商信息到缓存
//                        agentDao.reloadToCache(agent.getId());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log.error("提现通知处理异常,保存订单信息异常." + JSONUtil.toJsonStr(map));
//        }
    }
}
