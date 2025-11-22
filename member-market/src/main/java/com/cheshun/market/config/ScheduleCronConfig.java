package com.cheshun.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * 定时任务Cron表达式配置
 *
 * @author 阿隆
 * Created on 2021/8/3 16:40.
 */
@Configuration
@ConfigurationProperties(prefix = "schedule.cron")
@Data
public class ScheduleCronConfig {
    /**
     * 定时任务:查询提现订单信息
     */
    private String withdrawOrderQuery;
    /**
     * 定时任务:查询提现订单信息(锁占用时长)
     */
    private Duration withdrawOrderQueryLockTime;
    /**
     * 定时任务:同步ES交易记录
     */
    private String syncEsTrans;
    /**
     * 定时任务:同步ES交易记录(锁占用时长)
     */
    private Duration syncEsTransLockTime;
    /**
     * 定时任务:查询首次消费信息
     */
    private String firstConsumeQuery;
    /**
     * 定时任务:查询首次消费信息(锁占用时长)
     */
    private Duration firstConsumeQueryLockTime;
    /**
     * 定时任务:从发行方查询激活的ETC卡号信息
     */
    private String activeCardQuery;
    /**
     * 定时任务:从发行方查询激活的ETC卡号信息(锁占用时长)
     */
    private Duration activeCardQueryLockTime;
    /**
     * 定时任务:结算ETC卡激活奖励
     */
    private String settleActiveBonus;
    /**
     * 定时任务:结算ETC卡激活奖励(锁占用时长)
     */
    private Duration settleActiveBonusLockTime;
    /**
     * 定时任务:结算ETC卡首次消费奖励
     */
    private String settleFirstConsumeBonus;
    /**
     * 定时任务:结算ETC卡首次消费奖励(锁占用时长)
     */
    private Duration settleFirstConsumeBonusLockTime;
    /**
     * 定时任务:日统计
     */
    private String dayStats;
    /**
     * 定时任务:日统计(锁占用时长)
     */
    private Duration dayStatsLockTime;
    /**
     * 定时任务:代理商日统计
     */
    private String agentDayStats;
    /**
     * 定时任务:代理商日统计(锁占用时长)
     */
    private Duration agentDayStatsLockTime;
}
