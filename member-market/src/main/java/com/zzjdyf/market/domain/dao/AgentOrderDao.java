package com.zzjdyf.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.market.config.GlobalConfig;
import com.zzjdyf.market.domain.dao.interfaces.BaseDao;
import com.zzjdyf.market.domain.dao.interfaces.ICache;
import com.zzjdyf.market.domain.dao.interfaces.IElasticSearch;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentOrder;
import com.zzjdyf.market.domain.entity.ClsMarketEtcEsTrade;
import com.zzjdyf.market.domain.mapper.AgentOrderMapper;
import com.zzjdyf.market.domain.mapper.EsTradeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author 阿隆
 * Created on 2021/7/29 10:50 上午.
 */
@Component
@AllArgsConstructor
public class AgentOrderDao implements BaseDao<ClsMarketEtcAgentOrder>, ICache<ClsMarketEtcAgentOrder>, IElasticSearch {

    @Getter
    private final AgentOrderMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;
    @Getter
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final EsTradeMapper esTradeMapper;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentOrder.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentOrder> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentOrder> getEntityClass() {
        return ClsMarketEtcAgentOrder.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentOrder entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    /**
     * 不推荐使用该方法保存数据
     *
     * @param entity 数据
     * @return ClsMarketEtcAgentOrder
     * @see AgentOrderDao#save(ClsMarketEtcAgent agent, ClsMarketEtcAgentOrder entity) 推荐使用
     */
    @Deprecated
    @Override
    public ClsMarketEtcAgentOrder save(ClsMarketEtcAgentOrder entity) {
        return BaseDao.super.save(entity);
    }

    /**
     * 保存订单
     *
     * @param agent  代理商信息 {@link ClsMarketEtcAgent}
     * @param entity 订单信息 {@link ClsMarketEtcAgentOrder}
     * @return 订单信息 {@link ClsMarketEtcAgentOrder}
     */
    public ClsMarketEtcAgentOrder save(ClsMarketEtcAgent agent, ClsMarketEtcAgentOrder entity) {
        ClsMarketEtcAgentOrder data = BaseDao.super.save(entity);
        if (data != null) {
            ClsMarketEtcEsTrade trade = new ClsMarketEtcEsTrade();
            trade.setId(entity.getType().getValue() + "_" + data.getId());
            trade.setTradeId(data.getId());
            trade.setAgentId(entity.getAgentId());
            trade.setPhone(agent.getPhone());
            trade.setRealName(agent.getRealName());
            trade.setRole(agent.getRole().getValue());
            trade.setType(entity.getType());
            trade.setUpdateTime(data.getUpdateTime().getTime());
            trade.setCreateTime(data.getCreateTime().getTime());
            if (!saveToES(trade)) {
                // 同步ES失败,创建并保存同步记录,会有定时任务去处理
                esTradeMapper.insert(trade);
            }
        }
        return data;
    }

    /**
     * 单独保存订单到ES
     *
     * @param agent  代理商信息 {@link ClsMarketEtcAgent}
     * @param entity 订单信息 {@link ClsMarketEtcAgentOrder}
     * @return 订单信息 {@link ClsMarketEtcAgentOrder}
     */
    public ClsMarketEtcAgentOrder saveES(ClsMarketEtcAgent agent, ClsMarketEtcAgentOrder entity) {
        if (entity != null && entity.getId() != null) {
            ClsMarketEtcEsTrade trade = new ClsMarketEtcEsTrade();
            trade.setId(entity.getType().getValue() + "_" + entity.getId());
            trade.setTradeId(entity.getId());
            trade.setAgentId(entity.getAgentId());
            trade.setPhone(agent.getPhone());
            trade.setRealName(agent.getRealName());
            trade.setRole(agent.getRole().getValue());
            trade.setType(entity.getType());
            trade.setUpdateTime(entity.getUpdateTime().getTime());
            trade.setCreateTime(entity.getCreateTime().getTime());
            if (!saveToES(trade)) {
                // 同步ES失败,创建并保存同步记录,会有定时任务去处理
                esTradeMapper.insert(trade);
            }
        }
        return entity;
    }
}
