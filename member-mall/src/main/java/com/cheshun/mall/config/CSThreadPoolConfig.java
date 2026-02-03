package com.cheshun.mall.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步编排时 考虑丢失上下文问题 需要异步时共享上下文
 * RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
 * CompletableFuture<Void> itemAndStockFuture = CompletableFuture.supplyAsync(() -> {
 * RequestContextHolder.setRequestAttributes(requestAttributes);
 * },executor);
 *
 * @author wangzhuo
 * @date 20210905
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class CSThreadPoolConfig {
    @Bean("smsUnionExecutor")
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties properties) {
        return new ThreadPoolExecutor(
                properties.getCorePoolSize(),
                properties.getMaxPoolSize(),
                properties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(300000),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
