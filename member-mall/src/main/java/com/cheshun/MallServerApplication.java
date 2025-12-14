package com.cheshun;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wangzhuo
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableAsync
@EnableCaching//开发缓存注解功能
@EnableScheduling //开启任务调度
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = {"com.cheshun.mall.feign"})
@MapperScan({"com.cheshun.mall.domain.dao","com.cheshun.mall.domain.mapper"})
public class MallServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallServerApplication.class, args);
    }
}
