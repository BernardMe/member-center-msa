package com.zzjdyf.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.zzjdyf.mall.config.GlobalConfig;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.mall.domain.dao.interfaces.BaseDao;
import com.zzjdyf.mall.domain.dao.interfaces.ICache;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcMeun;
import com.zzjdyf.mall.domain.mapper.MeunMapper;
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
public class MeunDao implements BaseDao<ClsMarketEtcMeun>, ICache<ClsMarketEtcMeun> {
    @Getter
    private final MeunMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcMeun.class);
    }

    @Override
    public ICache<ClsMarketEtcMeun> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcMeun> getEntityClass() {
        return ClsMarketEtcMeun.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcMeun entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }
    public List<ClsMarketEtcMeun> findList(Long roleId){


        return mapper.findList(roleId);
    }

    public List<ClsMarketEtcMeun> queryListByLeaf(Integer leaf){
        return mapper.queryListByLeaf(leaf);
    }

    public ClsMarketEtcMeun queryByMeunIdId (Long meunId){
        return mapper.queryByMeunIdId(meunId);
    }


}
