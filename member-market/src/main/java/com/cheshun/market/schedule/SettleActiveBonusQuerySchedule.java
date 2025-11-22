package com.cheshun.market.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.dao.AgentPromoteHistoryDao;
import com.cheshun.market.domain.dao.GlobalSettingDao;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentOrderGoods;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.cheshun.market.domain.entity.ClsMarketEtcGlobalSetting;
import com.cheshun.market.domain.entity.enums.*;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.config.ScheduleCronConfig;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.domain.dao.AgentOrderGoodsDao;
import com.zzjdyf.market.domain.entity.enums.*;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务:结算ETC卡激活奖励
 *
 * @author 阿隆
 * Created on 2021/8/16 9:05 上午.
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@AllArgsConstructor
public class SettleActiveBonusQuerySchedule implements Runnable, SchedulingConfigurer {
    private final RedissonClient redissonClient;
    private final AgentDao agentDao;
    private final AgentOrderGoodsDao agentOrderGoodsDao;
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
    private final ScheduleCronConfig scheduleCronConfig;
    private final ThreadPoolTaskScheduler settleActiveBonusExecutor;
    private final GlobalSettingDao globalSettingDao;
    private final TransactionDefinition transactionDefinition;
    private final PlatformTransactionManager platformTransactionManager;
    private static final String LOCK_NAME = "SETTLE_ACTIVE_BONUS";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(settleActiveBonusExecutor);
        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getSettleActiveBonus()).nextExecutionTime(triggerContext));
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString().replace("-", "");
        log.debug("定时任务:结算ETC卡激活奖励." + id);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME);
        try {
            // 加锁
            rLock.lock(scheduleCronConfig.getSettleActiveBonusLockTime().getSeconds(), TimeUnit.SECONDS);
            // 加锁成功
            log.info("定时任务:结算ETC卡激活奖励(已获取锁)." + id);
            try {
                // 查询单笔订单信息
                handle(id);
            } catch (Exception e) {
                log.error("定时任务:结算ETC卡激活奖励执行异常." + id, e);
            }
            // 释放锁
            rLock.unlock();
            log.info("定时任务:结算ETC卡激活奖励(已释放锁)." + id);
        } catch (Exception e) {
            log.error("定时任务:结算ETC卡激活奖励执行异常." + id, e);
        }
    }

    /**
     * 结算ETC卡激活奖励
     */
    private void handle(String taskId) {
        // 分页查询可结算激活奖励的推广记录列表
        int pageIndex = 1;
        int pageSize = 20;
        IPage<ClsMarketEtcAgentPromoteHistory> iPage = agentPromoteHistoryDao.getMapper().queryCanSettleActiveBonusList(
                new Page<>(pageIndex, pageSize), BonusStatus.WAIT);
        List<ClsMarketEtcAgentPromoteHistory> records = iPage.getRecords();

        log.info("结算ETC卡激活奖励iPage"+records);
        while (records != null && records.size() > 0) {
            // 循环查询每一条代理商推广记录
            for (ClsMarketEtcAgentPromoteHistory entity : records) {
                log.info("结算entity：：=="+entity);
                try {
                    // 当其中某一条记录查询失败时,不能阻断后面记录的处理
                    handle(taskId, entity);
                } catch (Exception e) {
                    log.error("结算ETC卡激活奖励异常:" + taskId, e);
                }
            }
            pageIndex++;
            iPage = agentPromoteHistoryDao.getMapper().queryCanSettleActiveBonusList(
                    new Page<>(pageIndex, pageSize), BonusStatus.WAIT);
            records = iPage.getRecords();
        }
    }

    private void handle(String taskId, ClsMarketEtcAgentPromoteHistory entity) {
        // 获取直接推广该用户的代理商信息
        ClsMarketEtcAgent agent = null;
        if (entity.getAgentId() != null) {
            agent = agentDao.findOne(entity.getAgentId());
            log.info("agent====="+agent);
        }
        // 根据ETC卡号获得代理订单商品信息
        ClsMarketEtcAgentOrderGoods orderGoods = agentOrderGoodsDao.findOneByCardSn(entity.getCardSn());
        log.info("ETC分润查询orderGoods：："+orderGoods);
        if (orderGoods == null) {
            // 条件1:ETC卡号对应的代理订单商品不存在
            log.info("条件1:ETC卡号对应的代理订单商品不存在:{},{}", taskId, entity);
            // 激活的卡号不是代理商代理的卡
            if (agent == null) {
                // 条件2:直接推广该用户的代理商信息不存在,推广人应该不是代理商
                // 直接设置为【结算失败】状态
                case1(entity);
                log.info("条件2:直接推广该用户的代理商信息不存在,推广人应该不是代理商,直接设置为【结算失败】状态:{},{}", taskId, entity);
                return;
            }
            if (agent.getRole() == AgentRole.STAFF) {
                // 条件3:直接推广该用户的代理商是员工,应该是该员工直接推广的用户
                // 计算直接推广该用户的员工的绩效奖励（理想状态）
                case2(entity);
                log.info("条件3:直接推广该用户的代理商是员工,应该是该员工直接推广的用户,计算直接推广该用户的员工的绩效奖励（理想状态）:{},{}", taskId, entity);
                return;
            }
            // 如果代理商不是员工,那么一定是一级/二级/三级代理中的一种
            // 条件4:直接推广该用户的代理商是代理商,但激活的卡号不是该代理商的
            log.info("条件4:直接推广该用户的代理商是代理商,但激活的卡号不是该代理商的:{},{}", taskId, entity);
            if (entity.getMethod() == ActiveMethod.S) {
                // 条件22:代理商线下安装激活
                // 直接设置为【结算失败】状态
                case3(entity);
                log.info("条件22:代理商线下安装激活,直接设置为【结算失败】状态:{},{}", taskId, entity);
                return;
            }
            if (entity.getMethod() == ActiveMethod.E) {
                // 条件23:用户线上申请,自己激活
                log.info("条件23:用户线上申请,自己激活:{},{}", taskId, entity);
                if (agent.getRole() == AgentRole.AGENT_LEVEL_1) {
                    // 条件24:直接推广该用户的代理商是一级代理
                    // 计算直接推广该用户的代理商的激活奖励
                    case15(entity, agent);
                    log.info("条件24:直接推广该用户的代理商是一级代理,计算直接推广该用户的代理商的激活奖励:{},{}", taskId, entity);
                } else {
                    // 条件25:直接推广该用户的代理商不是一级代理
                    log.info("条件25:直接推广该用户的代理商不是一级代理:{},{}", taskId, entity);
                    // 获取直接推广该用户的代理商所属的一级代理商信息
                    ClsMarketEtcAgent agentForLv1 = agentDao.findOne(agent.getAgent1Id());
                    if (agentForLv1 == null) {
                        // 条件26:直接推广该用户的代理商所属的一级代理商信息不存在
                        // 直接设置为【结算失败】状态
                        case3(entity);
                        log.info("条件26:直接推广该用户的代理商所属的一级代理商信息不存在,直接设置为【结算失败】状态:{},{}", taskId, entity);
                        return;
                    }
                    // 条件27:直接推广该用户的代理商所属的一级代理商信息存在
                    // 计算直接推广该用户的代理商所属的一级代理商的激活奖励
                    case15(entity, agentForLv1);
                    log.info("条件27:直接推广该用户的代理商所属的一级代理商信息存在,计算直接推广该用户的代理商所属的一级代理商的激活奖励:{},{}", taskId, entity);
                }
                return;
            }
            // 条件28:未知激活方式
            // 直接设置为【结算失败】状态
            case3(entity);
            log.info("条件28:未知激活方式,直接设置为【结算失败】状态:{},{}", taskId, entity);
            return;
        }
        // 条件5:ETC卡号对应的代理订单商品存在
        log.info("条件5:ETC卡号对应的代理订单商品存在:{},{}", taskId, entity);
        if (orderGoods.getCardStatus() == ProductStatus.BROKEN) {
            // 条件7:该ETC卡号已被标记为损坏,按理说不应该出现这种情况
            case5(entity);
            log.info("条件7:该ETC卡号已被标记为损坏,按理说不应该出现这种情况:{},{}", taskId, entity);
            return;
        }
        if (orderGoods.getCardStatus() == ProductStatus.BROKEN_RETURNED) {
            // 条件8:该ETC卡号已被标记为损坏并退还,按理说不应该出现这种情况
            case6(entity);
            log.info("条件8:该ETC卡号已被标记为损坏并退还,按理说不应该出现这种情况:{},{}", taskId, entity);
            return;
        }
        // ETC卡号还未激活,可以继续处理
        // 获取ETC卡号对应的一级代理商信息
        ClsMarketEtcAgent agentForLv1 = agentDao.findOne(orderGoods.getAgentId());
        if (agentForLv1 == null) {
            // 条件9:该ETC卡号对应的一级代理商信息不存在,按理说不应该出现这种情况(订单商品都存在,代理商没理由不存在)
            case7(entity);
            log.info("条件9:该ETC卡号对应的一级代理商信息不存在,按理说不应该出现这种情况(订单商品都存在,代理商没理由不存在):{},{}", taskId, entity);
            return;
        }
        // 条件10:ETC卡号对应的一级代理商信息存在
        log.info("条件10:ETC卡号对应的一级代理商信息存在:{},{}", taskId, entity);
        if (agent == null) {
            // 条件11:直接推广该用户的代理商信息不存在,推广人应该不是代理商
            // 计算ETC卡号对应的一级代理商的激活奖励
            entity.setAgentId(agentForLv1.getId());
            case8(entity, agentForLv1);
            log.info("条件11:直接推广该用户的代理商信息不存在,推广人应该不是代理商,计算ETC卡号对应的一级代理商的激活奖励:{},{}", taskId, entity);
            return;
        }
        if (agent.getRole() == AgentRole.STAFF) {
            // 条件12:直接推广该用户的代理商是员工,应该是该员工直接推广的用户,而且该用户用的某个一级代理商的卡
            log.info("条件12:直接推广该用户的代理商是员工,应该是该员工直接推广的用户,而且该用户用的某个一级代理商的卡:{},{}", taskId, entity);
            // 判断直接推广该用户的员工是否是ETC卡号对应的一级代理商所属的员工
            if (agent.getId().equals(agentForLv1.getStaffId())) {
                // 条件13:ETC卡号对应的一级代理商是直接推广该用户的员工的下级
                // 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
                case9(entity, agentForLv1);
                log.info("条件13:ETC卡号对应的一级代理商是直接推广该用户的员工的下级,计算ETC卡号对应的一级代理商的激活奖励（理想状态）:{},{}", taskId, entity);
                return;
            }
            // 条件14:ETC卡号对应的一级代理商不是直接推广该用户的员工的下级
            // 计算ETC卡号对应的一级代理商的激活奖励
            case10(entity, agentForLv1);
            log.info("条件14:ETC卡号对应的一级代理商不是直接推广该用户的员工的下级:{},{}", taskId, entity);
            return;
        }
        // 如果直接推广该用户的代理商不是员工,那么一定是一级/二级/三级代理中的一种
        // 条件15:直接推广该用户的代理商是一级/二级/三级代理商
        log.info("条件15:直接推广该用户的代理商是一级/二级/三级代理商:{},{}", taskId, entity);
        // 检测直接推广该用户的代理商与ETC卡号对应的一级代理商是否是同一人
        if (agent.getId().equals(agentForLv1.getId())) {
            // 条件16:直接推广该用户的代理商与ETC卡号对应的一级代理商是同一人,直接推广该用户的代理商是一级代理商
            // 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
            case11(entity, agentForLv1);
            log.info("条件16:直接推广该用户的代理商与ETC卡号对应的一级代理商是同一人,直接推广该用户的代理商是一级代理商,计算ETC卡号对应的一级代理商的激活奖励（理想状态）:{},{}", taskId, entity);
            return;
        }
        // 条件17:直接推广该用户的代理商与ETC卡号对应的一级代理商不是同一人
        log.info("条件17:直接推广该用户的代理商与ETC卡号对应的一级代理商不是同一人:{},{}", taskId, entity);
        // 检测直接推广该用户的代理商与ETC卡号对应的一级代理商之间的关系
        if (agent.getAgent1Id().equals(agentForLv1.getId())) {
            // 条件18:直接推广该用户的代理商是ETC卡号对应的一级代理商的下级
            log.info("条件18:直接推广该用户的代理商是ETC卡号对应的一级代理商的下级:{},{}", taskId, entity);
            if (agent.getRole() == AgentRole.AGENT_LEVEL_2) {
                // 条件19:直接推广该用户的代理商是二级代理商
                // 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
                case12(entity, agentForLv1);
                log.info("条件19:直接推广该用户的代理商是二级代理商,计算ETC卡号对应的一级代理商的激活奖励（理想状态）:{},{}", taskId, entity);
                return;
            }
            // 条件20:直接推广该用户的代理商是三级代理商
            // 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
            case13(entity, agent, agentForLv1);
            log.info("条件20:直接推广该用户的代理商是二级代理商,计算ETC卡号对应的一级代理商的激活奖励（理想状态）:{},{}", taskId, entity);
            return;
        }
        // 条件21:直接推广该用户的代理商不是ETC卡号对应的一级代理商的下级
        // 计算ETC卡号对应的一级代理商的激活奖励
        case14(entity, agentForLv1);
        log.info("条件21:直接推广该用户的代理商不是ETC卡号对应的一级代理商的下级,计算ETC卡号对应的一级代理商的激活奖励:{},{}", taskId, entity);
    }

    /**
     * 条件1:ETC卡号对应的代理订单商品不存在,激活的卡号不是代理商代理的卡
     * 条件2:直接推广该用户的代理商信息不存在,推广人应该不是代理商
     * 直接设置为【结算失败】状态
     */
    private void case1(ClsMarketEtcAgentPromoteHistory entity) {
        // 设置激活奖励状态为【结算失败】
        entity.setActiveBonusStatus(BonusStatus.FAILED);
        // 设置首次消费奖励状态为【结算失败】
        entity.setFirstConsumeBonusStatus(BonusStatus.FAILED);
    }

    /**
     * 条件1:ETC卡号对应的代理订单商品不存在,激活的卡号不是代理商代理的卡
     * 条件3:直接推广该用户的代理商是员工,应该是该员工直接推广的用户
     * 计算直接推广该用户的员工的绩效奖励（理想状态）
     */
    private void case2(ClsMarketEtcAgentPromoteHistory entity) {
        // 获取全局配置信息
        Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap = globalSettingDao.findAll();
        // 计算员工绩效奖励
        BigDecimal staffBonus = calculateStaffBonus(globalSettingMap, entity.getAgentId());
        entity.setStaffBonus(staffBonus);
        // 设置激活奖励状态为【已结算】
        entity.setActiveBonusStatus(BonusStatus.SETTLED);
        // 设置首次消费奖励状态为【已结算】
        entity.setFirstConsumeBonusStatus(BonusStatus.SETTLED);
        // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            // 保存推广信息
            entity = agentPromoteHistoryDao.save(entity, false);
            if (entity == null) {
                throw new RRException("保存推广信息失败");
            }
            if (entity.getStaffBonus().compareTo(BigDecimal.ZERO) > 0) {
                // 员工有绩效奖励
                if (agentDao.getMapper().increaseActiveBonus(entity.getAgentId(), entity.getStaffBonus()) != 1) {
                    throw new RRException("增加员工绩效奖励失败");
                }
            }
            // 提交事务
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            // 执行异常,回滚事务
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        }
        // 重新加载数据到缓存
        agentPromoteHistoryDao.reloadToCache(entity.getId());
        agentDao.reloadToCache(entity.getAgentId());
    }

    /**
     * 条件1:ETC卡号对应的代理订单商品不存在,激活的卡号不是代理商代理的卡
     * 条件4:直接推广该用户的代理商是代理商,但激活的卡号不是该代理商的
     * 条件22:代理商线下安装激活
     * 直接设置为【结算失败】状态
     */
    private void case3(ClsMarketEtcAgentPromoteHistory entity) {
        case1(entity);
    }

    /**
     * 条件1:ETC卡号对应的代理订单商品不存在,激活的卡号不是代理商代理的卡
     * 条件4:直接推广该用户的代理商是代理商,但激活的卡号不是该代理商的
     * 条件23:线上邮寄给用户,用户自行安装
     * 计算直接推广该用户的代理商的激活奖励
     */
    private void case15(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        case8(entity, agentForLv1);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件7:该ETC卡号已被标记为损坏,按理说不应该出现这种情况
     * 直接设置为【结算失败】状态
     */
    private void case5(ClsMarketEtcAgentPromoteHistory entity) {
        case1(entity);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件8:该ETC卡号已被标记为损坏并退还,按理说不应该出现这种情况
     * 直接设置为【结算失败】状态
     */
    private void case6(ClsMarketEtcAgentPromoteHistory entity) {
        case1(entity);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件9:该ETC卡号对应的一级代理商信息不存在,按理说不应该出现这种情况(订单商品都存在,代理商没理由不存在)
     * 直接设置为【结算失败】状态
     */
    private void case7(ClsMarketEtcAgentPromoteHistory entity) {
        case1(entity);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件11:直接推广该用户的代理商信息不存在,推广人应该不是代理商
     * 计算ETC卡号对应的一级代理商的激活奖励
     */
    private void case8(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        // 设置员工id
        entity.setStaffId(agentForLv1.getStaffId());
        // 设置一级代理商id
        entity.setAgent1Id(agentForLv1.getId());
        // 获取全局配置信息
        Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap = globalSettingDao.findAll();
        // 计算一级代理分润奖励
        BigDecimal agent1Bonus = calculateAgentBonus(globalSettingMap, entity, agentForLv1);
        entity.setActiveBonus(agent1Bonus);
        // 设置激活奖励状态为已结算
        entity.setActiveBonusStatus(BonusStatus.SETTLED);
        // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            // 保存推广信息
            entity = agentPromoteHistoryDao.save(entity, false);
            if (entity == null) {
                throw new RRException("保存推广信息失败");
            }
            // 给一级代理商增加累计激活奖励
            if (agentDao.getMapper().increaseActiveBonus(entity.getAgent1Id(), agent1Bonus) != 1) {
                throw new RRException("给一级代理商增加累计激活奖励失败");
            }
            // 提交事务
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            // 执行异常,回滚事务
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        }
        // 重新加载数据到缓存
        agentPromoteHistoryDao.reloadToCache(entity.getId());
        agentDao.reloadToCache(entity.getAgent1Id());
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件12:直接推广该用户的代理商是员工,应该是该员工直接推广的用户,而且该用户用的某个一级代理商的卡
     * 条件13:ETC卡号对应的一级代理商是直接推广该用户的员工的下级
     * 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
     */
    private void case9(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        case8(entity, agentForLv1);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件12:直接推广该用户的代理商是员工,应该是该员工直接推广的用户,而且该用户用的某个一级代理商的卡
     * 条件14:ETC卡号对应的一级代理商不是直接推广该用户的员工的下级
     * 计算ETC卡号对应的一级代理商的激活奖励
     */
    private void case10(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        case8(entity, agentForLv1);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件15:直接推广该用户的代理商是一级/二级/三级代理商
     * 条件16:直接推广该用户的代理商与ETC卡号对应的一级代理商是同一人,直接推广该用户的代理商是一级代理商
     * 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
     */
    private void case11(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        case8(entity, agentForLv1);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件15:直接推广该用户的代理商是一级/二级/三级代理商
     * 条件17:直接推广该用户的代理商与ETC卡号对应的一级代理商不是同一人
     * 条件18:直接推广该用户的代理商是ETC卡号对应的一级代理商的下级
     * 条件19:直接推广该用户的代理商是二级代理商
     * 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
     */
    private void case12(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        case8(entity, agentForLv1);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件15:直接推广该用户的代理商是一级/二级/三级代理商
     * 条件17:直接推广该用户的代理商与ETC卡号对应的一级代理商不是同一人
     * 条件18:直接推广该用户的代理商是ETC卡号对应的一级代理商的下级
     * 条件20:直接推广该用户的代理商是三级代理商
     * 计算ETC卡号对应的一级代理商的激活奖励（理想状态）
     */
    private void case13(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agent, ClsMarketEtcAgent agentForLv1) {
        entity.setAgent2Id(agent.getAgent2Id());
        case8(entity, agentForLv1);
    }

    /**
     * 条件5:ETC卡号对应的代理订单商品存在
     * 条件10:ETC卡号对应的一级代理商信息存在
     * 条件15:直接推广该用户的代理商是一级/二级/三级代理商
     * 条件17:直接推广该用户的代理商与ETC卡号对应的一级代理商不是同一人
     * 条件21:直接推广该用户的代理商不是ETC卡号对应的一级代理商的下级
     * 计算ETC卡号对应的一级代理商的激活奖励
     */
    private void case14(ClsMarketEtcAgentPromoteHistory entity, ClsMarketEtcAgent agentForLv1) {
        case8(entity, agentForLv1);
    }

    /**
     * 计算一级代理分润奖励
     */
    private BigDecimal calculateAgentBonus(Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap,
                                           ClsMarketEtcAgentPromoteHistory entity,
                                           ClsMarketEtcAgent agentForLv1) {
        ActiveMethod activeMethod = entity.getMethod();
        if (globalSettingMap.size() == 0) {
            log.info("计算一级代理分润奖励异常,未配置分润信息:{}", agentForLv1.getId());
            return BigDecimal.ZERO;
        }
        // 保存总奖励
        BigDecimal totalBonus = BigDecimal.ZERO;

        // ************ 每激活一套，代理商即可获得的奖励 ***********
        if (activeMethod == ActiveMethod.E) {
            // 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装
            BigDecimal bonus = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_ONLINE_ACTIVE_BONUS);
            totalBonus = totalBonus.add(bonus);
            log.info("代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装,一级代理(ID={})可获得激活返还奖励 {} 元",
                    agentForLv1.getId(), bonus);
        } else if (activeMethod == ActiveMethod.S) {
            // 代理商推介用户成功申办，并现场安装ETC套装
            BigDecimal bonus = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_OFFLINE_ACTIVE_BONUS);
            totalBonus = totalBonus.add(bonus);
            log.info("代理商推介用户成功申办，并现场安装ETC套装,一级代理(ID={})可获得激活返还奖励 {} 元",
                    agentForLv1.getId(), bonus);
        }

        // ************ 每激活一套，代理商即可获得的额外奖励 ***********
        if (activeMethod == ActiveMethod.S) {
            // 代理商推介用户成功申办，并现场安装ETC套装
            BigDecimal bonus = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_OFFLINE_EXTRA_ACTIVE_BONUS);
            totalBonus = totalBonus.add(bonus);
            log.info("代理商推介用户成功申办，并现场安装ETC套装,一级代理(ID={})可获得激活返还额外奖励 {} 元",
                    agentForLv1.getId(), bonus);
        }

        // ************ 月激活量超过阈值后的"额外"奖励 ***********
        // 查询一级代理本月已累计推广用户数量
        LocalDate nowDate = LocalDate.now();
        LocalDateTime startDate = nowDate.withDayOfMonth(1).atStartOfDay();
        LocalDateTime endDate = nowDate.plusMonths(1).withDayOfMonth(1).atStartOfDay();
        LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(ClsMarketEtcAgentPromoteHistory::getActiveTime, Date.from(startDate.toInstant(ZoneOffset.of("+8"))));
        queryWrapper.lt(ClsMarketEtcAgentPromoteHistory::getActiveTime, Date.from(endDate.toInstant(ZoneOffset.of("+8"))));
        queryWrapper.and(wrapper -> wrapper.eq(ClsMarketEtcAgentPromoteHistory::getAgentId, agentForLv1.getId())
                .or()
                .eq(ClsMarketEtcAgentPromoteHistory::getAgent1Id, agentForLv1.getId())

        );
        //只查询当前记录之前的数据
        queryWrapper.lt(ClsMarketEtcAgentPromoteHistory::getId,entity.getId());
        int monthActivatedCount = Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0);
        log.info("一级代理(ID={})本月已激活 {} 套", agentForLv1.getId(), monthActivatedCount);
        if (monthActivatedCount > 0) {
            if (activeMethod == ActiveMethod.E) {
                // 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装
                // 获取额外奖励需要的阈值
                ClsMarketEtcGlobalSetting globalSetting = globalSettingMap.get(GlobalSettingKey.AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD);
                int extraBonusThreshold = Optional.ofNullable(globalSetting).map(t -> Integer.parseInt(t.getData())).orElse(0);
                extraBonusThreshold = Math.max(0, extraBonusThreshold);
                log.info("月激活额外奖励需激活 {} 套", extraBonusThreshold);
                if (extraBonusThreshold > 0 && monthActivatedCount + 1 >= extraBonusThreshold) {
                    BigDecimal bonusE = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS);
                    totalBonus = totalBonus.add(bonusE);
                    entity.setExtraStatus("1");
                    log.info("代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装,一级代理(ID={})可获得月激活额外奖励 {} 元",
                            agentForLv1.getId(), bonusE);
                    queryWrapper = getQueryMapper(ActiveMethod.E.getValue(),startDate,endDate,agentForLv1,entity);
                    int extraCountE = Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0);
                    if(extraCountE>0){
                        log.info("超过月激活数之后，之前所有未额外分润套数，额外分润E"+extraCountE);
                        BigDecimal extraBonus = bonusE.multiply(new BigDecimal(extraCountE));
                        totalBonus = totalBonus.add(extraBonus);
                        ClsMarketEtcAgentPromoteHistory history = getUpdateMarketEtcAgentPromoteHistory();
                        agentPromoteHistoryDao.getMapper().update(history,queryWrapper);
                    }
                    queryWrapper = getQueryMapper(ActiveMethod.S.getValue(),startDate,endDate,agentForLv1,entity);
                    int extraCountS = Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0);
                    if(extraCountS>0){
                        log.info("超过月激活数之后，之前所有未额外分润套数，额外分润S"+extraCountS);
                        BigDecimal bonusS = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS);
                        BigDecimal extraBonus = bonusS.multiply(new BigDecimal(extraCountS));
                        totalBonus = totalBonus.add(extraBonus);
                        ClsMarketEtcAgentPromoteHistory history = getUpdateMarketEtcAgentPromoteHistory();
                        agentPromoteHistoryDao.getMapper().update(history,queryWrapper);
                    }
                }
            } else if (activeMethod == ActiveMethod.S) {
                // 代理商推介用户成功申办，并现场安装ETC套装
                // 获取额外奖励需要的阈值
                ClsMarketEtcGlobalSetting globalSetting = globalSettingMap.get(GlobalSettingKey.AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD);
                int extraBonusThreshold = Optional.ofNullable(globalSetting).map(t -> Integer.parseInt(t.getData())).orElse(0);
                extraBonusThreshold = Math.max(0, extraBonusThreshold);
                log.info("月激活额外奖励需激活 {} 套", extraBonusThreshold);
                if (extraBonusThreshold > 0 && monthActivatedCount + 1 >= extraBonusThreshold) {
                    // 本月已激活数量,加上这次满 extraBonusThreshold 次,可获得额外奖励
                    BigDecimal bonusS = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS);
                    totalBonus = totalBonus.add(bonusS);
                    entity.setExtraStatus("1");
                    log.info("代理商推介用户成功申办，并现场安装ETC套装,一级代理(ID={})可获得月激活额外奖励 {} 元",
                            agentForLv1.getId(), bonusS);

                    queryWrapper = getQueryMapper(ActiveMethod.S.getValue(),startDate,endDate,agentForLv1,entity);
                    int extraCountS = Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0);
                    if(extraCountS>0){
                        log.info("超过月激活数之后，之前所有未额外分润套数，额外分润S"+extraCountS);
                        BigDecimal extraBonus = bonusS.multiply(new BigDecimal(extraCountS));
                        totalBonus = totalBonus.add(extraBonus);
                        ClsMarketEtcAgentPromoteHistory history = getUpdateMarketEtcAgentPromoteHistory();
                        agentPromoteHistoryDao.getMapper().update(history,queryWrapper);
                    }
                    queryWrapper = getQueryMapper(ActiveMethod.E.getValue(),startDate,endDate,agentForLv1,entity);
                    int extraCountE = Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0);
                    if(extraCountE>0){
                        log.info("超过月激活数之后，之前所有未额外分润套数，额外分润E"+extraCountE);
                        BigDecimal bonusE = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS);
                        BigDecimal extraBonus = bonusE.multiply(new BigDecimal(extraCountE));
                        totalBonus = totalBonus.add(extraBonus);
                        ClsMarketEtcAgentPromoteHistory history = getUpdateMarketEtcAgentPromoteHistory();
                        agentPromoteHistoryDao.getMapper().update(history,queryWrapper);
                    }
                }
            }
        }

        log.info("一级代理(ID={})可获得累计奖励 {} 元", agentForLv1.getId(), totalBonus);
        return totalBonus;
    }
    public LambdaQueryWrapper getQueryMapper(String str,LocalDateTime startDate,LocalDateTime endDate,
                                             ClsMarketEtcAgent agentForLv1,ClsMarketEtcAgentPromoteHistory entity){
        LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(ClsMarketEtcAgentPromoteHistory::getActiveTime, Date.from(startDate.toInstant(ZoneOffset.of("+8"))));
        queryWrapper.lt(ClsMarketEtcAgentPromoteHistory::getActiveTime, Date.from(endDate.toInstant(ZoneOffset.of("+8"))));
        queryWrapper.and(wrapper -> wrapper.eq(ClsMarketEtcAgentPromoteHistory::getAgentId, agentForLv1.getId())
                .or()
                .eq(ClsMarketEtcAgentPromoteHistory::getAgent1Id, agentForLv1.getId())
        );
        queryWrapper.eq(ClsMarketEtcAgentPromoteHistory::getExtraStatus,"0");
        queryWrapper.eq(ClsMarketEtcAgentPromoteHistory::getMethod,str);
        queryWrapper.lt(ClsMarketEtcAgentPromoteHistory::getId,entity.getId());
        return  queryWrapper;
    }
    public ClsMarketEtcAgentPromoteHistory getUpdateMarketEtcAgentPromoteHistory(){
        ClsMarketEtcAgentPromoteHistory history = new ClsMarketEtcAgentPromoteHistory();
        history.setExtraStatus("1");
        history.setActiveBonus(null);
        history.setFirstConsumeBonus(null);
        history.setStaffBonus(null);
        return history;
    }


    /**
     * 计算员工绩效奖励
     */
    private BigDecimal calculateStaffBonus(Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap,
                                           Long staffAgentId) {
        if (globalSettingMap.size() == 0) {
            return BigDecimal.ZERO;
        }
        // ************ 每激活一套，员工即可获得的奖励 ***********
        BigDecimal totalBonus = calculateBonus(globalSettingMap, GlobalSettingKey.STAFF_BONUS);
        log.info("员工直接推介用户成功申办并激活安装ETC套装，员工(ID={})可获得奖励 {} 元", staffAgentId, totalBonus);
        return totalBonus;
    }

    private BigDecimal calculateBonus(Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap,
                                      GlobalSettingKey key) {
        ClsMarketEtcGlobalSetting globalSetting = globalSettingMap.get(key);
        return new BigDecimal(Optional.ofNullable(globalSetting)
                .map(ClsMarketEtcGlobalSetting::getData)
                .orElse("0")
        ).max(BigDecimal.ZERO);
    }
}
