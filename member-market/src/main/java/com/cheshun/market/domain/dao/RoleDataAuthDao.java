package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcRoleDataAuth;
import com.cheshun.market.domain.mapper.RoleDataAuthMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;


@Component
@AllArgsConstructor
public class RoleDataAuthDao implements BaseDao<ClsMarketEtcRoleDataAuth>, ICache<ClsMarketEtcRoleDataAuth> {
    @Getter
    private final RoleDataAuthMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcRoleDataAuth.class);
    }

    @Override
    public ICache<ClsMarketEtcRoleDataAuth> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcRoleDataAuth> getEntityClass() {
        return ClsMarketEtcRoleDataAuth.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcRoleDataAuth entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    public void updateByRoleId(ClsMarketEtcRoleDataAuth clsMarketEtcRoleDataAuth){
        mapper.updateByRoleId(clsMarketEtcRoleDataAuth);
    }

    public ClsMarketEtcRoleDataAuth queryByRoleId(Long roleId){
       return mapper.queryByRoleId(roleId);
    }

}
