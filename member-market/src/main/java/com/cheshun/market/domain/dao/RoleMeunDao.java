package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcRoleMeun;
import com.cheshun.market.domain.mapper.RoleMeunMapper;
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
public class RoleMeunDao implements BaseDao<ClsMarketEtcRoleMeun>, ICache<ClsMarketEtcRoleMeun> {
    @Getter
    private final RoleMeunMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcRoleMeun.class);
    }

    @Override
    public ICache<ClsMarketEtcRoleMeun> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcRoleMeun> getEntityClass() {
        return ClsMarketEtcRoleMeun.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcRoleMeun entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    public void deleteByRoleMeunId(Long roleId, Long meunId) {
        mapper.deleteByRoleMeunId(roleId,meunId);
    }

    public ClsMarketEtcRoleMeun findOneByRoleMeunId(ClsMarketEtcRoleMeun clsMarketEtcRoleMeun){
        return mapper.findOneByRoleMeunId(clsMarketEtcRoleMeun);
    }

    public List<ClsMarketEtcRoleMeun> findListByRoleId(Long roleId,Integer leaf){

        return mapper.findListByRoleId(roleId,leaf);
    }


}
