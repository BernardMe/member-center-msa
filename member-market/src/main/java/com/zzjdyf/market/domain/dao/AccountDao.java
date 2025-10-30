package com.zzjdyf.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.market.config.GlobalConfig;
import com.zzjdyf.market.domain.dao.interfaces.BaseDao;
import com.zzjdyf.market.domain.dao.interfaces.ICache;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAccount;
import com.zzjdyf.market.domain.entity.enums.AccountStatus;
import com.zzjdyf.market.domain.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;


@Component
@AllArgsConstructor
public class AccountDao implements BaseDao<ClsMarketEtcAccount>, ICache<ClsMarketEtcAccount> {
    @Getter
    private final AccountMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAccount.class);
    }

    @Override
    public ICache<ClsMarketEtcAccount> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAccount> getEntityClass() {
        return ClsMarketEtcAccount.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAccount entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    public ClsMarketEtcAccount findOneByPhone (String phone){

        return mapper.findOneByPhone(phone, AccountStatus.Enable.getValue());
    }

}
