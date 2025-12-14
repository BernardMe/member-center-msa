package com.cheshun.mall.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.cheshun.mall.config.GlobalConfig;
import com.cheshun.mall.domain.dao.interfaces.BaseDao;
import com.cheshun.mall.domain.dao.interfaces.ICache;
import com.cheshun.mall.domain.dao.interfaces.IElasticSearch;
import com.cheshun.mall.domain.mapper.EsTradeMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.mall.domain.entity.ClsMarketEtcAgent;
import com.cheshun.mall.domain.entity.ClsMarketEtcAgentUpperOrder;
import com.cheshun.mall.domain.entity.ClsMarketEtcEsTrade;
import com.cheshun.mall.domain.entity.enums.TradeType;
import com.cheshun.mall.domain.mapper.AgentUpperOrderMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author wangzhuo
 * Created on 20211112
 */
@Component
@AllArgsConstructor
public class AgentUpperOrderDao implements BaseDao<ClsMarketEtcAgentUpperOrder>, ICache<ClsMarketEtcAgentUpperOrder>, IElasticSearch {
    private final static String CACHE_KEY_PREFIX_ORDER = "order";

    @Getter
    private final AgentUpperOrderMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;
    @Getter
    private final ElasticsearchOperations elasticsearchOperations;
    @Getter
    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final EsTradeMapper esTradeMapper;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentUpperOrder.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentUpperOrder> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentUpperOrder> getEntityClass() {
        return ClsMarketEtcAgentUpperOrder.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentUpperOrder entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        saveToCache(getCacheKeyPrefix(CACHE_KEY_PREFIX_ORDER) + entity.getOrderId(), entity.getId());
    }

    /**
     * 不推荐使用该方法保存数据
     *
     * @param entity 数据
     * @return ClsMarketEtcAgentUpperOrder
     * @see AgentUpperOrderDao#save(ClsMarketEtcAgent agent, ClsMarketEtcAgentUpperOrder entity) 推荐使用
     */
    @Deprecated
    @Override
    public ClsMarketEtcAgentUpperOrder save(ClsMarketEtcAgentUpperOrder entity) {
        return BaseDao.super.save(entity);
    }

    /**
     * 保存订单
     *
     * @param agent  代理商信息 {@link ClsMarketEtcAgent}
     * @param entity 订单信息 {@link ClsMarketEtcAgentUpperOrder}
     * @return 订单信息 {@link ClsMarketEtcAgentUpperOrder}
     */
    public ClsMarketEtcAgentUpperOrder save(ClsMarketEtcAgent agent, ClsMarketEtcAgentUpperOrder entity) {
        ClsMarketEtcAgentUpperOrder data = BaseDao.super.save(entity);
        if (data != null) {
            ClsMarketEtcEsTrade trade = new ClsMarketEtcEsTrade();
            trade.setId(TradeType.WITHDRAW.getValue() + "_" + data.getId());
            trade.setTradeId(data.getId());
            trade.setAgentId(entity.getAgentId());
            trade.setPhone(agent.getPhone());
            trade.setRealName(agent.getRealName());
            trade.setRole(agent.getRole().getValue());
            trade.setType(TradeType.WITHDRAW);
            trade.setUpdateTime(data.getUpdateTime().getTime());
            trade.setCreateTime(data.getCreateTime().getTime());
            if (!saveToES(trade)) {
                // 同步ES失败,创建并保存同步记录,会有定时任务去处理
                esTradeMapper.insert(trade);
            }
        }
        return data;
    }
}