package com.zzjdyf.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzjdyf.mall.config.GlobalConfig;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zzjdyf.mall.domain.dao.interfaces.BaseDao;
import com.zzjdyf.mall.domain.dao.interfaces.ICache;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgentBankCard;
import com.zzjdyf.mall.domain.mapper.AgentBankCardMapper;
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
 * @author 阿隆
 * Created on 2021/8/2 3:44 下午.
 */
@Component
@AllArgsConstructor
public class AgentBankCardDao implements BaseDao<ClsMarketEtcAgentBankCard>, ICache<ClsMarketEtcAgentBankCard> {
    private final static String CACHE_KEY_PREFIX_AGENT = "agent";

    @Getter
    private final AgentBankCardMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentBankCard.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentBankCard> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentBankCard> getEntityClass() {
        return ClsMarketEtcAgentBankCard.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentBankCard entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        saveToCache(getCacheKeyPrefix(CACHE_KEY_PREFIX_AGENT) + entity.getAgentId(), findAllFromDbByAgentId(entity.getAgentId()));
    }

    /**
     * 根据代理id查询数据并保存到缓存
     *
     * @param agentId 代理id
     * @return 数据列表
     */
    public List<ClsMarketEtcAgentBankCard> findAllByAgent(Long agentId) {
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_AGENT) + agentId;
        List<Long> idList = readFromCache(cacheKey, new TypeReference<List<Long>>() {
        });
        if (idList == null) {
            idList = findAllFromDbByAgentId(agentId);
            saveToCache(cacheKey, idList);
        }
        List<ClsMarketEtcAgentBankCard> list = new ArrayList<>(idList.size());
        for (Long id : idList) {
            Optional.ofNullable(findOne(id)).ifPresent(list::add);
        }
        return list;
    }

    private List<Long> findAllFromDbByAgentId(Long agentId) {
        LambdaQueryWrapper<ClsMarketEtcAgentBankCard> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcAgentBankCard::getAgentId, agentId);
        return Optional.ofNullable(getMapper().selectList(wrapper))
                .orElse(new ArrayList<>(0))
                .stream()
                .map(ClsMarketEtcAgentBankCard::getId)
                .collect(Collectors.toList());
    }
}