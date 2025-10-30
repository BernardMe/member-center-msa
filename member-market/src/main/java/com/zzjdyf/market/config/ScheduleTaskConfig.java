package com.zzjdyf.market.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定时任务线程池配置
 *
 * @author 阿隆
 * Created on 2021/8/3 16:40.
 */
@Configuration
@Data
public class ScheduleTaskConfig {
    @Bean
    public ThreadPoolTaskScheduler withdrawOrderQueryExecutor() {
        return createExecutor("withdrawOrderQuery");
    }

    @Bean
    public ThreadPoolTaskScheduler syncEsTransExecutor() {
        return createExecutor("syncEsTrans");
    }

    @Bean
    public ThreadPoolTaskScheduler firstConsumeExecutor() {
        return createExecutor("firstConsume");
    }

    @Bean
    public ThreadPoolTaskScheduler activeCardQueryExecutor() {
        return createExecutor("activeCardQuery");
    }

    @Bean
    public ThreadPoolTaskScheduler settleActiveBonusExecutor() {
        return createExecutor("settleActiveBonus");
    }

    @Bean
    public ThreadPoolTaskScheduler settleFirstConsumeBonusExecutor() {
        return createExecutor("settleFirstConsumeBonus");
    }

    @Bean
    public ThreadPoolTaskScheduler dayStatsExecutor() {
        return createExecutor("dayStats");
    }

    @Bean
    public ThreadPoolTaskScheduler agentDayStatsExecutor() {
        return createExecutor("agentDayStats");
    }

    /**
     * 创建线程池
     *
     * @param name 线程池名称
     * @return 线程池 {@link ThreadPoolTaskScheduler}
     */
    private ThreadPoolTaskScheduler createExecutor(String name) {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setThreadNamePrefix(name + "-schedule-");
        executor.setPoolSize(1);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRemoveOnCancelPolicy(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return executor;
    }
}
