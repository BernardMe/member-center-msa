package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentGoods;
import com.cheshun.market.domain.entity.enums.DepositStatus;
import com.cheshun.market.domain.mapper.AgentGoodsMapper;
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
public class AgentGoodsDao implements BaseDao<ClsMarketEtcAgentGoods>, ICache<ClsMarketEtcAgentGoods> {
    private final static String CACHE_KEY_PREFIX_AGENT = "agent";

    @Getter
    private final AgentGoodsMapper mapper;
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
    public ICache<ClsMarketEtcAgentGoods> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentGoods> getEntityClass() {
        return ClsMarketEtcAgentGoods.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentGoods entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        saveToCache(getCacheKeyPrefix(CACHE_KEY_PREFIX_AGENT) + entity.getAgentId(), findAllFromDbByAgentId(entity.getAgentId()));
    }

    /**
     * 根据代理商id查询数据并保存到缓存
     *
     * @param agentId 代理商id
     * @return 数据列表
     */
    public List<ClsMarketEtcAgentGoods> findAllByAgent(Long agentId) {
        String cacheKey = getCacheKeyPrefix(CACHE_KEY_PREFIX_AGENT) + agentId;
        List<Long> idList = readFromCache(cacheKey, new TypeReference<List<Long>>() {
        });
        if (idList == null) {
            idList = findAllFromDbByAgentId(agentId);
            saveToCache(cacheKey, idList);
        }
        List<ClsMarketEtcAgentGoods> list = new ArrayList<>(idList.size());
        for (Long id : idList) {
            Optional.ofNullable(findOne(id)).ifPresent(list::add);
        }
        return list;
    }

    private List<Long> findAllFromDbByAgentId(Long agentId) {
        LambdaQueryWrapper<ClsMarketEtcAgentGoods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcAgentGoods::getAgentId, agentId);
        // 过滤代理商品信息is_enable有效
        wrapper.eq(ClsMarketEtcAgentGoods::getIsEnabled, Boolean.TRUE);
        // 仅查询待缴纳押金和已缴纳押金的代理商品
        wrapper.in(ClsMarketEtcAgentGoods::getStatus, DepositStatus.WAIT_PAYMENT, DepositStatus.PAID, DepositStatus.WAIT_FILLED);///GLD
        return Optional.ofNullable(getMapper().selectList(wrapper))
                .orElse(new ArrayList<>(0))
                .stream()
                .map(ClsMarketEtcAgentGoods::getId)
                .collect(Collectors.toList());
    }
}
