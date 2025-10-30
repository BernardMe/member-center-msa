package com.zzjdyf.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * 全局配置
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {
    /**
     * redis缓存保存时长
     */
    private Duration redisCacheTtl = Duration.ofDays(7);
    /**
     * 激活xx时间后开始查询首次消费
     */
    private Duration startQueryFirstConsumeAfterActive = Duration.ofDays(1);
    /**
     * 激活xx时间后停止查询首次消费
     */
    private Duration stopQueryFirstConsumeQueryAfterActive = Duration.ofDays(30);
    /**
     * 每个用户每天可提现最大次数
     */
    private int withdrawMaxTimesOfDay = 5;
    /**
     * 每个用户每天可提现累计金额
     */
    private int withdrawSumAmountOfDay = 2000;
}
