package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcRole;
import com.cheshun.market.domain.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;


@Component
@AllArgsConstructor
public class RoleDao implements BaseDao<ClsMarketEtcRole>, ICache<ClsMarketEtcRole> {
    @Getter
    private final RoleMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcRole.class);
    }

    @Override
    public ICache<ClsMarketEtcRole> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcRole> getEntityClass() {
        return ClsMarketEtcRole.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcRole entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    public void update(ClsMarketEtcRole entity){

        mapper.updateById(entity);
    }
}
