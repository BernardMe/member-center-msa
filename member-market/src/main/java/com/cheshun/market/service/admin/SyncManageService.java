package com.cheshun.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.dao.*;
import com.cheshun.market.domain.entity.*;
import com.cheshun.market.domain.entity.enums.BonusStatus;
import com.cheshun.market.domain.entity.enums.ProductStatus;
import com.cheshun.market.domain.entity.enums.TradeType;
import com.cheshun.market.domain.entity.enums.WithdrawStatus;
import com.zzjdyf.market.domain.dao.*;
import com.zzjdyf.market.domain.entity.*;
//import com.zzjdyf.market.schedule.FirstConsumeQuerySchedule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/6/30 9:28 上午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class SyncManageService {
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final AgentDao agentDao;
    private final AgentOrderDao agentOrderDao;
    private final AgentOrderGoodsDao agentOrderGoodsDao;
    private final AgentWithdrawOrderDao agentWithdrawOrderDao;
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
    private final GoodsDao goodsDao;
    private final RedissonClient redissonClient;
    //private final FirstConsumeQuerySchedule firstConsumeQuerySchedule;
    private static final String AGENT_LOCK_NAME = "WITHDRAW_LOCK_%d";

    /**
     * 同步订单到ES
     */
    public final void syncOrder() {
        elasticsearchTemplate.deleteIndex(ClsMarketEtcEsTrade.class);
        reloadAgentOrder();
        reloadWithdrawOrder();
    }

    /**
     * 重新加载代理订单数据到ES
     */
    private void reloadAgentOrder() {
        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ClsMarketEtcAgentOrder::getId);
        IPage<ClsMarketEtcAgentOrder> iPage = agentOrderDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgentOrder> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcAgentOrder entity : records) {
                ClsMarketEtcAgent agent = agentDao.findOne(entity.getAgentId());
                ClsMarketEtcEsTrade trade = new ClsMarketEtcEsTrade();
                trade.setId(entity.getType().getValue() + "_" + entity.getId());
                trade.setTradeId(entity.getId());
                trade.setAgentId(entity.getAgentId());
                if (agent != null) {
                    trade.setPhone(agent.getPhone());
                    trade.setRealName(agent.getRealName());
                    trade.setRole(agent.getRole().getValue());
                }
                trade.setType(entity.getType());
                trade.setUpdateTime(entity.getUpdateTime().getTime());
                trade.setCreateTime(entity.getCreateTime().getTime());
                agentOrderDao.saveToCache(entity);
                agentOrderDao.saveToES(trade);
            }
            pageIndex++;
            iPage = agentOrderDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }

    /**
     * 重新加载提现订单数据到ES
     */
    private void reloadWithdrawOrder() {
        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcAgentWithdrawOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ClsMarketEtcAgentWithdrawOrder::getId);
        IPage<ClsMarketEtcAgentWithdrawOrder> iPage = agentWithdrawOrderDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgentWithdrawOrder> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcAgentWithdrawOrder entity : records) {
                ClsMarketEtcAgent agent = agentDao.findOne(entity.getAgentId());
                ClsMarketEtcEsTrade trade = new ClsMarketEtcEsTrade();
                trade.setId(TradeType.WITHDRAW.getValue() + "_" + entity.getId());
                trade.setTradeId(entity.getId());
                trade.setAgentId(entity.getAgentId());
                if (agent != null) {
                    trade.setPhone(agent.getPhone());
                    trade.setRealName(agent.getRealName());
                    trade.setRole(agent.getRole().getValue());
                }
                trade.setType(TradeType.WITHDRAW);
                trade.setUpdateTime(entity.getUpdateTime().getTime());
                trade.setCreateTime(entity.getCreateTime().getTime());
                agentWithdrawOrderDao.saveToCache(entity);
                agentWithdrawOrderDao.saveToES(trade);
            }
            pageIndex++;
            iPage = agentWithdrawOrderDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }

    /**
     * 同步商品销量
     */
    public final void syncSales() {
        // 查询总销量
        QueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("goods_id as goodsId, count(1) as sales");
        queryWrapper1.isNotNull("card_sn");
        queryWrapper1.groupBy("goods_id");
        List<Map<String, Object>> selectResult = agentOrderGoodsDao.getMapper().selectMaps(queryWrapper1);
        selectResult = Optional.ofNullable(selectResult).orElse(new ArrayList<>(0));
        Map<Long, Long> salesMap = new HashMap<>();
        for (Map<String, Object> map : selectResult) {
            long goodsId = Long.parseLong(map.get("goodsId").toString());
            long sales = Long.parseLong(map.get("sales").toString());
            if (sales > 0) {
                salesMap.put(goodsId, sales);
            }
        }

        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ClsMarketEtcGoods::getId);
        IPage<ClsMarketEtcGoods> iPage = goodsDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcGoods> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcGoods entity : records) {
                if (salesMap.size() == 0 || !salesMap.containsKey(entity.getId())) {
                    continue;
                }
                entity.setSales(salesMap.get(entity.getId()));
                goodsDao.save(entity);
            }
            pageIndex++;
            iPage = goodsDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }

    /**
     * 同步卡和设备状态
     */
    public final void syncCardAndDeviceStatus() {
        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentPromoteHistory::getActiveBonusStatus, BonusStatus.SETTLED);
        queryWrapper.orderByAsc(ClsMarketEtcAgentPromoteHistory::getId);
        IPage<ClsMarketEtcAgentPromoteHistory> iPage = agentPromoteHistoryDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgentPromoteHistory> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcAgentPromoteHistory entity : records) {
                ClsMarketEtcAgentOrderGoods deviceOrderGoods = agentOrderGoodsDao.findOneByDeviceSn(entity.getDeviceSn());
                if (deviceOrderGoods != null && deviceOrderGoods.getCardStatus() == ProductStatus.NO_ACTIVE) {
                    deviceOrderGoods.setDeviceStatus(ProductStatus.ACTIVATED);
                    agentOrderGoodsDao.save(deviceOrderGoods);
                }
                ClsMarketEtcAgentOrderGoods cardOrderGoods = agentOrderGoodsDao.findOneByCardSn(entity.getCardSn());
                if (cardOrderGoods != null && cardOrderGoods.getCardStatus() == ProductStatus.NO_ACTIVE) {
                    cardOrderGoods.setCardStatus(ProductStatus.ACTIVATED);
                    cardOrderGoods.setDeviceSn(entity.getDeviceSn());
                    cardOrderGoods.setDeviceStatus(ProductStatus.ACTIVATED);
                    agentOrderGoodsDao.save(cardOrderGoods);
                }
            }
            pageIndex++;
            iPage = agentPromoteHistoryDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }

    /**
     * 同步代理商激活和分润信息
     */
    public final void syncBonus() {
        // 同步代理商已提现总金额
        this.syncWithdrawAmount();
        // 查询首次消费记录
        //firstConsumeQuerySchedule.handle(UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * 同步代理商已提现总金额
     */
    private void syncWithdrawAmount() {
        int pageIndex = 1;
        int pageSize = 20;
        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(ClsMarketEtcAgent::getTotalActiveBonus, 0)
                .or()
                .gt(ClsMarketEtcAgent::getTotalFirstConsumeBonus, 0);
        queryWrapper.orderByAsc(ClsMarketEtcAgent::getId);
        IPage<ClsMarketEtcAgent> iPage = agentDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        List<ClsMarketEtcAgent> records = iPage.getRecords();
        while (records != null && records.size() > 0) {
            for (ClsMarketEtcAgent agent : records) {
                // 获取分布式锁
                RLock rLock = redissonClient.getLock(AGENT_LOCK_NAME + agent.getId());
                try {
                    // 加锁
                    rLock.lock(60, TimeUnit.SECONDS);
                    // 加锁成功
                    agentDao.getMapper().updateWithdrawAmount(agent.getId(), Arrays.asList(
                            WithdrawStatus.ACCEPTED.getValue(),
                            WithdrawStatus.FINISHED.getValue(),
                            WithdrawStatus.PENDING_PAUSE.getValue(),
                            WithdrawStatus.PENDING_UNKNOWN.getValue(),
                            WithdrawStatus.PENDING.getValue()));
                    agentDao.reloadToCache(agent.getId());
                } finally {
                    if (rLock.isHeldByCurrentThread()) {
                        // 释放锁
                        rLock.unlock();
                    }
                }
            }
            pageIndex++;
            iPage = agentDao.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
            records = iPage.getRecords();
        }
    }
}
