package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.dao.interfaces.IElasticSearch;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentReturnGoods;
import com.cheshun.market.domain.entity.ClsMarketEtcEsTrade;
import com.cheshun.market.domain.entity.enums.ReturnGoodsStatus;
import com.cheshun.market.domain.entity.enums.TradeType;
import com.cheshun.market.domain.mapper.AgentReturnOrderMapper;
import com.cheshun.market.domain.mapper.EsTradeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangzhuo
 * Created on 20211112
 */
@Component
@AllArgsConstructor
public class AgentReturnGoodsDao implements BaseDao<ClsMarketEtcAgentReturnGoods>, ICache<ClsMarketEtcAgentReturnGoods>, IElasticSearch {
    private final static String CACHE_KEY_PREFIX_ORDER = "order";

    @Getter
    private final AgentReturnOrderMapper mapper;
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
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentReturnGoods.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentReturnGoods> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentReturnGoods> getEntityClass() {
        return ClsMarketEtcAgentReturnGoods.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentReturnGoods entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        saveToCache(getCacheKeyPrefix(CACHE_KEY_PREFIX_ORDER) + entity.getOrderId(), entity.getId());
    }

    /**
     * 不推荐使用该方法保存数据
     *
     * @param entity 数据
     * @return ClsMarketEtcAgentOrder
     * @see AgentReturnGoodsDao#save(ClsMarketEtcAgent agent, ClsMarketEtcAgentReturnGoods entity) 推荐使用
     */
    @Deprecated
    @Override
    public ClsMarketEtcAgentReturnGoods save(ClsMarketEtcAgentReturnGoods entity) {
        return BaseDao.super.save(entity);
    }

    /**
     * 保存订单
     *
     * @param agent  代理商信息 {@link ClsMarketEtcAgent}
     * @param entity 订单信息 {@link ClsMarketEtcAgentReturnGoods}
     * @return 订单信息 {@link ClsMarketEtcAgentReturnGoods}
     */
    public ClsMarketEtcAgentReturnGoods save(ClsMarketEtcAgent agent, ClsMarketEtcAgentReturnGoods entity) {
        ClsMarketEtcAgentReturnGoods data = BaseDao.super.save(entity);
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

    /**
     * 检测ETC卡号是否存在
     *
     * @param cardList ETC卡号列表
     * @return 已存在的ETC卡号列表
     */
    public List<String> cardIsExist4Retrun(List<String> cardList) {
        if (null == cardList || cardList.isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ClsMarketEtcAgentReturnGoods::getCardSn, cardList);
        queryWrapper.eq(ClsMarketEtcAgentReturnGoods::getStatus, ReturnGoodsStatus.NORMAL);
        return Optional.ofNullable(mapper.selectList(queryWrapper)).orElseGet(Collections::emptyList)
                .stream().map(ClsMarketEtcAgentReturnGoods::getCardSn).collect(Collectors.toList());
    }

    /**
     * 检测ETC设备号是否存在
     *
     * @param deviceList ETC设备号列表
     * @return 已存在的ETC设备号列表
     */
    public List<String> deviceIsExist4Return(List<String> deviceList) {
        if (null == deviceList || deviceList.isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNull(ClsMarketEtcAgentReturnGoods::getCardSn);
        queryWrapper.in(ClsMarketEtcAgentReturnGoods::getDeviceSn, deviceList);
        queryWrapper.eq(ClsMarketEtcAgentReturnGoods::getStatus, ReturnGoodsStatus.NORMAL);
        return Optional.ofNullable(mapper.selectList(queryWrapper)).orElseGet(Collections::emptyList)
                .stream().map(ClsMarketEtcAgentReturnGoods::getCardSn).collect(Collectors.toList());
    }

    /**
     * 移动端_根据订单号查询退货订单关联实体列表
     *
     * @param orderId    订单号
     * @return 退货订单关联实体列表
     */
    public List<ClsMarketEtcAgentReturnGoods> selectByOrderId(Long orderId) {
        if (null == orderId) {
            return null;
        }
        LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentReturnGoods::getOrderId, orderId);
        return Optional.ofNullable(mapper.selectList(queryWrapper)).orElseGet(Collections::emptyList);
    }
}