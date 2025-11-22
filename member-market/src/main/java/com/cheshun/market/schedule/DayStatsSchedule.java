package com.cheshun.market.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cheshun.market.domain.dao.AgentPromoteHistoryDao;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.cheshun.market.domain.entity.ClsMarketEtcDayStats;
import com.cheshun.market.domain.entity.enums.AgentAuditStatus;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.BonusStatus;
import com.cheshun.market.config.ScheduleCronConfig;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.domain.dao.DayStatsDao;
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

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务:日统计
 *
 * @author 阿隆
 * Created on 2021/9/29 5:31 下午.
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@AllArgsConstructor
public class DayStatsSchedule implements Runnable, SchedulingConfigurer {
    private final RedissonClient redissonClient;
    private final AgentDao agentDao;
    private final DayStatsDao dayStatsDao;
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
    private final ScheduleCronConfig scheduleCronConfig;
    private final ThreadPoolTaskScheduler dayStatsExecutor;
    private static final String LOCK_NAME = "DAY_STATS";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(dayStatsExecutor);
        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getDayStats()).nextExecutionTime(triggerContext));
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString().replace("-", "");
        log.debug("定时任务:日统计:" + id);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME);
        try {
            // 加锁
            rLock.lock(scheduleCronConfig.getDayStatsLockTime().getSeconds(), TimeUnit.SECONDS);
            // 加锁成功
            log.info("定时任务:日统计(已获取锁)." + id);
            try {
                handle();
            } catch (Exception e) {
                log.error("定时任务:日统计执行异常", e);
            }
            // 释放锁
            rLock.unlock();
            log.info("定时任务:日统计(已释放锁)." + id);
        } catch (Exception e) {
            log.error("定时任务:日统计执行异常", e);
        }
    }

    /**
     * 处理订单
     */
    private void handle() {
        // 查询最后一次统计日期
        Date latestStatsDate = dayStatsDao.findLatestStatsDate();
        // 从2021-09-10日开始查询
        LocalDate statsDate = LocalDate.of(2021, 9, 10);
        if (latestStatsDate != null) {
            statsDate = latestStatsDate.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDate();
        }
        // 当前日期
        LocalDate today = LocalDate.now();
        while (today.toEpochDay() - statsDate.toEpochDay() > 0) {
            // 不统计今天的数据
            try {
                ClsMarketEtcDayStats dayStats = dayStatsDao.findOneByDate(Date.from(statsDate.atStartOfDay().toInstant(ZoneOffset.of("+8"))));
                if (dayStats == null) {
                    dayStats = new ClsMarketEtcDayStats();
                    dayStats.setStatsDate(Date.from(statsDate.atStartOfDay().toInstant(ZoneOffset.of("+8"))));

                    Date statsDateEndTime = Date.from(statsDate.atStartOfDay().plusDays(1).plusSeconds(-1).toInstant(ZoneOffset.of("+8")));

                    // 统计 statsDate 累计发卡量
                    LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper1 = new LambdaQueryWrapper<>();
                    queryWrapper1.isNotNull(ClsMarketEtcAgentPromoteHistory::getEtcAccount);
                    queryWrapper1.eq(ClsMarketEtcAgentPromoteHistory::getActiveBonusStatus, BonusStatus.SETTLED);
                    queryWrapper1.le(ClsMarketEtcAgentPromoteHistory::getActiveTime, statsDateEndTime);
                    dayStats.setTotalCardCount(Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper1)).orElse(0));

                    // 统计 statsDate 累计1级代理商数量
                    LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.APPLY_TERMINATE, AgentAuditStatus.TERMINATED);
                    queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_1);
                    queryWrapper.le(ClsMarketEtcAgent::getCreateTime, statsDateEndTime);
                    dayStats.setTotalAgent1Count(Optional.ofNullable(agentDao.getMapper().selectCount(queryWrapper)).orElse(0));

                    // 统计 statsDate 累计2级代理商数量
                    queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.APPLY_TERMINATE, AgentAuditStatus.TERMINATED);
                    queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_2);
                    queryWrapper.le(ClsMarketEtcAgent::getCreateTime, statsDateEndTime);
                    dayStats.setTotalAgent2Count(Optional.ofNullable(agentDao.getMapper().selectCount(queryWrapper)).orElse(0));

                    // 统计 statsDate 累计3级代理商数量
                    queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.APPLY_TERMINATE, AgentAuditStatus.TERMINATED);
                    queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_3);
                    queryWrapper.le(ClsMarketEtcAgent::getCreateTime, statsDateEndTime);
                    dayStats.setTotalAgent3Count(Optional.ofNullable(agentDao.getMapper().selectCount(queryWrapper)).orElse(0));

                    // 计算累计代理商数量
                    dayStats.setTotalAgentCount(dayStats.getTotalAgent1Count() + dayStats.getTotalAgent2Count() + dayStats.getTotalAgent3Count());

                    // 查询 statsDate-1 统计信息
                    ClsMarketEtcDayStats yesterdayStats = dayStatsDao.findOneByDate(Date.from(statsDate.plusDays(-1).atStartOfDay().toInstant(ZoneOffset.of("+8"))));
                    // 计算发卡量增量
                    dayStats.setIncreaseCardCount(dayStats.getTotalCardCount() - Optional.ofNullable(yesterdayStats).map(ClsMarketEtcDayStats::getTotalCardCount).orElse(0));
                    // 计算代理商数量增量
                    dayStats.setIncreaseAgentCount(dayStats.getTotalAgentCount() - Optional.ofNullable(yesterdayStats).map(ClsMarketEtcDayStats::getTotalAgentCount).orElse(0));

                    if (dayStats.getIncreaseAgentCount() > 0 || dayStats.getTotalAgentCount() > 0 || dayStats.getIncreaseCardCount() > 0 || dayStats.getTotalCardCount() > 0) {
                        dayStatsDao.save(dayStats);
                    }
                    log.info("日统计.统计日期:{}", dayStats);
                }

                // 继续统计下一天的数据
                statsDate = statsDate.plusDays(1);
            } catch (Exception e) {
                log.error("日统计执行异常.统计日期:" + statsDate, e);
                throw e;
            }
        }
    }
}
