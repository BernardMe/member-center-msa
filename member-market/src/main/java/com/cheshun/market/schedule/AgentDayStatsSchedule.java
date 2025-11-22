package com.cheshun.market.schedule;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.dao.AgentDayStatsDao;
import com.cheshun.market.domain.dao.AgentPromoteHistoryDao;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentDayStats;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.config.ScheduleCronConfig;
import com.cheshun.market.domain.dao.AgentDao;
//import com.zzjdyf.market.feign.GetwayFeignService;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 定时任务:代理商日统计
 *
 * @author 阿隆
 * Created on 2021/9/29 5:31 下午.
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@AllArgsConstructor
public class AgentDayStatsSchedule implements Runnable, SchedulingConfigurer {
    private final RedissonClient redissonClient;
    private final AgentDao agentDao;
    private final AgentDayStatsDao agentDayStatsDao;
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
    //private final GetwayFeignService getwayFeignService;
    private final ScheduleCronConfig scheduleCronConfig;
    private final ThreadPoolTaskScheduler agentDayStatsExecutor;
    private static final String LOCK_NAME = "AGENT_DAY_STATS";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(agentDayStatsExecutor);
        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getDayStats()).nextExecutionTime(triggerContext));
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString().replace("-", "");
        log.debug("定时任务:代理商日统计:" + id);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME);
        try {
            // 加锁
            rLock.lock(scheduleCronConfig.getDayStatsLockTime().getSeconds(), TimeUnit.SECONDS);
            // 加锁成功
            log.info("定时任务:代理商日统计(已获取锁)." + id);
            try {
                handle();
            } catch (Exception e) {
                log.error("定时任务:代理商日统计执行异常", e);
            }
            // 释放锁
            rLock.unlock();
            log.info("定时任务:代理商日统计(已释放锁)." + id);
        } catch (Exception e) {
            log.error("定时任务:代理商日统计执行异常", e);
        }
    }

    /**
     * 处理订单
     */
    private void handle() {
        int pageIndex = 1;
        int pageSize = 100;
        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_1);
        queryWrapper.orderByAsc(ClsMarketEtcAgent::getId);
        IPage<ClsMarketEtcAgent> iPage = agentDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgent> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcAgent agent : records) {
                handle(agent);
            }
            pageIndex++;
            iPage = agentDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }

    private void handle(ClsMarketEtcAgent agent) {
        // 查询代理商最后一次统计日期
        Date latestStatsDate = agentDayStatsDao.findLatestStatsDate(agent.getId());
        // 从该代理商创建之日开始查询
        LocalDate statsDate = agent.getCreateTime().toInstant().atOffset(ZoneOffset.of("+8")).toLocalDate();
        if (latestStatsDate != null) {
            statsDate = latestStatsDate.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDate();
        }
        // 当前日期
        LocalDate today = LocalDate.now();
        while (today.toEpochDay() - statsDate.toEpochDay() > 0) {
            try {
                Date statsDateBeginTime = Date.from(statsDate.atStartOfDay().toInstant(ZoneOffset.of("+8")));
                Date statsDateEndTime = Date.from(statsDate.plusDays(1).atStartOfDay().plusSeconds(-1).toInstant(ZoneOffset.of("+8")));

                ClsMarketEtcAgentDayStats agentDayStats = agentDayStatsDao.findOneByAgentAndDate(agent.getId(), Date.from(statsDate.atStartOfDay().toInstant(ZoneOffset.of("+8"))));
                if (agentDayStats == null) {
                    agentDayStats = new ClsMarketEtcAgentDayStats();
                    agentDayStats.setAgentId(agent.getId());
                    agentDayStats.setStatsDate(statsDateBeginTime);

                    // 统计 statsDate 这一天的发卡量
                    LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.isNotNull(ClsMarketEtcAgentPromoteHistory::getEtcAccount);
                    queryWrapper.and(wrapper -> wrapper.eq(ClsMarketEtcAgentPromoteHistory::getAgentId, agent.getId())
                            .or()
                            .eq(ClsMarketEtcAgentPromoteHistory::getAgent1Id, agent.getId()));
                    // 仅查询statsDate 这一天的数据
                    queryWrapper.between(ClsMarketEtcAgentPromoteHistory::getCreateTime, statsDateBeginTime, statsDateEndTime);
                    agentDayStats.setCardCount(Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0));

                    // 查询通行笔数和交易金额
                    // 查询 statsDate 这一天及以前已推广的所有卡
                    int pageIndex = 1;
                    int pageSize = 100;
                    String statsDateStr = statsDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.isNotNull(ClsMarketEtcAgentPromoteHistory::getEtcAccount);
                    queryWrapper.and(wrapper -> wrapper.eq(ClsMarketEtcAgentPromoteHistory::getAgentId, agent.getId())
                            .or()
                            .eq(ClsMarketEtcAgentPromoteHistory::getAgent1Id, agent.getId()));
                    // 仅查询已产生首次消费的数据
                    queryWrapper.gt(ClsMarketEtcAgentPromoteHistory::getFirstConsumeBonus, 0);
                    IPage<ClsMarketEtcAgentPromoteHistory> page = agentPromoteHistoryDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
                    List<ClsMarketEtcAgentPromoteHistory> records = page.getRecords();
                    while (records != null && records.size() > 0) {
                        // etc账号列表
                        List<String> etcAccountList = records.stream().map(ClsMarketEtcAgentPromoteHistory::getEtcAccount).collect(Collectors.toList());
                        // 从 getway 查询指定日期和etc账号列表的通行笔数和交易金额
                        queryTravelStats(agentDayStats, etcAccountList, statsDateStr);

                        pageIndex++;
                        page = agentPromoteHistoryDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
                        records = page.getRecords();
                    }
                    // 如果getway返回通行通行笔数<=0, 发卡量<=0, 交易金额<=0 则代理商日统计信息未存储
                    if (agentDayStats.getCardCount() > 0 || agentDayStats.getPassageTimes() > 0 || agentDayStats.getPassageAmount().compareTo(BigDecimal.ZERO) > 0) {
                        agentDayStatsDao.save(agentDayStats);
                    }
                    log.info("代理商日统计.代理商:{}-{},{}", agent.getId(), agent.getRealName(), agentDayStats);
                }

                // 继续统计下一天的数据
                statsDate = statsDate.plusDays(1);
            } catch (Exception e) {
                log.error("代理商日统计执行异常.统计日期:" + statsDate, e);
                throw e;
            }
        }
    }

    private void queryTravelStats(ClsMarketEtcAgentDayStats agentDayStats, List<String> etcAccountList, String date) {
        Map<String, Object> body = new HashMap<>(2, 1);
        body.put("queryDate", date);
        body.put("etcNumber", etcAccountList);
        log.info("###@###: " + JSONObject.toJSONString(body));
        //Map<String, Object> map = getwayFeignService.queryTravelStats(body);
        Map<String, Object> map = null;
        log.info("###@@@###: " + JSONObject.toJSONString(map));
        log.info("查询代理商累计推广用户的通行笔数和交易金额.{}-{},{},{}", agentDayStats.getAgentId(), date, JSONUtil.toJsonStr(etcAccountList), JSONUtil.toJsonStr(map));
        if (map == null || map.size() == 0 || !map.containsKey("ResultList")) {
            return;
        }
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) map.get("ResultList");
        if (resultList == null || resultList.isEmpty()) {
            return;
        }
        resultList.forEach(t -> {
            // 增加通行次数
            agentDayStats.setPassageTimes(agentDayStats.getPassageTimes() + Integer.parseInt(t.get("travelCount").toString()));
            // 增加通行费用
            agentDayStats.setPassageAmount(agentDayStats.getPassageAmount().add(new BigDecimal(t.get("travelAmt").toString())));
        });
    }
}
