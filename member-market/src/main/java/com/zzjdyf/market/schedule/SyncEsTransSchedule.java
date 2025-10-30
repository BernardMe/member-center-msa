package com.zzjdyf.market.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.market.config.ScheduleCronConfig;
import com.zzjdyf.market.domain.entity.ClsMarketEtcEsTrade;
import com.zzjdyf.market.domain.mapper.EsTradeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务:同步ES交易记录
 *
 * @author 阿隆
 * Created on 2021/8/3 16:40.
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@AllArgsConstructor
public class SyncEsTransSchedule implements Runnable, SchedulingConfigurer {
    private final RedissonClient redissonClient;
    private final EsTradeMapper esTradeMapper;
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final ScheduleCronConfig scheduleCronConfig;
    private final ThreadPoolTaskScheduler syncEsTransExecutor;
    private static final String LOCK_NAME = "LOCK_SYNC_ES_TRANS";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(syncEsTransExecutor);
        scheduledTaskRegistrar.addTriggerTask(this, triggerContext -> new CronTrigger(scheduleCronConfig.getSyncEsTrans()).nextExecutionTime(triggerContext));
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString().replace("-", "");
        log.debug("定时任务:同步ES交易记录:" + id);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(LOCK_NAME);
        try {
            // 加锁
            rLock.lock(scheduleCronConfig.getSyncEsTransLockTime().getSeconds(), TimeUnit.SECONDS);
            // 加锁成功
            log.info("定时任务:同步ES交易记录(已获取锁)." + id);
            try {
                // 同步ES交易记录
                handle();
            } catch (Exception e) {
                log.error("定时任务:同步ES交易记录执行异常", e);
            }
            // 释放锁
            rLock.unlock();
            log.info("定时任务:同步ES交易记录(已释放锁)." + id);
        } catch (Exception e) {
            log.error("定时任务:同步ES交易记录执行异常", e);
        }
    }

    /**
     * 同步ES交易记录
     */
    private void handle() {
        // 1.分页查询交易明细ES同步记录
        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcEsTrade> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ClsMarketEtcEsTrade::getId);
        IPage<ClsMarketEtcEsTrade> iPage = esTradeMapper.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcEsTrade> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            // 2.循环查询每一笔交易记录
            for (ClsMarketEtcEsTrade trade : records) {
                // 当其中某一笔交易记录同步失败时,不能阻断后面的交易记录处理
                try {
                    IndexQuery indexQuery = new IndexQueryBuilder().withObject(trade).build();
                    elasticsearchTemplate.index(indexQuery);
                    esTradeMapper.deleteById(trade.getId());
                } catch (Exception e) {
                    log.error("同步ES交易记录异常", e);
                }
            }
            pageIndex++;
            iPage = esTradeMapper.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }
}
