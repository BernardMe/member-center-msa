package com.cheshun.market.domain.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.config.GlobalConfig;
import com.cheshun.market.domain.dao.interfaces.BaseDao;
import com.cheshun.market.domain.dao.interfaces.ICache;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentOrderGoods;
import com.cheshun.market.domain.entity.enums.ProductStatus;
import com.cheshun.market.domain.mapper.AgentOrderGoodsMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
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
public class AgentOrderGoodsDao implements BaseDao<ClsMarketEtcAgentOrderGoods>, ICache<ClsMarketEtcAgentOrderGoods> {
    @Getter
    private final AgentOrderGoodsMapper mapper;
    @Getter
    private final RedissonClient redissonClient;
    private final GlobalConfig globalConfig;

    @PostConstruct
    @Override
    public void init() {
        BaseDao.super.init();
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), ClsMarketEtcAgentOrderGoods.class);
    }

    @Override
    public ICache<ClsMarketEtcAgentOrderGoods> getCache() {
        return this;
    }

    @Override
    public Duration getCacheDuration() {
        return globalConfig.getRedisCacheTtl();
    }

    @Override
    public Class<ClsMarketEtcAgentOrderGoods> getEntityClass() {
        return ClsMarketEtcAgentOrderGoods.class;
    }

    @Override
    public void saveToCache(ClsMarketEtcAgentOrderGoods entity) {
        saveToCache(getDefaultCacheKeyPrefix() + entity.getId(), entity);
    }

    /**
     * 检测ETC卡号是否存在
     *
     * @param cardList ETC卡号列表
     * @return 已存在的ETC卡号列表
     */
    public List<String> cardIsExist(List<String> cardList) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getCardStatus, ProductStatus.NO_ACTIVE,ProductStatus.ACTIVATED,ProductStatus.BROKEN);
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getCardSn, cardList);
        return Optional.ofNullable(mapper.selectList(queryWrapper)).orElseGet(Collections::emptyList)
                .stream().map(ClsMarketEtcAgentOrderGoods::getCardSn).collect(Collectors.toList());
    }

    /**
     * 检测ETC设备号是否存在
     *
     * @param deviceList ETC设备号列表
     * @return 已存在的ETC设备号列表
     */
    public List<String> deviceIsExist(List<String> deviceList) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getDeviceStatus,ProductStatus.NO_ACTIVE,ProductStatus.ACTIVATED,ProductStatus.BROKEN);
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getDeviceSn, deviceList);
        return Optional.ofNullable(mapper.selectList(queryWrapper)).orElseGet(Collections::emptyList)
                .stream().map(ClsMarketEtcAgentOrderGoods::getCardSn).collect(Collectors.toList());
    }

    /**
     * 根据ETC卡号获得对于的商品代理信息
     *
     * @param cardSn ETC卡号
     * @return 商品代理信息
     */
    public ClsMarketEtcAgentOrderGoods findOneByCardSn(String cardSn) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getCardStatus,ProductStatus.NO_ACTIVE,ProductStatus.ACTIVATED,ProductStatus.BROKEN);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getCardSn, cardSn)
                .or()
                // 尝试拼接速通卡的 1101 的前缀
                // ⚠️ 需要注意，因为目前只有速通卡，所以卡号需要拼接 1101 的前缀
                // 如果增加了其他发行方的卡，需要增加额外的判断，也许不需要拼接其他前缀
                .eq(ClsMarketEtcAgentOrderGoods::getCardSn, "1101" + cardSn);
        return mapper.selectOne(queryWrapper);
    }

    /**
     * 根据OBU标签号获得对于的商品代理信息
     *
     * @param deviceSn OBU标签号
     * @return 商品代理信息
     */
    public ClsMarketEtcAgentOrderGoods findOneByDeviceSn(String deviceSn) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getDeviceStatus,ProductStatus.NO_ACTIVE,ProductStatus.ACTIVATED,ProductStatus.BROKEN);
        queryWrapper.isNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getDeviceSn, deviceSn);
        return mapper.selectOne(queryWrapper);
    }

    /**
     * 查询代理商手中指定商品还未激活的卡数量
     *
     * @param agentId 代理商id
     * @param goodsId 指定商品
     * @return 还未激活的卡数量
     */
    public int countOfNoActive(Long agentId, Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getGoodsId, goodsId);
        queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getCardStatus, ProductStatus.NO_ACTIVE);
        return Optional.ofNullable(mapper.selectCount(queryWrapper)).orElse(0);
    }

    /**
     * 查询代理商手中指定商品已激活的卡数量
     *
     * @param agentId 代理商id
     * @param goodsId 指定商品
     * @return 已激活的卡数量
     */
    public int countOfActivated(Long agentId, Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getGoodsId, goodsId);
        queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getCardStatus, ProductStatus.ACTIVATED, ProductStatus.BROKEN, ProductStatus.BROKEN_RETURNED);
        return Optional.ofNullable(mapper.selectCount(queryWrapper)).orElse(0);
    }

    /**
     * 查询代理商手中指定商品已损坏未退回的卡数量
     *
     * @param agentId 代理商id
     * @param goodsId 指定商品
     * @return 已损坏未退回的卡数量
     */
    public int countOfBroken(Long agentId, Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getGoodsId, goodsId);
        queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getCardStatus, ProductStatus.BROKEN);
        return Optional.ofNullable(mapper.selectCount(queryWrapper)).orElse(0);
    }

    /**
     * 查询代理商手中指定商品还未激活的OBU数量
     *
     * @param agentId 代理商id
     * @param goodsId 指定商品
     * @return 还未激活的OBU数量
     */
    public int countOfNoActiveObu(Long agentId, Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getGoodsId, goodsId);
        queryWrapper.isNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getDeviceSn);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getDeviceStatus, ProductStatus.NO_ACTIVE);
        return Optional.ofNullable(mapper.selectCount(queryWrapper)).orElse(0);
    }

    /**
     * 查询代理商手中指定商品已激活的OBU数量
     *
     * @param agentId 代理商id
     * @param goodsId 指定商品
     * @return 已激活的OBU数量
     */
    public int countOfActivatedObu(Long agentId, Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getGoodsId, goodsId);
        queryWrapper.isNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getDeviceSn);
        queryWrapper.in(ClsMarketEtcAgentOrderGoods::getDeviceStatus, ProductStatus.ACTIVATED, ProductStatus.BROKEN, ProductStatus.BROKEN_RETURNED);
        return Optional.ofNullable(mapper.selectCount(queryWrapper)).orElse(0);
    }

    /**
     * 查询代理商手中指定商品已损坏未退回的OBU数量
     *
     * @param agentId 代理商id
     * @param goodsId 指定商品
     * @return 已损坏未退回的OBU数量
     */
    public int countOfBrokenObu(Long agentId, Long goodsId) {
        LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, agentId);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getGoodsId, goodsId);
        queryWrapper.isNull(ClsMarketEtcAgentOrderGoods::getCardSn);
        queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getDeviceSn);
        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getDeviceStatus, ProductStatus.BROKEN);
        return Optional.ofNullable(mapper.selectCount(queryWrapper)).orElse(0);
    }
}
