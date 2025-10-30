package com.zzjdyf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangzhuo
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.zzjdyf.mall.feign"})
public class MallServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallServerApplication.class, args);
    }
}
