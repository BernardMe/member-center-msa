package com.cheshun.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

/**
 * @author 阿隆
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cheshun.market.feign"})
public class MainApplication {
    public static void main(String[] args) {
        // 在main方法里插入下面的代码以解决es的netty冲突问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(MainApplication.class, args);
    }
}
