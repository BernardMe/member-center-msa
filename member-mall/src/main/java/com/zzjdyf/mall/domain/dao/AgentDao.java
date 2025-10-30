package com.zzjdyf.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.mall.config.GlobalConfig;
import com.zzjdyf.mall.domain.dao.interfaces.BaseDao;
import com.zzjdyf.mall.domain.dao.interfaces.ICache;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.mall.domain.mapper.AgentMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Optional;

/**
 * @author 阿隆
 * Created on 2021/7/29 10:38 上午.
 */
@Component
@AllArgsConstructor
public class AgentDao implements BaseDao<ClsMarketEtcAgent>, ICache<ClsMarketEtcAgent> {
    private final static String CACHE_KEY_PREFIX_PHONE = "phone";

    @Getter
    private final AgentMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgent.class);
    }

    @Override
    public ICache<ClsMarketEtcAgent> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgent> getEntityClass() {
        return ClsMarketEtcAgent.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgent entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        saveToCache(getCacheKeyPrefix(CACHE_KEY_PREFIX_PHONE) + entity.getPhone(), entity.getId());
    }

    /**
     * 根据手机号查询单条数据并保存到缓存
     *
     * @param phone 手机号
     * @return 单条数据
     */
    public ClsMarketEtcAgent findOneByPhone(String phone) {
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_PHONE) + phone;
        Long id = null;
        ClsMarketEtcAgent entity;
        if (id == null || id == 0) {
            LambdaQueryWrapper<ClsMarketEtcAgent> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ClsMarketEtcAgent::getPhone, phone);
            entity = getMapper().selectOne(wrapper);
           /* if (entity != null) {
                saveToCache(entity);
            } else {
                saveToCache(cacheKey, 0);
            }*/
        } else {
            entity = findOne(id);
        }
        return Optional.ofNullable(entity).filter(t -> t.getId() != null).orElse(null);
    }

    /**
     * 根据openId查询数据
     */
    public ClsMarketEtcAgent findOneByOpenId(String openId){
        LambdaQueryWrapper<ClsMarketEtcAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcAgent::getOpenId, openId);
        ClsMarketEtcAgent  entity = getMapper().selectOne(wrapper);
        return Optional.ofNullable(entity).filter(t -> t.getId() != null).orElse(null);
    }



}
