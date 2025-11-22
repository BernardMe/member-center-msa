package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcRoleMo;
import com.cheshun.market.domain.mapper.RoleMoMapper;
import com.cheshun.market.vo.dto.RoleMoAdminDto;
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
public class RoleMoDao implements BaseDao<ClsMarketEtcRoleMo>, ICache<ClsMarketEtcRoleMo> {
    @Getter
    private final RoleMoMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcRoleMo.class);
    }

    @Override
    public ICache<ClsMarketEtcRoleMo> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcRoleMo> getEntityClass() {
        return ClsMarketEtcRoleMo.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcRoleMo entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    public void deleteByMeunId(ClsMarketEtcRoleMo clsMarketEtcRoleMo){
        mapper.deleteByMeunId(clsMarketEtcRoleMo);
    }

    public void insert(ClsMarketEtcRoleMo entity){
        mapper.insert(entity);
    }



    public List<RoleMoAdminDto> findListByRoleAndMenu(Long meunId, Long roleId){
        return mapper.findListByRoleAndMenu(meunId,roleId);
    }

}
