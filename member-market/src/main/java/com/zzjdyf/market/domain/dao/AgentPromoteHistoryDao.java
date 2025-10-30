package com.zzjdyf.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.market.config.GlobalConfig;
import com.zzjdyf.market.domain.dao.interfaces.BaseDao;
import com.zzjdyf.market.domain.dao.interfaces.ICache;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.zzjdyf.market.domain.mapper.AgentPromoteHistoryMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author 阿隆
 * Created on 2021/8/11 10:10 上午.
 */
@Component
@AllArgsConstructor
public class AgentPromoteHistoryDao implements BaseDao<ClsMarketEtcAgentPromoteHistory>, ICache<ClsMarketEtcAgentPromoteHistory> {
    @Getter
    private final AgentPromoteHistoryMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentPromoteHistory.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentPromoteHistory> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentPromoteHistory> getEntityClass() {
        return ClsMarketEtcAgentPromoteHistory.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentPromoteHistory entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }
}
