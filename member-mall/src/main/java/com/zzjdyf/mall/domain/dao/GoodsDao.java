package com.zzjdyf.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.zzjdyf.mall.config.GlobalConfig;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.mall.domain.dao.interfaces.BaseDao;
import com.zzjdyf.mall.domain.dao.interfaces.ICache;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcGoods;
import com.zzjdyf.mall.domain.mapper.GoodsMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * 代理商补货订单类型
 *
 * @author wangzhuo
 * Created on 20210721
 */
@Component
@AllArgsConstructor
public class GoodsDao implements BaseDao<ClsMarketEtcGoods>, ICache<ClsMarketEtcGoods> {

    @Getter
    private final GoodsMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcGoods.class);
    }

    @Override
    public ICache<ClsMarketEtcGoods> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcGoods> getEntityClass() {
        return ClsMarketEtcGoods.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcGoods entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }
}
