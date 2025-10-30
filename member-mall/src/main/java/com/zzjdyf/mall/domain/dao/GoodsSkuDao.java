package com.zzjdyf.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzjdyf.mall.config.GlobalConfig;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zzjdyf.mall.domain.dao.interfaces.BaseDao;
import com.zzjdyf.mall.domain.dao.interfaces.ICache;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcGoodsSku;
import com.zzjdyf.mall.domain.entity.enums.PublishStatus;
import com.zzjdyf.mall.domain.mapper.GoodsSkuMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 代理商补货订单类型
 *
 * @author wangzhuo
 * Created on 20210721
 */
@Component
@AllArgsConstructor
public class GoodsSkuDao implements BaseDao<ClsMarketEtcGoodsSku>, ICache<ClsMarketEtcGoodsSku> {
    private final static String CACHE_KEY_PREFIX_GOODS = "goods";

    @Getter
    private final GoodsSkuMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcGoodsSku.class);
    }

    @Override
    public ICache<ClsMarketEtcGoodsSku> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcGoodsSku> getEntityClass() {
        return ClsMarketEtcGoodsSku.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcGoodsSku entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_GOODS) + entity.getGoodsId();
        List<Long> idList = readFromCache(cacheKey, new TypeReference<List<Long>>() {
        });
        if (idList != null) {
            if (!idList.contains(entity.getId())) {
                idList.add(entity.getId());
                saveToCache(cacheKey, idList);
            }
        } else {
            idList = findAllFromDbByGoodsId(entity.getGoodsId());
            saveToCache(cacheKey, idList);
        }
    }

    /**
     * 根据商品id查询数据并保存到缓存
     *
     * @param goodsId 商品id
     * @return 数据列表
     */
    public List<ClsMarketEtcGoodsSku> findAllByGoods(Long goodsId) {
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_GOODS) + goodsId;
        List<Long> idList = readFromCache(cacheKey, new TypeReference<List<Long>>() {
        });
        if (idList == null) {
            idList = findAllFromDbByGoodsId(goodsId);
            saveToCache(cacheKey, idList);
        }
        List<ClsMarketEtcGoodsSku> list = new ArrayList<>(idList.size());
        for (Long id : idList) {
            Optional.ofNullable(findOne(id)).ifPresent(list::add);
        }
        return list;
    }

    private List<Long> findAllFromDbByGoodsId(Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcGoodsSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcGoodsSku::getGoodsId, goodsId);
        wrapper.eq(ClsMarketEtcGoodsSku::getStatus, PublishStatus.ADDED);
        return Optional.ofNullable(getMapper().selectList(wrapper))
                .orElse(new ArrayList<>(0))
                .stream()
                .map(ClsMarketEtcGoodsSku::getId)
                .collect(Collectors.toList());
    }
}
