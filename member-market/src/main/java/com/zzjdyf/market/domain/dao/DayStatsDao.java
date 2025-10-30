package com.zzjdyf.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.market.config.GlobalConfig;
import com.zzjdyf.market.domain.dao.interfaces.BaseDao;
import com.zzjdyf.market.domain.dao.interfaces.ICache;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentDayStats;
import com.zzjdyf.market.domain.entity.ClsMarketEtcDayStats;
import com.zzjdyf.market.domain.mapper.DayStatsMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 代理商补货订单类型
 *
 * @author wangzhuo
 * Created on 20210721
 */
@Component
@AllArgsConstructor
public class DayStatsDao implements BaseDao<ClsMarketEtcDayStats>, ICache<ClsMarketEtcDayStats> {
    private final static String CACHE_KEY_PREFIX_DATE = "date_%s";

    @Getter
    private final DayStatsMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentDayStats.class);
    }

    @Override
    public ICache<ClsMarketEtcDayStats> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcDayStats> getEntityClass() {
        return ClsMarketEtcDayStats.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcDayStats entity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String statsDate = simpleDateFormat.format(entity.getStatsDate());
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_DATE, statsDate);
        saveToCache(cacheKey, entity.getId());
    }

    @Override
    public void deleteFromCache(ClsMarketEtcDayStats entity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String statsDate = simpleDateFormat.format(entity.getStatsDate());
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_DATE, statsDate);
        deleteFromCache(cacheKey);
    }

    /**
     * 查询最后一次统计日期
     *
     * @return 最后一次统计日期
     */
    public Date findLatestStatsDate() {
        LambdaQueryWrapper<ClsMarketEtcDayStats> queryWrapper = new QueryWrapper<ClsMarketEtcDayStats>()
                .select("max(stats_date) as statsDate")
                .lambda();
        ClsMarketEtcDayStats data = mapper.selectOne(queryWrapper);
        if (data == null) {
            return null;
        }
        return data.getStatsDate();
    }

    /**
     * 根据日期查询数据
     *
     * @param date 日期
     * @return 指定日期的统计信息
     */
    public ClsMarketEtcDayStats findOneByDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String statsDate = simpleDateFormat.format(date);
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_DATE, statsDate);
        ClsMarketEtcDayStats data = readFromCache(cacheKey, ClsMarketEtcDayStats.class);
        if (data != null) {
            if (data.getId() == null) {
                return null;
            }
            return data;
        }
        LambdaQueryWrapper<ClsMarketEtcDayStats> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcDayStats::getStatsDate, date);
        data = mapper.selectOne(queryWrapper);
        if (data == null) {
            data = new ClsMarketEtcDayStats();
            data.setStatsDate(date);
        }
        saveToCache(data);
        return Optional.of(data).filter(t -> t.getId() != null).orElse(null);
    }

    /**
     * 根据日期范围查询数据
     *
     * @param fromDate 开始日期
     * @param toDate   结束日期
     * @return 指定日期范围的统计信息
     */
    public ClsMarketEtcDayStats findAllByDateRange(Date fromDate, Date toDate) {
        LambdaQueryWrapper<ClsMarketEtcDayStats> queryWrapper = new QueryWrapper<ClsMarketEtcDayStats>()
                .select("ifnull(sum(increase_agent_count), 0) as increaseAgentCount, " +
                        "ifnull(max(total_agent_count), 0) as totalAgentCount, " +
                        "ifnull(max(total_agent1_count), 0) as totalAgent1Count, " +
                        "ifnull(max(total_agent2_count), 0) as totalAgent2Count, " +
                        "ifnull(max(total_agent3_count), 0) as totalAgent3Count, " +
                        "ifnull(sum(increase_card_count), 0) as increaseCardCount, " +
                        "ifnull(max(total_card_count), 0) as totalCardCount, " +
                        "max(stats_date) as statsDate")
                .lambda();
        queryWrapper.between(ClsMarketEtcDayStats::getStatsDate, fromDate, toDate);
        return getMapper().selectOne(queryWrapper);
    }

    /**
     * 清理所有数据
     */
    public void deleteAll() {
        int pageIndex = 1;
        int pageSize = 100;
        LambdaQueryWrapper<ClsMarketEtcDayStats> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ClsMarketEtcDayStats::getId);
        IPage<ClsMarketEtcDayStats> iPage = selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcDayStats> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcDayStats stats : records) {
                delete(stats);
            }
            pageIndex++;
            iPage = selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
        deleteFromCache(getEntityClass().getSimpleName() + "*", true);
    }
}
