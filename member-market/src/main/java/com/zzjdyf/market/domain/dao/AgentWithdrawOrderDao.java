package com.zzjdyf.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zzjdyf.market.config.GlobalConfig;
import com.zzjdyf.market.domain.dao.interfaces.BaseDao;
import com.zzjdyf.market.domain.dao.interfaces.ICache;
import com.zzjdyf.market.domain.dao.interfaces.IElasticSearch;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentWithdrawOrder;
import com.zzjdyf.market.domain.entity.ClsMarketEtcEsTrade;
import com.zzjdyf.market.domain.entity.enums.TradeType;
import com.zzjdyf.market.domain.entity.enums.WithdrawStatus;
import com.zzjdyf.market.domain.mapper.AgentWithdrawOrderMapper;
import com.zzjdyf.market.domain.mapper.EsTradeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author 阿隆
 * Created on 2021/8/2 3:44 下午.
 */
@Component
@AllArgsConstructor
public class AgentWithdrawOrderDao implements BaseDao<ClsMarketEtcAgentWithdrawOrder>, ICache<ClsMarketEtcAgentWithdrawOrder>, IElasticSearch {
    private final static String CACHE_KEY_PREFIX_SN = "sn";

    @Getter
    private final AgentWithdrawOrderMapper mapper;
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
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentWithdrawOrder.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentWithdrawOrder> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentWithdrawOrder> getEntityClass() {
        return ClsMarketEtcAgentWithdrawOrder.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentWithdrawOrder entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
        saveToCache(getCacheKeyPrefix(CACHE_KEY_PREFIX_SN) + entity.getSn(), entity.getId());
    }

    /**
     * 不推荐使用该方法保存数据
     *
     * @param entity 数据
     * @return ClsMarketEtcAgentOrder
     * @see AgentWithdrawOrderDao#save(ClsMarketEtcAgent agent, ClsMarketEtcAgentWithdrawOrder entity) 推荐使用
     */
    @Deprecated
    @Override
    public ClsMarketEtcAgentWithdrawOrder save(ClsMarketEtcAgentWithdrawOrder entity) {
        return BaseDao.super.save(entity);
    }

    /**
     * 保存订单
     *
     * @param agent  代理商信息 {@link ClsMarketEtcAgent}
     * @param entity 订单信息 {@link ClsMarketEtcAgentWithdrawOrder}
     * @return 订单信息 {@link ClsMarketEtcAgentWithdrawOrder}
     */
    public ClsMarketEtcAgentWithdrawOrder save(ClsMarketEtcAgent agent, ClsMarketEtcAgentWithdrawOrder entity) {
        ClsMarketEtcAgentWithdrawOrder data = BaseDao.super.save(entity);
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
     * 查询指定代理商已提现总金额
     *
     * @param agentId 代理商id
     * @return 已提现总金额
     */
    public BigDecimal totalAmount(Long agentId) {
        LambdaQueryWrapper<ClsMarketEtcAgentWithdrawOrder> queryWrapper = new QueryWrapper<ClsMarketEtcAgentWithdrawOrder>()
                .select("sum(broker_amount) as brokerAmount")
                .lambda();
        queryWrapper.eq(ClsMarketEtcAgentWithdrawOrder::getAgentId, agentId);
        queryWrapper.in(ClsMarketEtcAgentWithdrawOrder::getStatus, Arrays.asList(
                WithdrawStatus.ACCEPTED,
                WithdrawStatus.FINISHED,
                WithdrawStatus.PENDING_PAUSE,
                WithdrawStatus.PENDING_UNKNOWN,
                WithdrawStatus.PENDING));
        ClsMarketEtcAgentWithdrawOrder data = getMapper().selectOne(queryWrapper);
        return Optional.ofNullable(data).map(ClsMarketEtcAgentWithdrawOrder::getBrokerAmount).orElse(BigDecimal.ZERO);
    }

    /**
     * 查询指定代理商今日已累计提现金额
     *
     * @param agentId 代理商id
     * @return 今日已累计提现金额
     */
    public BigDecimal todayWithdrawAmount(Long agentId) {
        LocalDateTime startDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1).plusSeconds(-1);
        LambdaQueryWrapper<ClsMarketEtcAgentWithdrawOrder> queryWrapper = new QueryWrapper<ClsMarketEtcAgentWithdrawOrder>()
                .select("sum(broker_amount) as brokerAmount")
                .lambda();
        queryWrapper.eq(ClsMarketEtcAgentWithdrawOrder::getAgentId, agentId);
        queryWrapper.in(ClsMarketEtcAgentWithdrawOrder::getStatus, Arrays.asList(
                WithdrawStatus.ACCEPTED,
                WithdrawStatus.FINISHED,
                WithdrawStatus.PENDING_PAUSE,
                WithdrawStatus.PENDING_UNKNOWN,
                WithdrawStatus.PENDING));
        queryWrapper.between(ClsMarketEtcAgentWithdrawOrder::getCreateTime,
                Date.from(startDateTime.toInstant(ZoneOffset.of("+8"))),
                Date.from(endDateTime.toInstant(ZoneOffset.of("+8"))));
        ClsMarketEtcAgentWithdrawOrder data = getMapper().selectOne(queryWrapper);
        return Optional.ofNullable(data).map(ClsMarketEtcAgentWithdrawOrder::getBrokerAmount).orElse(BigDecimal.ZERO);
    }

    /**
     * 查询指定代理商今日已提现次数
     *
     * @param agentId 代理商id
     * @return 今日已提现次数
     */
    public int todayWithdrawTimes(Long agentId) {
        LocalDateTime startDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1).plusSeconds(-1);
        LambdaQueryWrapper<ClsMarketEtcAgentWithdrawOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentWithdrawOrder::getAgentId, agentId);
        queryWrapper.in(ClsMarketEtcAgentWithdrawOrder::getStatus, Arrays.asList(
                WithdrawStatus.ACCEPTED,
                WithdrawStatus.FINISHED,
                WithdrawStatus.PENDING_PAUSE,
                WithdrawStatus.PENDING_UNKNOWN,
                WithdrawStatus.PENDING));
        queryWrapper.between(
                ClsMarketEtcAgentWithdrawOrder::getCreateTime,
                Date.from(startDateTime.toInstant(ZoneOffset.of("+8"))),
                Date.from(endDateTime.toInstant(ZoneOffset.of("+8"))));
        return Optional.ofNullable(getMapper().selectCount(queryWrapper)).orElse(0);
    }

    /**
     * 查询指定代理商是否有正在提现的订单
     *
     * @param agentId 代理商id
     * @return 是否有正在提现的订单
     */
    public boolean existAcceptedWithdrawOrder(Long agentId) {
        LambdaQueryWrapper<ClsMarketEtcAgentWithdrawOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentWithdrawOrder::getAgentId, agentId);
        // 提现状态不是终态时,即可认为提现正在处理中
        queryWrapper.notIn(ClsMarketEtcAgentWithdrawOrder::getStatus, Arrays.asList(
                WithdrawStatus.FINISHED,
                WithdrawStatus.FAILED,
                WithdrawStatus.REFUND,
                WithdrawStatus.CANCELED));
        return Optional.ofNullable(getMapper().selectCount(queryWrapper)).orElse(0) > 0;
    }
}