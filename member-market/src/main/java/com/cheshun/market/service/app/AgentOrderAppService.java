package com.cheshun.market.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.dao.*;
import com.cheshun.market.domain.entity.*;
import com.cheshun.market.domain.entity.enums.*;
import com.cheshun.market.service.DtoService;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.vo.command.AddOrderCommand;
import com.cheshun.market.vo.command.AddRepleshOrderCommand;
import com.cheshun.market.vo.command.AddRepleshUpOrderCommand;
import com.cheshun.market.vo.command.AddReturnOrderCommand;
import com.cheshun.market.vo.dto.CanReplenishAppDto;
import com.cheshun.market.vo.dto.OrderAppDto;
import com.cheshun.market.vo.query.IdQuery;
import com.cheshun.market.vo.query.OrderAppPageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import static com.cheshun.market.domain.entity.enums.TradeType.*;

/**
 * @author wangzhuo
 * Created on 20210727
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentOrderAppService {
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final AgentDao agentDao;
    private final AgentGoodsDao agentGoodsDao;
    private final AgentOrderDao agentOrderDao;
    private final AgentOrderGoodsDao agentOrderGoodsDao;
    private final AgentReturnGoodsDao agentReturnGoodsDao;
    private final AgentUpperOrderDao agentUpperOrderDao;
    private final AgentWithdrawOrderDao agentWithdrawOrderDao;
    private final GoodsDao goodsDao;
    private final GoodsSkuDao goodsSkuDao;
    private final TransactionDefinition transactionDefinition;
    private final PlatformTransactionManager platformTransactionManager;

    /**
     * 创建订货订单
     *
     * @param id      代理商id
     * @param command 命令 {@link AddOrderCommand}
     */
    public void order(Long id, AddOrderCommand command) {
        // 获取当前登陆用户的代理商信息
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .filter(t -> t.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE || t.getAuditStatus() == AgentAuditStatus.PASSED)
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
            throw new RRException("您已申请解约,不可执行该操作");
        }
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            throw new RRException("仅允许一级代理商采购设备");
        }
        // 该代理商是否已经代理其他商品
        LambdaQueryWrapper<ClsMarketEtcAgentGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentGoods::getAgentId, agent.getId());
        queryWrapper.ne(ClsMarketEtcAgentGoods::getGoodsId, command.getGoodsId());
        // 过滤代理商品信息is_enable有效
        queryWrapper.eq(ClsMarketEtcAgentGoods::getIsEnabled, Boolean.TRUE);
        // 代理状态(已申请待缴纳|已缴纳押金)
        queryWrapper.in(ClsMarketEtcAgentGoods::getStatus, DepositStatus.WAIT_PAYMENT, DepositStatus.PAID, DepositStatus.WAIT_FILLED);
        if (Optional.ofNullable(agentGoodsDao.getMapper().selectCount(queryWrapper)).orElse(0) >= 1) {
            throw new RRException("您已代理其他商品");
        }
        // 获取要代理的商品信息
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(command.getGoodsId()))
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .orElseThrow(() -> new RRException("该商品不存在"));
        // 获取要代理的商品规格信息
        List<ClsMarketEtcGoodsSku> goodsSkuList = goodsSkuDao.findAllByGoods(goods.getId());
        ClsMarketEtcGoodsSku goodsSku = goodsSkuList.stream()
                .filter(t -> t.getId().equals(command.getGoodsSkuId()))
                .findAny()
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .orElseThrow(() -> new RRException("该商品规格不存在"));
        // 校验商品库存
        if (goods.getStock() <= goodsSku.getQuantity()) {
            throw new RRException("该商品规格库存不足");
        }
        // 校验该代理商是否正在代理该商品
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        ClsMarketEtcAgentGoods agentGoods = agentGoodsList.stream()
                // 代理商品的状态为"待缴纳押金"或"已缴纳押金"
                .filter(t -> t.getStatus() == DepositStatus.WAIT_PAYMENT || t.getStatus() == DepositStatus.PAID || t.getStatus() == DepositStatus.WAIT_FILLED)  ///GLD
                .findAny()
                .orElse(null);
        if (agentGoods != null) {
            // 该代理商品已存在,校验该代理商品是否存在"订货"订单
            LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
            // 订单类型为"订货"
            queryWrapper1.eq(ClsMarketEtcAgentOrder::getType, ORDER);
            int orderCount = agentOrderDao.getMapper().selectCount(queryWrapper1);
            // 每一个代理商品有且仅有一条"订货"订单数据
            // "订货"订单是"补货"订单的前置订单,如果"订货"订单不存在,"补货"订单不可能存在
            if (orderCount >= 1) {
                // "订货"订单已存在
                throw new RRException("您已代理该商品");
            }
            // 该代理商品还未创建订货订单
        } else {
            // 还未代理该商品,创建代理商品信息
            agentGoods = new ClsMarketEtcAgentGoods();
            agentGoods.setAgentId(agent.getId());
            agentGoods.setGoodsId(goods.getId());
            agentGoods.setGoodsSkuId(goodsSku.getId());
            agentGoods.setQuantity(goodsSku.getQuantity());
            agentGoods.setDeposit(goodsSku.getDeposit());
            agentGoods.setReplRatio(goodsSku.getReplRatio());
            agentGoods.setFullRepl(goodsSku.getFullRepl());
            agentGoods.setPaidDeposit(BigDecimal.ZERO);
            agentGoods.setStatus(DepositStatus.WAIT_PAYMENT);
            // 创建订货订单代理商品信息is_enable默认有效
            agentGoods.setIsEnabled(Boolean.TRUE);
            // 保存代理商品信息
            agentGoods = agentGoodsDao.save(agentGoods);
            if (agentGoods == null) {
                throw new RRException("系统异常,保存失败");
            }
        }
        // 创建该代理商品的订货订单
        ClsMarketEtcAgentOrder agentOrder = new ClsMarketEtcAgentOrder();

        agentOrder.setType(ORDER);
        agentOrder.setAgentGoodsId(agentGoods.getId());
        agentOrder.setAgentId(agent.getId());
        agentOrder.setGoodsId(goods.getId());
        agentOrder.setCardQuantity(goodsSku.getQuantity());
        agentOrder.setDeviceQuantity(goodsSku.getQuantity());
        agentOrder.setConsignee(command.getConsignee());
        agentOrder.setConsigneePhone(command.getConsigneePhone());
        agentOrder.setConsigneeAddress(command.getConsigneeAddress());
        agentOrder.setStatus(OrderStatus.WAIT_DELIVER);
        agentOrder.setGoodsSkuId(agentGoods.getGoodsSkuId());
        // 保存订货订单信息
        agentOrder = agentOrderDao.save(agent, agentOrder);
        if (agentOrder == null) {
            throw new RRException("系统异常,保存失败");
        }
    }

    /**
     * 根据id查询订单
     *
     * @param id    代理商id
     * @param query 条件 {@link IdQuery}
     * @return 订单  {@link OrderAppDto}
     */
    public OrderAppDto get(Long id, IdQuery query) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        ClsMarketEtcAgentOrder order = Optional.ofNullable(agentOrderDao.findOne(query.getId()))
                .filter(t -> t.getAgentId().equals(agent.getId()))
                .orElseThrow(() -> new RRException("该订单不存在"));
        return DtoService.INSTANCE.toDto(order);
    }

    /**
     * 分页查询订单
     *
     * @param id    代理商id
     * @param query 条件 {@link OrderAppPageQuery}
     * @return 订单列表  {@link IPage<OrderAppDto>}
     */
    public IPage<OrderAppDto> query(Long id, OrderAppPageQuery query) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (!elasticsearchTemplate.indexExists(ClsMarketEtcEsTrade.class)) {
            return new Page<>();
        }
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            // 只有一级代理商才会有订单
            return new Page<>();
        }
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        searchQuery.withIndices("cls_market_etc");
        searchQuery.withTypes("agent_order");
        // 设置分页
        searchQuery.withPageable(PageRequest.of(query.getPageNum() - 1, query.getPageSize()));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 查询条件-代理商Id
        Optional.ofNullable(agent.getId())
                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("agentId", t)));
        // 查询条件-订单类型(订货/补货)
        Optional.ofNullable(query.getType()).filter(t -> t == ORDER || t == REPLENISH || t == WITHDRAW)
                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.termQuery("type", t.getValue())));
        // 查询条件-交易时间
        if (query.getStartTime() != null && query.getEndTime() != null) {
            LocalDateTime beginDate = query.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
            LocalDateTime endDate = query.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1).atStartOfDay();
            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime")
                    .from(beginDate.toInstant(ZoneOffset.of("+8")).toEpochMilli(), true)
                    .to(endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli(), true));
        }
        searchQuery.withQuery(boolQueryBuilder);
        searchQuery.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
        org.springframework.data.domain.Page<ClsMarketEtcEsTrade> page = elasticsearchTemplate.queryForPage(searchQuery.build(), ClsMarketEtcEsTrade.class);
        List<OrderAppDto> list = new ArrayList<>();
        if (page.hasContent()) {
            List<ClsMarketEtcEsTrade> content = page.getContent();
            //这里便利ES里面的交易记录
            for (ClsMarketEtcEsTrade trade : content) {
                //这里判断当前交易是一个订货/补货订单
                if (ORDER.equals(trade.getType()) || REPLENISH.equals(trade.getType())
                        || REPLENISH_UP.equals(trade.getType()) || RETURN.equals(trade.getType())) {
                    ClsMarketEtcAgentOrder agentOrder = agentOrderDao.findOne(trade.getTradeId());
                    if (agentOrder == null) {
                        continue;
                    }
                    // 查询个人订单列表可能是历史订单信息所以不能过滤is_enable
                    ClsMarketEtcAgentGoods agentGoods = agentGoodsDao.findOne(agentOrder.getAgentGoodsId());
                    if (agentGoods == null) {
                        continue;
                    }
                    OrderAppDto orderAppDto = DtoService.INSTANCE.toAppDto(agentOrder);
                    orderAppDto.setTypeName(orderAppDto.getType().getDescribe());
                    orderAppDto.setStatusStr(agentOrder.getStatus().getDescribe());
                    switch (agentOrder.getType()) {
                        case ORDER:
                            // 订货订单
                            orderAppDto.setAmount(agentGoods.getPaidDeposit());
                            break;
                        case REPLENISH:
                            // 补货订单
                            orderAppDto.setAmount(BigDecimal.ZERO);
                            break;
                        case REPLENISH_UP:
                            // 提标补货订单
                            orderAppDto.setAmount(BigDecimal.ZERO);
                            break;
                        case RETURN:
                            // 退货订单
                            orderAppDto.setAmount(BigDecimal.ZERO);
                            break;
                    }
                    list.add(orderAppDto);
                }
                //这里判断当前交易是一个提现订单
                if (WITHDRAW.equals(trade.getType())) {
                    ClsMarketEtcAgentWithdrawOrder agentWithdrawOrder = agentWithdrawOrderDao.findOne(trade.getTradeId());
                    if (agentWithdrawOrder == null) {
                        continue;
                    }
                    OrderAppDto orderAppDto = DtoService.INSTANCE.toAppDto(agentWithdrawOrder);
                    orderAppDto.setType(WITHDRAW);
                    orderAppDto.setTypeName(orderAppDto.getType().getDescribe());
                    orderAppDto.setStatusStr(agentWithdrawOrder.getStatus().getDescribe());
                    orderAppDto.setAmount(agentWithdrawOrder.getBrokerAmount());
                    list.add(orderAppDto);
                }
            }
        }
        return new Page<OrderAppDto>(page.getNumber(), page.getSize(), page.getTotalElements()).setRecords(list);
    }

    /**
     * 查询待补货明细
     *
     * @param id 代理商id
     * @return 待补货明细信息 {@link CanReplenishAppDto}
     */
    public CanReplenishAppDto canReplenish(Long id) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            // 只有一级代理商才能补货
            return new CanReplenishAppDto();
        }
        // 获取该代理商正在代理的商品
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        ClsMarketEtcAgentGoods agentGoods = agentGoodsList.stream()
                // 代理商品的状态为"已缴纳押金"或"待补足押金"
                .filter(t -> (t.getStatus() == DepositStatus.PAID || t.getStatus() == DepositStatus.WAIT_FILLED))  ///GLD
                .findAny()
                .orElseThrow(() -> new RRException("您还未代理商品或未缴纳押金"));
        // 查询代理商手中已激活卡数量
        int activatedQuantity = agentOrderGoodsDao.countOfActivated(agent.getId(), agentGoods.getGoodsId());
        // 查询代理商手中剩余的未激活卡数量
        int noActiveQuantity = agentOrderGoodsDao.countOfNoActive(agent.getId(), agentGoods.getGoodsId());
        // 询代理商手中剩余的已损坏未退回卡数量
        int brokenQuantity = agentOrderGoodsDao.countOfBroken(agent.getId(), agentGoods.getGoodsId());
        // 计算满足补货条件时最多应该剩余的数量 = 订货申请的卡数量 - 1
        int shouldRemainQuantity = BigDecimal.valueOf(agentGoods.getQuantity()).subtract(BigDecimal.ONE).intValue();
        // 可补货卡数量
        int replenishQuantity = 0;
        // 判断剩余未激活数量是否满足补货条件
        if (shouldRemainQuantity >= noActiveQuantity) {
            // 计算可补货的数量 = (订货申请的卡数量 - 未激活卡数量 - 已损坏未退回卡数量)
            replenishQuantity = agentGoods.getQuantity() - (noActiveQuantity + brokenQuantity);
        }
        // 查询代理商手中已激活OBU数量
        int activatedObuQuantity = agentOrderGoodsDao.countOfActivatedObu(agent.getId(), agentGoods.getGoodsId());
        // 查询代理商手中剩余的未激活OBU数量
        int noActiveObuQuantity = agentOrderGoodsDao.countOfNoActiveObu(agent.getId(), agentGoods.getGoodsId());
        // 询代理商手中剩余的已损坏未退回OBU数量
        int brokenObuQuantity = agentOrderGoodsDao.countOfBrokenObu(agent.getId(), agentGoods.getGoodsId());
        // 计算满足补货条件时最多应该剩余的数量 = 订货申请的OBU数量 - 1
        int shouldRemainObuQuantity = BigDecimal.valueOf(agentGoods.getQuantity()).subtract(BigDecimal.ONE).intValue();
        // 可补货OBU数量
        int replenishObuQuantity = 0;
        // 判断剩余未激活数量是否满足补货条件
        if (shouldRemainObuQuantity >= noActiveObuQuantity) {
            // 计算可补货的数量 = (订货申请的OBU数量 - 未激活OBU数量 - 已损坏未退回OBU数量)
            replenishObuQuantity = agentGoods.getQuantity() - (noActiveObuQuantity + brokenObuQuantity);
        }
        CanReplenishAppDto canReplenishAppDto = new CanReplenishAppDto();
        // 累计设备数量 = 已激活设备数量 + 未激活设备数量
        canReplenishAppDto.setTotalQuantity(activatedQuantity + noActiveQuantity);
        // 已激活设备数量
        canReplenishAppDto.setActivatedQuantity(activatedQuantity);
        // 订货申请的设备数量
        canReplenishAppDto.setSkuQuantity(agentGoods.getQuantity());
        // 未激活设备数量
        canReplenishAppDto.setNoActiveQuantity(noActiveQuantity);
        // 可补货设备数量
        canReplenishAppDto.setReplenishQuantity(replenishQuantity);
        // 累计OBU数量
        canReplenishAppDto.setTotalObuQuantity(activatedObuQuantity + noActiveObuQuantity);
        // 已激活OBU数量
        canReplenishAppDto.setActivatedObuQuantity(activatedObuQuantity);
        // 未激活OBU数量
        canReplenishAppDto.setNoActiveObuQuantity(noActiveObuQuantity);
        // 可补货的OBU数量
        canReplenishAppDto.setReplenishObuQuantity(replenishObuQuantity);
        // 是否可补货
        canReplenishAppDto.setCanReplenish(replenishQuantity > 0 || replenishObuQuantity > 0);
        return canReplenishAppDto;
    }

    /**
     * 查询提标补货待补货明细
     *
     * @param id 代理商id
     * @param goodsSkuId 要提标规格id
     * @return 待补货明细信息 {@link CanReplenishAppDto}
     */
    public CanReplenishAppDto canReplenishUp(Long id, Long goodsSkuId) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            // 只有一级代理商才能补货
            return new CanReplenishAppDto();
        }
        // 获取该代理商正在代理的商品
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        ClsMarketEtcAgentGoods agentGoods = agentGoodsList.stream()
                // 代理商品的状态为"已缴纳押金"或"待补足押金"
                .filter(t -> (t.getStatus() == DepositStatus.PAID || t.getStatus() == DepositStatus.WAIT_FILLED))  ///GLD
                .findAny()
                .orElseThrow(() -> new RRException("您还未代理商品或未缴纳押金"));
        // 获取要代理的商品信息
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(agentGoods.getGoodsId()))
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .orElseThrow(() -> new RRException("该商品不存在"));
        // 获取正在代理的商品规格信息
        List<ClsMarketEtcGoodsSku> goodsSkuList = goodsSkuDao.findAllByGoods(goods.getId());
        // 获取要提标代理的商品规格信息
        ClsMarketEtcGoodsSku newGoodsSku = goodsSkuList.stream()
                .filter(t -> t.getId().equals(goodsSkuId) && t.getStatus() == PublishStatus.ADDED)
                .findAny()
                .orElseThrow(() -> new RRException("要提标代理的商品规格不存在"));
        // 校验必须单向提升规格
        if (agentGoods.getQuantity() >= newGoodsSku.getQuantity()) {
            throw new RRException("提标补货必须提升规格");
        }
        // 查询代理商手中已激活设备卡数量
        int activatedQuantity = agentOrderGoodsDao.countOfActivated(agent.getId(), agentGoods.getGoodsId());
        // 查询代理商手中剩余的未激活卡数量
        int noActiveQuantity = agentOrderGoodsDao.countOfNoActive(agent.getId(), agentGoods.getGoodsId());
        // 询代理商手中剩余的已损坏未退回卡数量
        int brokenQuantity = agentOrderGoodsDao.countOfBroken(agent.getId(), agentGoods.getGoodsId());
        int replenishQuantity = newGoodsSku.getQuantity() - (noActiveQuantity + brokenQuantity);
        // 查询代理商手中剩余的未激活OBU数量
        int noActiveObuQuantity = agentOrderGoodsDao.countOfNoActiveObu(agent.getId(), agentGoods.getGoodsId());
        // 查询代理商手中剩余的已损坏未退OBU数量
        int brokenObuQuantity = agentOrderGoodsDao.countOfBrokenObu(agent.getId(), agentGoods.getGoodsId());
        int replenishObuQuantity = newGoodsSku.getQuantity() - (noActiveObuQuantity + brokenObuQuantity);
        // 再次计算代理商提标后最多可补货的数量
        int maxReplenishQuantity = Math.min(replenishQuantity, replenishObuQuantity);
        CanReplenishAppDto canReplenishAppDto = new CanReplenishAppDto();
        // 累计设备数量 = 已激活设备数量 + 未激活设备数量
        canReplenishAppDto.setTotalQuantity(activatedQuantity + noActiveQuantity);
        // 已激活设备数量
        canReplenishAppDto.setActivatedQuantity(activatedQuantity);
        // 订货申请的设备数量
        canReplenishAppDto.setSkuQuantity(agentGoods.getQuantity());
        // 未激活设备数量
        canReplenishAppDto.setNoActiveQuantity(noActiveQuantity);
        // 提标补货申请的设备规格
        canReplenishAppDto.setAuditSkuQuantity(newGoodsSku.getQuantity());
        // 可补货商品套数
        canReplenishAppDto.setReplenishQuantity(maxReplenishQuantity);
        // 是否可补货
        canReplenishAppDto.setCanReplenish(replenishQuantity > 0);
        return canReplenishAppDto;
    }

    /**
     * 创建补货订单
     *
     * @param id      代理商id
     * @param command {@link AddRepleshOrderCommand}
     */
    public void createReplenishOrder(Long id, AddRepleshOrderCommand command) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .filter(t -> t.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE || t.getAuditStatus() == AgentAuditStatus.PASSED)
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
            throw new RRException("您已申请解约,不可执行该操作");
        }
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            throw new RRException("仅允许一级代理商补货");
        }
        // 获取该代理商正在代理的商品
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        ClsMarketEtcAgentGoods agentGoods = agentGoodsList.stream()
                // 代理商品的状态为"已缴纳押金"
                .filter(t -> (t.getStatus() == DepositStatus.PAID))
                .findAny()
                .orElseThrow(() -> new RRException("您未代理该商品或未缴纳押金"));  ///GLD
        // 获取要代理的商品信息
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(agentGoods.getGoodsId()))
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .orElseThrow(() -> new RRException("该商品不存在"));
        // 获取要代理的商品规格信息
        List<ClsMarketEtcGoodsSku> goodsSkuList = goodsSkuDao.findAllByGoods(goods.getId());
        ClsMarketEtcGoodsSku goodsSku = goodsSkuList.stream()
                .filter(t -> t.getId().equals(agentGoods.getGoodsSkuId()) && t.getStatus() == PublishStatus.ADDED)
                .findAny()
                .orElseThrow(() -> new RRException("该商品规格不存在"));
        // 校验商品库存
        if (goods.getStock() <= goodsSku.getQuantity()) {
            throw new RRException("该商品规格库存不足");
        }
        // 校验商品OBU库存
        if (goods.getObuStock() <= goodsSku.getQuantity()) {
            throw new RRException("该商品规格OBU库存不足");
        }
        // 校验该代理商品是否存在"订货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrder::getType, ORDER);
        queryWrapper.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.SHIPPED);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper)).orElse(0) == 0) {
            throw new RRException("您还未订购该商品");
        }
        // 校验该代理商品是否存在待发货的"补货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getType, REPLENISH);
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_REPLENISH);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper2)).orElse(0) > 0) {
            throw new RRException("您已经存在待补货订单，请勿重复申请");
        }
        // 校验该代理商品是否存在待发货的"提标补货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getType, REPLENISH_UP);
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_REPLENISH);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper3)).orElse(0) > 0) {
            throw new RRException("您已经存在待审核提标补货订单，无法创建补货申请");
        }
        // 校验该代理商品是否存在待审核的"退货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getType, RETURN);
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_RETURN);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper4)).orElse(0) > 0) {
            throw new RRException("您已经存在申请退货订单，无法创建补货申请");
        }
        Integer applyCardQuantity = StringUtils.isEmpty(command.getApplyCardQuantity()) ? 0 : Integer.parseInt(command.getApplyCardQuantity());
        Integer applyDeviceQuantity = StringUtils.isEmpty(command.getApplyDeviceQuantity()) ? 0 : Integer.parseInt(command.getApplyDeviceQuantity());
        if (applyCardQuantity.compareTo(0) < 0 || applyDeviceQuantity.compareTo(0) < 0) {
            throw new RRException("申请补货的卡数量或OBU数量异常");
        }
        if (applyCardQuantity > 0) {
            // 查询代理商手中剩余的未激活数量
            int noActiveQuantity = agentOrderGoodsDao.countOfNoActive(agent.getId(), agentGoods.getGoodsId());
            // 计算满足补货条件时最多应该剩余的数量 = 订货申请的设备数量 - 1
            int shouldRemainQuantity = BigDecimal.valueOf(agentGoods.getQuantity())
                    .subtract(BigDecimal.ONE)
                    .intValue();
            int brokenQuantity = agentOrderGoodsDao.countOfBroken(agent.getId(), agentGoods.getGoodsId());
            // 判断剩余未激活数量是否满足补货条件
            if (shouldRemainQuantity < noActiveQuantity) {
                throw new RRException("不满足补货条件,至少还需激活 " + (noActiveQuantity - shouldRemainQuantity) + " 张卡后才能补货");
            }
            if (applyCardQuantity.compareTo((goodsSku.getQuantity() - (noActiveQuantity + brokenQuantity))) > 0) {
                throw new RRException("申请补货的卡数量超过当前规格可补货卡数量");
            }
        }
        if (applyDeviceQuantity > 0) {
            // 查询代理商手中剩余的未激活OBU数量
            int noActiveObuQuantity = agentOrderGoodsDao.countOfNoActiveObu(agent.getId(), agentGoods.getGoodsId());
            // 计算满足补货条件时最多应该剩余的数量 = 订货申请的OBU数量 - 1
            int shouldRemainObuQuantity = BigDecimal.valueOf(agentGoods.getQuantity())
                    .subtract(BigDecimal.ONE)
                    .intValue();
            int brokenObuQuantity = agentOrderGoodsDao.countOfBrokenObu(agent.getId(), agentGoods.getGoodsId());
            // 判断剩余未激活数量是否满足补货条件
            if (shouldRemainObuQuantity < noActiveObuQuantity) {
                throw new RRException("不满足补货条件,至少还需激活 " + (noActiveObuQuantity - shouldRemainObuQuantity) + " 个OBU后才能补货");
            }
            if (applyDeviceQuantity.compareTo((goodsSku.getQuantity() - (noActiveObuQuantity + brokenObuQuantity))) > 0) {
                throw new RRException("申请补货的OBU数量超过当前规格可补货OBU数量");
            }
        }
        // 创建补货订单
        ClsMarketEtcAgentOrder order = new ClsMarketEtcAgentOrder();
        order.setType(REPLENISH);
        order.setAgentGoodsId(agentGoods.getId());
        order.setAgentId(agent.getId());
        order.setGoodsId(goods.getId());
        // 2.0需求不再判断是否全补
        order.setCardQuantity(applyCardQuantity);
        order.setDeviceQuantity(applyDeviceQuantity);
        order.setConsignee(command.getConsignee());
        order.setConsigneePhone(command.getConsigneePhone());
        order.setConsigneeAddress(command.getConsigneeAddress());
        order.setStatus(OrderStatus.APPLY_REPLENISH);
        order.setGoodsSkuId(agentGoods.getGoodsSkuId());
        // 保存补货订单
        if (agentOrderDao.save(agent, order) == null) {
            throw new RRException("系统异常，补货失败");
        }
    }

    /**
     * 创建提标补货订单
     *
     * @param id      代理商id
     * @param command {@link AddRepleshUpOrderCommand}
     */
    public void createReplenishUpOrder(Long id, AddRepleshUpOrderCommand command) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .filter(t -> t.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE || t.getAuditStatus() == AgentAuditStatus.PASSED)
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
            throw new RRException("您已申请解约,不可执行该操作");
        }
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            throw new RRException("仅允许一级代理商补货");
        }
        // 获取该代理商正在代理的商品
        // 包含代理商-规格信息
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        ClsMarketEtcAgentGoods agentGoods = agentGoodsList.stream()
                // 代理商品的状态为"已缴纳押金"或"待补足押金"
                .filter(t -> (t.getStatus() == DepositStatus.PAID || t.getStatus() == DepositStatus.WAIT_FILLED))
                .findAny()
                .orElseThrow(() -> new RRException("您未代理该商品或未缴纳押金"));
        // 获取要代理的商品信息
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(agentGoods.getGoodsId()))
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .orElseThrow(() -> new RRException("该商品不存在"));
        // 获取正在代理的商品规格信息
        List<ClsMarketEtcGoodsSku> goodsSkuList = goodsSkuDao.findAllByGoods(goods.getId());
        ClsMarketEtcGoodsSku goodsSku = goodsSkuList.stream()
                .filter(t -> t.getId().equals(agentGoods.getGoodsSkuId()) && t.getStatus() == PublishStatus.ADDED)
                .findAny()
                .orElseThrow(() -> new RRException("正在代理的商品规格信息异常"));
        // 校验代理商是否正在代理同等规格
        if (agentGoods.getGoodsSkuId().equals(command.getNewSkuId())) {
            throw new RRException("您正在代理该商品的同等规格");
        }
        // 获取要提标代理的商品规格信息
        ClsMarketEtcGoodsSku newGoodsSku = goodsSkuList.stream()
                .filter(t -> t.getId().equals(command.getNewSkuId()) && t.getStatus() == PublishStatus.ADDED)
                .findAny()
                .orElseThrow(() -> new RRException("要提标代理的商品规格不存在"));
        // 校验必须单向提升规格
        if (goodsSku.getQuantity() >= newGoodsSku.getQuantity()) {
            throw new RRException("提标补货必须提升规格");
        }
        // 校验自定义补货数量必须 大于0且不为空
        if ("1".equals(String.valueOf(command.getIsCustomQuantity()))) {
            if (command.getCustomRepleshQuantity() <= 0 || null == command.getCustomRepleshQuantity()) {
                throw new RRException("自定义补货数量输入异常");
            }
        }
        // 校验商品库存
        if (goods.getStock() <= newGoodsSku.getQuantity()) {
            throw new RRException("该商品规格库存不足");
        }
        // 校验该代理商品是否存在"订货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrder::getType, ORDER);
        queryWrapper.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.SHIPPED);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper)).orElse(0) == 0) {
            throw new RRException("您还未订购该商品");
        }
        // 校验该代理商品是否存在待发货的"补货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getType, REPLENISH);
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_REPLENISH);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper2)).orElse(0) > 0) {
            throw new RRException("您已经存在待补货订单，无法创建提标补货申请");
        }
        // 校验该代理商品是否存在待发货的"提标补货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getType, REPLENISH_UP);
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_REPLENISH);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper3)).orElse(0) > 0) {
            throw new RRException("您已经存在待审核提标补货订单，请勿重复申请");
        }
        // 校验该代理商品是否存在待审核的"退货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getType, RETURN);
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_RETURN);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper4)).orElse(0) > 0) {
            throw new RRException("您已经存在申请退货订单，无法创建提标补货申请");
        }
        // 查询代理商手中剩余的未激活卡数量
        int noActiveQuantity = agentOrderGoodsDao.countOfNoActive(agent.getId(), agentGoods.getGoodsId());
        // 查询代理商手中剩余的已损坏未退回卡数量
        int brokenQuantity = agentOrderGoodsDao.countOfBroken(agent.getId(), agentGoods.getGoodsId());
        int repleshQuantity = newGoodsSku.getQuantity() - (noActiveQuantity + brokenQuantity);
        // 查询代理商手中剩余的未激活OBU数量
        int noActiveObuQuantity = agentOrderGoodsDao.countOfNoActiveObu(agent.getId(), agentGoods.getGoodsId());
        // 查询代理商手中剩余的已损坏未退OBU数量
        int brokenObuQuantity = agentOrderGoodsDao.countOfBrokenObu(agent.getId(), agentGoods.getGoodsId());
        int repleshObuQuantity = newGoodsSku.getQuantity() - (noActiveObuQuantity + brokenObuQuantity);
        // 再次计算代理商提标后最多可补货的数量
        int maxRepleshQuantity = Math.min(repleshQuantity, repleshObuQuantity);
        // 校验自定义补货数量必须 <= 代理商提标后最多可补货的数量
        if ("1".equals(String.valueOf(command.getIsCustomQuantity()))) {
            if (command.getCustomRepleshQuantity() > maxRepleshQuantity) {
                throw new RRException("自定义补货数量不能超过代理商提标后最多可补货的数量");
            }
        }
        ClsMarketEtcAgentOrder order = null;
        try {
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 创建申请提标补货订单
                order = new ClsMarketEtcAgentOrder();
                order.setType(REPLENISH_UP);
                order.setAgentGoodsId(agentGoods.getId());
                order.setAgentId(agent.getId());
                order.setGoodsId(goods.getId());
                order.setGoodsSkuId(agentGoods.getGoodsSkuId());
                order.setReduceSkuId(command.getNewSkuId());
                // 自定义补货数量
                if ("0".equals(String.valueOf(command.getIsCustomQuantity()))) {
                    order.setCardQuantity(maxRepleshQuantity);
                    order.setDeviceQuantity(maxRepleshQuantity);
                } else {
                    order.setCardQuantity(command.getCustomRepleshQuantity());
                    order.setDeviceQuantity(command.getCustomRepleshQuantity());
                }
                order.setConsignee(command.getConsignee());
                order.setConsigneePhone(command.getConsigneePhone());
                order.setConsigneeAddress(command.getConsigneeAddress());
                order.setStatus(OrderStatus.APPLY_REPLENISH);
                // 保存提标补货订单
                if (agentOrderDao.save(order, false) == null) {
                    throw new RRException("系统异常，提标补货失败");
                }
                // 保存提标补货订单关联表(旧规格/新规格)
                ClsMarketEtcAgentUpperOrder upperOrder = new ClsMarketEtcAgentUpperOrder();
                upperOrder.setAgentId(agent.getId());
                upperOrder.setOrderId(order.getId());
                upperOrder.setGoodsSkuId(agentGoods.getGoodsSkuId());
                // 申请提标补货不能更改当前规格
                upperOrder.setAuditGoodsSkuId(command.getNewSkuId());
                agentUpperOrderDao.save(upperOrder, false);
                // 更新代理商品信息(押金状态)
                // 申请提标补货设置待审核商品规格id
                agentGoods.setAuditGoodsSkuId(command.getNewSkuId());
                agentGoods.setStatus(DepositStatus.WAIT_FILLED);
                agentGoodsDao.save(agentGoods, false);
                // 提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
        } catch (Exception e) {
            log.error("提标补货申请失败,系统发生异常", e);
            throw new RRException("提标补货申请失败,系统发生异常");
        }
        // 重新加载代理商品信息到缓存
        agentGoodsDao.reloadToCache(agentGoods.getId());
        // 立即加载提标补货订单信息到ES
        agentOrderDao.saveES(agent, order);
    }

    /**
     * 自定义计算押金
     * @param agentGoods
     * @param customRepleshQuantity
     * @return
     */
    private BigDecimal amountDeposit(ClsMarketEtcAgentGoods agentGoods, Integer customRepleshQuantity) {
        BigDecimal deposit = BigDecimal.ZERO;
        //计算押金
        deposit = agentGoods.getDeposit().add(BigDecimal.valueOf(70L).multiply(BigDecimal.valueOf(customRepleshQuantity)));
        return deposit;
    }

    /**
     * 创建退货订单
     *
     * @param command 退货订单信息 {@link AddOrderCommand}
     */
    public void createReturnOrder(Long id, AddReturnOrderCommand command) {
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .filter(t -> t.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE || t.getAuditStatus() == AgentAuditStatus.PASSED)
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
            throw new RRException("您已申请解约,不可执行该操作");
        }
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            throw new RRException("仅允许一级代理商申请退货");
        }
        // 获取该代理商正在代理的商品
        // 包含代理商-规格信息
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        ClsMarketEtcAgentGoods agentGoods = agentGoodsList.stream()
                // 代理商品的状态为"已缴纳押金"
                .filter(t -> (t.getStatus() == DepositStatus.PAID))  ///GLD
                .findAny()
                .orElseThrow(() -> new RRException("您未代理该商品或未缴纳押金"));
        // 获取要代理的商品信息
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(agentGoods.getGoodsId()))
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .orElseThrow(() -> new RRException("该商品不存在"));
        // 获取正在代理的商品规格信息
        List<ClsMarketEtcGoodsSku> goodsSkuList = goodsSkuDao.findAllByGoods(goods.getId());
        ClsMarketEtcGoodsSku goodsSku = goodsSkuList.stream()
                .filter(t -> t.getId().equals(agentGoods.getGoodsSkuId()) && t.getStatus() == PublishStatus.ADDED)
                .findAny()
                .orElseThrow(() -> new RRException("正在代理的商品规格信息异常"));

        // 校验退还设备订单待处理(待发货，申请补货，)防止重复提交
        // 校验该代理商品是否存在"订货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentOrder::getType, ORDER);
        queryWrapper.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.SHIPPED);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper)).orElse(0) == 0) {
            throw new RRException("您还未订购该商品");
        }
        // 校验该代理商品是否存在待发货的"补货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getType, REPLENISH);
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper2.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_REPLENISH);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper2)).orElse(0) > 0) {
            throw new RRException("您已经存在待补货订单，无法创建退货申请");
        }
        // 校验该代理商品是否存在待发货的"提标补货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getType, REPLENISH_UP);
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper3.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_REPLENISH);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper3)).orElse(0) > 0) {
            throw new RRException("您已经存在待审核提标补货订单，无法创建退货申请");
        }
        // 校验该代理商品是否存在待审核的"申请退货"订单
        LambdaQueryWrapper<ClsMarketEtcAgentOrder> queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getType, RETURN);
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getAgentGoodsId, agentGoods.getId());
        queryWrapper4.eq(ClsMarketEtcAgentOrder::getStatus, OrderStatus.APPLY_RETURN);
        if (Optional.ofNullable(agentOrderDao.getMapper().selectCount(queryWrapper4)).orElse(0) > 0) {
            throw new RRException("您已经存在申请退货订单，请勿重复申请");
        }

        if (command.getCardList().isEmpty() && command.getDeviceList().isEmpty()) {
            throw new RRException("申请退货卡或设备列表不能同时为空");
        }
        // 校验待退货列表
        // 校验ETC卡号是否已存在
        List<String> existCardList = agentReturnGoodsDao.cardIsExist4Retrun(command.getCardList());
        if (!ObjectUtils.isEmpty(existCardList)) {
            StringJoiner sj = new StringJoiner("，");
            existCardList.forEach(sj::add);
            throw new RRException("退货申请失败,以下ETC卡号已退货或已申请：" + sj);
        }
        // 校验OBU标签号是否已存在
        List<String> existDeviceList = agentReturnGoodsDao.deviceIsExist4Return(command.getDeviceList());
        if (!ObjectUtils.isEmpty(existDeviceList)) {
            StringJoiner sj = new StringJoiner("，");
            existDeviceList.forEach(sj::add);
            throw new RRException("退货申请失败,以下OBU标签号已退货或已申请：" + sj);
        }
        // 校验
        List<ClsMarketEtcAgentReturnGoods> cardOrderGoodsList = (command.getCardList() != null) ?
                new ArrayList<>(command.getCardList().size()) : new ArrayList<>();
        List<ClsMarketEtcAgentReturnGoods> deviceOrderGoodsList = (command.getDeviceList() != null) ?
                new ArrayList<>(command.getDeviceList().size()) : new ArrayList<>();
        if (GoodsType.CARD.equals(command.getGoodsType())) {
            for (String sn : command.getCardList()) {
                // 创建卡退货信息
                ClsMarketEtcAgentOrderGoods orderGoods = agentOrderGoodsDao.findOneByCardSn(sn);
                ClsMarketEtcAgentReturnGoods returnGoods = new ClsMarketEtcAgentReturnGoods();
                returnGoods.setAgentId(agent.getId());
                returnGoods.setCardSn(sn);
                returnGoods.setCardStatus(orderGoods.getCardStatus());
                cardOrderGoodsList.add(returnGoods);
            }
        } else {
            for (String sn : command.getDeviceList()) {
                // 创建设备退货信息
                ClsMarketEtcAgentOrderGoods orderGoods = agentOrderGoodsDao.findOneByDeviceSn(sn);
                ClsMarketEtcAgentReturnGoods returnGoods = new ClsMarketEtcAgentReturnGoods();
                returnGoods.setAgentId(agent.getId());
                returnGoods.setDeviceSn(sn);
                returnGoods.setDeviceStatus(orderGoods.getDeviceStatus());
                deviceOrderGoodsList.add(returnGoods);
            }
        }
        ClsMarketEtcAgentOrder order = null;
        try {
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 创建申请退货订单
                order = new ClsMarketEtcAgentOrder();
                order.setType(RETURN);
                order.setAgentGoodsId(agentGoods.getId());
                order.setAgentId(agent.getId());
                order.setGoodsId(goods.getId());
                order.setCardQuantity(cardOrderGoodsList.size());
                order.setDeviceQuantity(deviceOrderGoodsList.size());
                order.setConsignee("");
                order.setConsigneePhone("");
                order.setConsigneeAddress("");
                order.setStatus(OrderStatus.APPLY_RETURN);
                order.setGoodsSkuId(agentGoods.getGoodsSkuId());
                // 保存退货订单
                if (agentOrderDao.save(order, false) == null) {
                    throw new RRException("系统异常，退货申请失败");
                }
                // 保存退货订单关联表
                // 保存申请退货商品信息-卡
                for (int i = 0; i < cardOrderGoodsList.size(); i++) {
                    ClsMarketEtcAgentReturnGoods orderGoods = cardOrderGoodsList.get(i);
                    orderGoods.setOrderId(order.getId());
                    agentReturnGoodsDao.save(orderGoods, false);
                }
                // 保存订单商品信息-设备
                for (int i = 0; i < deviceOrderGoodsList.size(); i++) {
                    ClsMarketEtcAgentReturnGoods orderGoods = deviceOrderGoodsList.get(i);
                    orderGoods.setOrderId(order.getId());
                    agentReturnGoodsDao.save(orderGoods, false);
                }
                // 提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
        } catch (Exception e) {
            log.error("退货申请失败,系统发生异常", e);
            throw new RRException("退货申请失败,系统发生异常");
        }
        // 重新加载代理商信息到缓存
        agentDao.reloadToCache(agent.getId());
        // 重新加载商品信息到缓存
        goodsDao.reloadToCache(agentGoods.getGoodsId());
        // 重新加载代理商品信息到缓存
        agentGoodsDao.reloadToCache(agentGoods.getId());
        // 立即加载退货订单信息到ES
        agentOrderDao.saveES(agent, order);
    }
}
