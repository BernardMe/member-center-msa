//package com.zzjdyf.market.schedule;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.zzjdyf.market.etc.config.GlobalConfig;
//import com.zzjdyf.market.etc.config.ScheduleCronConfig;
//import com.zzjdyf.market.domain.dao.AgentPromoteHistoryDao;
//import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
//import com.zzjdyf.market.domain.entity.enums.BonusStatus;
//import com.zzjdyf.market.feign.GetwayFeignService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 定时任务:查询首次消费记录
// *
// * @author 阿隆
// * Created on 2021/8/16 9:05 上午.
// */
//@Slf4j
//@Component
//@EnableScheduling
//@EnableAsync
//@AllArgsConstructor
//public class FirstConsumeQuerySchedule implements Runnable, SchedulingConfigurer {
//    private final RedissonClient redissonClient;
//    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
//    private final ScheduleCronConfig scheduleCronConfig;
//    private final ThreadPoolTaskScheduler firstConsumeExecutor;
//    private final GetwayFeignService getwayFeignService;
//    private final GlobalConfig globalConfig;
//    private static final String LOCK_NAME = "FIRST_CONSUME_QUERY";
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.setTaskScheduler(firstConsumeExecutor);
//        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getFirstConsumeQuery()).nextExecutionTime(triggerContext));
//    }
//
//    @Override
//    public void run() {
//        String id = UUID.randomUUID().toString().replace("-", "");
//        log.debug("定时任务:查询首次消费记录." + id);
//        // 获取分布式锁
//        RLock rLock = redissonClient.getLock(LOCK_NAME);
//        try {
//            // 加锁
//            rLock.lock(scheduleCronConfig.getFirstConsumeQueryLockTime().getSeconds(), TimeUnit.SECONDS);
//            // 加锁成功
//            log.info("定时任务:查询首次消费记录(已获取锁)." + id);
//            try {
//                // 查询单笔订单信息
//                handle(id);
//            } catch (Exception e) {
//                log.error("定时任务:查询首次消费记录执行异常." + id, e);
//            }
//            // 释放锁
//            rLock.unlock();
//            log.info("定时任务:查询首次消费记录(已释放锁)." + id);
//        } catch (Exception e) {
//            log.error("定时任务:查询首次消费记录执行异常." + id, e);
//        }
//    }
//
//    /**
//     * 查询首次消费记录
//     */
//    public void handle(String taskId) {
//        // 分页查询可查询首次消费订单的推广记录列表
//        int pageIndex = 1;
//        int pageSize = 20;
//        IPage<ClsMarketEtcAgentPromoteHistory> iPage = agentPromoteHistoryDao.getMapper().queryCanQueryFirstConsumeList(
//                new Page<>(pageIndex, pageSize),
//                BonusStatus.SETTLED,
//                globalConfig.getStartQueryFirstConsumeAfterActive().toDays(),
//                globalConfig.getStopQueryFirstConsumeQueryAfterActive().toDays());
//        List<ClsMarketEtcAgentPromoteHistory> records = iPage.getRecords();
//        while (records != null && records.size() > 0) {
//            // 循环查询每一条代理商推广记录
//            AtomicInteger count = new AtomicInteger(0);
//            for (ClsMarketEtcAgentPromoteHistory entity : records) {
//                try {
//                    // 当其中某一条记录查询失败时,不能阻断后面记录的处理
//                    Map<String, Object> map = getwayFeignService.queryFirstConsume(entity.getEtcAccount());
//                    log.info("查询首次消费记录:{},{},{}", taskId, entity.getEtcAccount(), map);
//                    if (!map.containsKey("returnCode")) {
//                        continue;
//                    }
//                    // {"returnCode":"000500","returnMsg":"未查询到通行记录"}
//                    if ("000500".equals(map.get("returnCode"))) {
//                        // 未查询到数据,这种响应的数据量比较多,无需记录日志
//                        continue;
//                    }
//                    if (!"000000".equals(map.get("returnCode"))) {
//                        continue;
//                    }
//                    if (!map.containsKey("travelTime")) {
//                        continue;
//                    }
//                    // yyyyMMddHHmmss
//                    String travelTime = map.get("travelTime").toString().replaceAll("\\s", "");
//                    entity.setFirstConsumeTime(Date.from(LocalDateTime.parse(travelTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toInstant(ZoneOffset.of("+8"))));
//                    entity.setFirstConsumeOrderSn(map.get("travelId").toString());
//                    agentPromoteHistoryDao.save(entity);
//                    count.incrementAndGet();
//                } catch (Exception e) {
//                    log.error("查询首次消费记录异常:" + taskId, e);
//                }
//            }
//            if (count.get() == 0) {
//                pageIndex++;
//            }
//            iPage = agentPromoteHistoryDao.getMapper().queryCanQueryFirstConsumeList(
//                    new Page<>(pageIndex, pageSize),
//                    BonusStatus.SETTLED,
//                    globalConfig.getStartQueryFirstConsumeAfterActive().toDays(),
//                    globalConfig.getStopQueryFirstConsumeQueryAfterActive().toDays());
//            records = iPage.getRecords();
//        }
//    }
//}
