package com.zzjdyf.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.mall.config.GlobalConfig;
import com.zzjdyf.mall.domain.dao.interfaces.BaseDao;
import com.zzjdyf.mall.domain.dao.interfaces.ICache;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgentDayStats;
import com.zzjdyf.mall.domain.mapper.AgentDayStatsMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
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
public class AgentDayStatsDao implements BaseDao<ClsMarketEtcAgentDayStats>, ICache<ClsMarketEtcAgentDayStats> {
    private final static String CACHE_KEY_PREFIX_DATE_AGENT = "date_%s:agent_%d";

    @Getter
    private final AgentDayStatsMapper mapper;
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
    public ICache<ClsMarketEtcAgentDayStats> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentDayStats> getEntityClass() {
        return ClsMarketEtcAgentDayStats.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentDayStats entity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String statsDate = simpleDateFormat.format(entity.getStatsDate());
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_DATE_AGENT, statsDate, entity.getAgentId());
        saveToCache(cacheKey, entity);
    }

    @Override
    public void deleteFromCache(ClsMarketEtcAgentDayStats entity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String statsDate = simpleDateFormat.format(entity.getStatsDate());
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_DATE_AGENT, statsDate, entity.getAgentId());
        deleteFromCache(cacheKey);
    }

    /**
     * 查询代理商最后一次统计日期
     *
     * @param agentId 代理商id
     * @return 最后一次统计日期
     */
    public Date findLatestStatsDate(Long agentId) {
        LambdaQueryWrapper<ClsMarketEtcAgentDayStats> queryWrapper = new QueryWrapper<ClsMarketEtcAgentDayStats>()
                .select("max(stats_date) as statsDate")
                .lambda();
        queryWrapper.eq(ClsMarketEtcAgentDayStats::getAgentId, agentId);
        ClsMarketEtcAgentDayStats data = mapper.selectOne(queryWrapper);
        if (data == null) {
            return null;
        }
        return data.getStatsDate();
    }

    /**
     * 根据日期查询数据
     *
     * @param agentId 代理商id
     * @param date    日期
     * @return 指定日期的统计信息
     */
    public ClsMarketEtcAgentDayStats findOneByAgentAndDate(Long agentId, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String statsDate = simpleDateFormat.format(date);
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_DATE_AGENT, statsDate, agentId);
        ClsMarketEtcAgentDayStats data = readFromCache(cacheKey, ClsMarketEtcAgentDayStats.class);
        if (data != null) {
            if (data.getId() == null) {
                return null;
            }
            return data;
        }
        LambdaQueryWrapper<ClsMarketEtcAgentDayStats> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentDayStats::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentDayStats::getStatsDate, date);
        data = mapper.selectOne(queryWrapper);
        if (data == null) {
            data = new ClsMarketEtcAgentDayStats();
            data.setAgentId(agentId);
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
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 指定日期范围的统计信息
     */
    public List<ClsMarketEtcAgentDayStats> findAllByDateRange(Date fromDate, Date toDate, int pageNum, int pageSize) {
        LambdaQueryWrapper<ClsMarketEtcAgentDayStats> queryWrapper = new QueryWrapper<ClsMarketEtcAgentDayStats>()
                .select("agent_id, sum(card_count) as card_count, sum(passage_times) as passage_times, sum(passage_amount) as passage_amount, stats_date")
                .lambda();
        queryWrapper.between(ClsMarketEtcAgentDayStats::getStatsDate, fromDate, toDate);
        queryWrapper.groupBy(ClsMarketEtcAgentDayStats::getAgentId);
        queryWrapper.orderByDesc(ClsMarketEtcAgentDayStats::getCardCount);
        IPage<ClsMarketEtcAgentDayStats> page = new Page<>(pageNum, pageSize);
        page = selectPage(page, queryWrapper);
        if (page.getRecords() == null || page.getRecords().size() == 0) {
            return new ArrayList<>(0);
        }
        return page.getRecords();
    }

    /**
     * 清理所有数据
     */
    public void deleteAll() {
        int pageIndex = 1;
        int pageSize = 100;
        LambdaQueryWrapper<ClsMarketEtcAgentDayStats> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ClsMarketEtcAgentDayStats::getId);
        IPage<ClsMarketEtcAgentDayStats> iPage = selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgentDayStats> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcAgentDayStats stats : records) {
                delete(stats);
            }
            pageIndex++;
            iPage = selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
        deleteFromCache(getEntityClass().getSimpleName() + "*", true);
    }
}
