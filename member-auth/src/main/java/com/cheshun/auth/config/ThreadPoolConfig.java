package com.cheshun.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {
    @Bean("gainGroupId")  // 指定名称，按业务隔离
    public ThreadPoolTaskExecutor gainGroupId() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(80);
        executor.setThreadNamePrefix("getgroupid-task-");
        executor.setKeepAliveSeconds(30); // 非核心线程空闲60秒后回收
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 由调用线程处理被拒任务
        executor.setWaitForTasksToCompleteOnShutdown(true); // 关闭时等待任务完成
        executor.setAwaitTerminationSeconds(60); // 最多等待60秒
        executor.initialize();
        return executor;
    }

    @Bean("aiTaskExecutor")  // 指定名称，按业务隔离
    public ThreadPoolTaskExecutor aiTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("ai-task-");  // 线程命名，便于监控
        executor.setKeepAliveSeconds(30); // 非核心线程空闲60秒后回收
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 由调用线程处理被拒任务
        executor.setWaitForTasksToCompleteOnShutdown(true); // 关闭时等待任务完成
        executor.setAwaitTerminationSeconds(60); // 最多等待60秒
        executor.initialize();
        return executor;
    }

    @Bean("ActivityTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("activity-async-");
        executor.initialize();
        return executor;
    }
}