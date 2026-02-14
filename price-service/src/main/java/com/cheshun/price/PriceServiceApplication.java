package com.cheshun.price;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 比价服务启动类
 */
@SpringBootApplication
@MapperScan("com.cheshun.price.mapper")
//@EnableScheduling  // 启用定时任务（用于爬虫定时执行）
public class PriceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceServiceApplication.class, args);
    }
}