package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcMeunMo;
import com.cheshun.market.domain.mapper.MeunMoMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;


@Component
@AllArgsConstructor
public class MeunMoDao implements BaseDao<ClsMarketEtcMeunMo>, ICache<ClsMarketEtcMeunMo> {
    @Getter
    private final MeunMoMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcMeunMo.class);
    }

    @Override
    public ICache<ClsMarketEtcMeunMo> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcMeunMo> getEntityClass() {
        return ClsMarketEtcMeunMo.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcMeunMo entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }
    public List<ClsMarketEtcMeunMo> findList(){


        return null;
    }

}
