package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentGoods;
import com.cheshun.market.domain.entity.ClsMarketEtcGlobalSetting;
import com.cheshun.market.domain.entity.enums.GlobalSettingKey;
import com.cheshun.market.domain.mapper.GlobalSettingMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/8/12 15:18.
 */
@Component
@AllArgsConstructor
public class GlobalSettingDao implements BaseDao<ClsMarketEtcGlobalSetting>, ICache<ClsMarketEtcGlobalSetting> {
    private final static String CACHE_KEY_PREFIX_ALL = "all";

    @Getter
    private final GlobalSettingMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentGoods.class);
    }

    @Override
    public ICache<ClsMarketEtcGlobalSetting> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcGlobalSetting> getEntityClass() {
        return ClsMarketEtcGlobalSetting.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcGlobalSetting entity) {
        saveToCache(getCacheKeyPrefix(null) + CACHE_KEY_PREFIX_ALL, findAll());
    }

    /**
     * 查询所有数据并保存到缓存
     *
     * @return 数据
     */
    public Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> findAll() {
        String cacheKey = getCacheKeyPrefix(null) + CACHE_KEY_PREFIX_ALL;
        List<ClsMarketEtcGlobalSetting> list = readFromCache(cacheKey, new TypeReference<List<ClsMarketEtcGlobalSetting>>() {
        });
        if (list == null) {
            LambdaQueryWrapper<ClsMarketEtcGlobalSetting> wrapper = new LambdaQueryWrapper<>();
            list = Optional.ofNullable(getMapper().selectList(wrapper)).orElse(new ArrayList<>(0));
            saveToCache(cacheKey, list);
        }
        return list.stream().collect(Collectors.toMap(t -> GlobalSettingKey.valueOf(t.getName()), Function.identity()));
    }
}
