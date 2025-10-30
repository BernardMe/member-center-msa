package com.zzjdyf.market.schedule;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.ScheduleCronConfig;
import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.dao.AgentPromoteHistoryDao;
import com.zzjdyf.market.domain.dao.GlobalSettingDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGlobalSetting;
import com.zzjdyf.market.domain.entity.enums.ActiveMethod;
import com.zzjdyf.market.domain.entity.enums.AgentRole;
import com.zzjdyf.market.domain.entity.enums.BonusStatus;
import com.zzjdyf.market.domain.entity.enums.GlobalSettingKey;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务:结算ETC卡首次消费奖励
 *
 * @author 阿隆
 * Created on 2021/8/16 9:05 上午.
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@AllArgsConstructor
public class SettleFirstConsumeBonusSchedule implements Runnable, SchedulingConfigurer {
    private final RedissonClient redissonClient;
    private final AgentDao agentDao;
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
    private final ScheduleCronConfig scheduleCronConfig;
    private final ThreadPoolTaskScheduler settleFirstConsumeBonusExecutor;
    private final GlobalSettingDao globalSettingDao;
    private final TransactionDefinition transactionDefinition;
    private final PlatformTransactionManager platformTransactionManager;
    private static final String LOCK_NAME = "SETTLE_FIRST_CONSUME";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(settleFirstConsumeBonusExecutor);
        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getSettleFirstConsumeBonus()).nextExecutionTime(triggerContext));
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString().replace("-", "");
        log.debug("定时任务:结算ETC卡首次消费奖励." + id);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME);
        try {
            // 加锁
            rLock.lock(scheduleCronConfig.getSettleFirstConsumeBonusLockTime().getSeconds(), TimeUnit.SECONDS);
            // 加锁成功
            log.info("定时任务:结算ETC卡首次消费奖励(已获取锁)." + id);
            try {
                // 查询单笔订单信息
                handle(id);
            } catch (Exception e) {
                log.error("定时任务:结算ETC卡首次消费奖励执行异常." + id, e);
            }
            // 释放锁
            rLock.unlock();
            log.info("定时任务:结算ETC卡首次消费奖励(已释放锁)." + id);
        } catch (Exception e) {
            log.error("定时任务:结算ETC卡首次消费奖励执行异常." + id, e);
        }
    }

    /**
     * 结算ETC卡首次消费奖励
     */
    private void handle(String taskId) {
        // 分页查询可结算首次消费奖励的推广记录列表
        int pageIndex = 1;
        int pageSize = 20;
        IPage<ClsMarketEtcAgentPromoteHistory> iPage = agentPromoteHistoryDao.getMapper().queryCanSettleFirstConsumeBonusList(
                new Page<>(pageIndex, pageSize), BonusStatus.SETTLED, BonusStatus.WAIT);
        List<ClsMarketEtcAgentPromoteHistory> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            // 循环查询每一条代理商推广记录
            for (ClsMarketEtcAgentPromoteHistory entity : records) {
                // 当其中某一条记录查询失败时,不能阻断后面记录的处理
                try {
                    handle(taskId, entity);
                } catch (Exception e) {
                    log.error("结算ETC卡首次消费奖励异常:" + taskId, e);
                }
            }
            pageIndex++;
            iPage = agentPromoteHistoryDao.getMapper().queryCanSettleFirstConsumeBonusList(
                    new Page<>(pageIndex, pageSize), BonusStatus.SETTLED, BonusStatus.WAIT);
            records = iPage.getRecords();
        }
    }

    private void handle(String taskId, ClsMarketEtcAgentPromoteHistory entity) {
        if (entity.getAgent1Id() == null) {
            // ETC卡号对应的一级代理商信息不存在
            // 直接推广该用户的代理商一定是员工
            // 无需结算首次消费奖励
            log.info("条件1:ETC卡号对应的一级代理商信息不存在,直接推广该用户的代理商一定是员工,无需结算首次消费奖励:{},{}", taskId, entity);
            return;
        }
        // 获取直接推广该用户的代理商信息
        ClsMarketEtcAgent agent = agentDao.findOne(entity.getAgentId());
        if (agent.getRole() == AgentRole.STAFF) {
            // 直接推广该用户的代理商是员工
            // 无需结算首次消费奖励
            log.info("条件2:直接推广该用户的代理商是员工,无需结算首次消费奖励:{},{}", taskId, entity);
            return;
        }
        // 直接推广该用户的代理商是代理商
        // 获取全局配置信息
        Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap = globalSettingDao.findAll();
        // 计算一级代理首次消费分润奖励
        BigDecimal agent1Bonus = calculateAgentBonus(globalSettingMap, entity.getMethod(), entity.getAgent1Id());
        entity.setFirstConsumeBonus(agent1Bonus);
        // 设置首次消费奖励状态为已结算
        entity.setFirstConsumeBonusStatus(BonusStatus.SETTLED);
        // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            // 保存推广信息
            ClsMarketEtcAgentPromoteHistory data = agentPromoteHistoryDao.save(entity, false);
            if (data == null) {
                throw new RRException("保存推广信息失败", String.format("%s,%s", taskId, entity));
            }
            // 给一级代理商增加累计首次消费奖励
            if (agentDao.getMapper().increaseFirstConsumeBonus(entity.getAgent1Id(), agent1Bonus) != 1) {
                throw new RRException("给一级代理商增加累计首次消费奖励失败", String.format("%s,%s", taskId, entity));
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
     * 计算一级代理首次消费分润奖励
     */
    private BigDecimal calculateAgentBonus(Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> globalSettingMap,
                                           ActiveMethod activeMethod,
                                           Long agentForLv1Id) {
        if (globalSettingMap.size() == 0) {
            log.info("计算一级代理分润奖励异常,未配置分润信息:{}", agentForLv1Id);
            return BigDecimal.ZERO;
        }
        // 保存总奖励
        BigDecimal totalBonus = BigDecimal.ZERO;

        // ************ 用户首次消费后，代理商即可获得的奖励 ***********
        if (activeMethod == ActiveMethod.E) {
            // 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装
            BigDecimal bonus = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_ONLINE_FIRST_CONSUME_BONUS);
            totalBonus = totalBonus.add(bonus);
            log.info("首次消费记录查询:代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装,一级代理(ID={})可获得首次消费奖励 {} 元", agentForLv1Id, bonus);
        } else if (activeMethod == ActiveMethod.S) {
            // 代理商推介用户成功申办，并现场安装ETC套装
            BigDecimal bonus = calculateBonus(globalSettingMap, GlobalSettingKey.AGENT_OFFLINE_FIRST_CONSUME_BONUS);
            totalBonus = totalBonus.add(bonus);
            log.info("首次消费记录查询:代理商推介用户成功申办，并现场安装ETC套装,一级代理(ID={})可获得首次消费奖励 {} 元", agentForLv1Id, bonus);
        }

        log.info("首次消费记录查询:一级代理(ID={})可获得累计奖励 {} 元", agentForLv1Id, totalBonus);
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
