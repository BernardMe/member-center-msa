package com.zzjdyf.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.dao.*;
import com.zzjdyf.market.domain.entity.*;
import com.zzjdyf.market.domain.entity.enums.*;
import com.zzjdyf.market.service.DtoService;
import com.zzjdyf.market.service.app.OrderGoodsAppService;
import com.zzjdyf.market.vo.command.ConfirmPaidCommand;
import com.zzjdyf.market.vo.command.DealReturnCommand;
import com.zzjdyf.market.vo.command.DeliverCommand;
import com.zzjdyf.market.vo.dto.AgentOrderAdminDto;
import com.zzjdyf.market.vo.dto.OrderDetailGoodsAdminDto;
import com.zzjdyf.market.vo.dto.OrderGoodsAppDto;
import com.zzjdyf.market.vo.query.AgentTradePageQuery;
import com.zzjdyf.market.vo.query.IdQuery;
import com.zzjdyf.market.vo.query.OrderGoodsMngAppPageQuery;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentOrderAdminService {
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final AgentDao agentDao;
    private final GoodsDao goodsDao;
    private final GoodsSkuDao goodsSkuDao;
    private final AgentGoodsDao agentGoodsDao;
    private final AgentOrderDao agentOrderDao;
    private final AgentOrderGoodsDao agentOrderGoodsDao;
    private final AgentReturnGoodsDao agentReturnGoodsDao;
    private final AgentUpperOrderDao agentUpperOrderDao;
    private final OrderGoodsAppService orderGoodsAppService;
    private final TransactionDefinition transactionDefinition;
    private final PlatformTransactionManager platformTransactionManager;

    /**
     * 分页查询订单列表
     *
     * @param query 条件 {@link AgentTradePageQuery}
     * @return 交易列表  {@link IPage< AgentOrderAdminDto >}
     */
    public IPage<AgentOrderAdminDto> query(AgentTradePageQuery query) {
        if (!elasticsearchTemplate.indexExists(ClsMarketEtcEsTrade.class)) {
            return new Page<>();
        }
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        searchQuery.withIndices("cls_market_etc");
        searchQuery.withTypes("agent_order");
        // 设置分页
        searchQuery.withPageable(PageRequest.of(query.getPageNum() - 1, query.getPageSize()));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 查询条件-代理商姓名
        Optional.ofNullable(query.getRealName()).filter(StringUtils::hasText)
                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("realName", t)));
        // 查询条件-代理商手机号
        Optional.ofNullable(query.getPhone()).filter(StringUtils::hasText)
                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.termQuery("phone", t)));
        // 查询条件-代理商级别
        Optional.ofNullable(query.getRole())
                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.termQuery("role", t.getValue())));
        // 查询条件-订单类型(订货/补货)
        if (query.getType() == TradeType.ORDER || query.getType() == TradeType.REPLENISH
                || query.getType() == TradeType.REPLENISH_UP || query.getType() == TradeType.RETURN) {
            boolQueryBuilder.must(QueryBuilders.termQuery("type", query.getType().getValue()));
        } else {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.ORDER.getValue()));
            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.REPLENISH.getValue()));
            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.REPLENISH_UP.getValue()));
            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.RETURN.getValue()));
            boolQueryBuilder.must(boolQueryBuilder1);
        }
        // 查询条件-交易时间
        if (query.getBeginDate() != null && query.getEndDate() != null) {
            LocalDateTime beginDate = query.getBeginDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
            LocalDateTime endDate = query.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1).atStartOfDay();
            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime")
                    .from(beginDate.toInstant(ZoneOffset.of("+8")).toEpochMilli(), true)
                    .to(endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli(), true));
        }
        searchQuery.withQuery(boolQueryBuilder);
        searchQuery.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
        org.springframework.data.domain.Page<ClsMarketEtcEsTrade> page = elasticsearchTemplate.queryForPage(searchQuery.build(), ClsMarketEtcEsTrade.class);
        List<AgentOrderAdminDto> list = new ArrayList<>();
        Map<Long, ClsMarketEtcAgent> agentMap = new HashMap<>();
        Map<Long, ClsMarketEtcGoods> goodsMap = new HashMap<>();
        if (page.hasContent()) {
            List<ClsMarketEtcEsTrade> content = page.getContent();
            for (ClsMarketEtcEsTrade trade : content) {
                ClsMarketEtcAgentOrder agentOrder = agentOrderDao.findOne(trade.getTradeId());
                if (agentOrder == null) {
                    continue;
                }
                // 查询订单列表可能是历史订单信息所以不能过滤is_enable
                ClsMarketEtcAgentGoods agentGoods = agentGoodsDao.findOne(agentOrder.getAgentGoodsId());
                if (agentGoods == null) {
                    continue;
                }
                AgentOrderAdminDto agentGoodsDto = DtoService.INSTANCE.toDto(agentGoods);
                agentGoodsDto.setId(agentOrder.getId());
                agentGoodsDto.setType(agentOrder.getType());
                agentGoodsDto.setDepositStatus(agentGoods.getStatus());
                agentGoodsDto.setCardQuantity(agentOrder.getCardQuantity());
                agentGoodsDto.setDeviceQuantity(agentOrder.getDeviceQuantity());
                agentGoodsDto.setConsignee(agentOrder.getConsignee());
                agentGoodsDto.setConsigneePhone(agentOrder.getConsigneePhone());
                agentGoodsDto.setConsigneeAddress(agentOrder.getConsigneeAddress());
                agentGoodsDto.setOrderStatus(agentOrder.getStatus());
                agentGoodsDto.setCreateTime(agentOrder.getCreateTime());
                agentGoodsDto.setAuditGoodsSkuId(agentGoods.getAuditGoodsSkuId());
                agentGoodsDto.setIsReduceQuantity(agentOrder.getIsReduceQuantity());

                if (TradeType.ORDER.equals(agentOrder.getType())) {
                    agentGoodsDto.setQuantity(agentOrder.getCardQuantity());
                }
                ClsMarketEtcAgent agent;
                if (!agentMap.containsKey(agentOrder.getAgentId())) {
                    agent = agentDao.findOne(agentOrder.getAgentId());
                    agentMap.put(agentOrder.getAgentId(), agent);
                } else {
                    agent = agentMap.get(agentOrder.getAgentId());
                }
                if (agent != null) {
                    agentGoodsDto.setAgentRealName(agent.getRealName());
                    agentGoodsDto.setAgentPhone(agent.getPhone());
                    agentGoodsDto.setAgentRole(agent.getRole());
                    if (agentGoods.getStatus() == DepositStatus.PAID) {
                        // 已缴纳押金,说明正在代理该商品,检测是否可补货
                        agentGoodsDto.setCanReplenish(calculateReplenishQuantity(agent, agentGoods) > 0);
                    }
                }
                ClsMarketEtcGoods goods;
                if (!goodsMap.containsKey(agentOrder.getGoodsId())) {
                    goods = goodsDao.findOne(agentOrder.getGoodsId());
                    goodsMap.put(agentOrder.getGoodsId(), goods);
                } else {
                    goods = goodsMap.get(agentOrder.getGoodsId());
                }
                if (goods != null) {
                    agentGoodsDto.setBrand(goods.getBrand());
                    agentGoodsDto.setModel(goods.getModel());
                }
                if(agentOrder.getGoodsSkuId() != null && agentOrder.getType() == TradeType.REPLENISH){
                    // 同标补货订单则显示原有规格和押金
                    ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getGoodsSkuId());
                    agentGoodsDto.setQuantity(goods1.getQuantity());
                    agentGoodsDto.setDeposit(goods1.getDeposit());
                    agentGoodsDto.setGoodsSkuId(agentOrder.getGoodsSkuId());
                }else if(agentOrder.getReduceSkuId() != null && agentOrder.getType() == TradeType.RETURN){
                    // 降标退货订单则显示降低后规格和押金
                    ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getReduceSkuId());
                    agentGoodsDto.setQuantity(goods1.getQuantity());
                    agentGoodsDto.setDeposit(goods1.getDeposit());
                    agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                }else if(agentOrder.getReduceSkuId() != null && agentOrder.getType() == TradeType.REPLENISH_UP&&
                        (agentOrder.getStatus() == OrderStatus.WAIT_DELIVER || agentOrder.getStatus() == OrderStatus.SHIPPED)){
                    ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getReduceSkuId());
                    // 提标补货订单待发货/已发货则显示申请提升的规格和押金
                    agentGoodsDto.setQuantity(goods1.getQuantity());
                    agentGoodsDto.setDeposit(goods1.getDeposit());
                    agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                }else if(agentOrder.getReduceSkuId() != null && agentOrder.getType() == TradeType.REPLENISH_UP && (
                        agentOrder.getStatus() == OrderStatus.APPLY_REPLENISH || agentOrder.getStatus() == OrderStatus.CANCELLED ||
                                agentOrder.getStatus() == OrderStatus.DENIED)){
                    // 提标补货订单申请中/已取消/已拒绝则显示原有规格和押金
                    ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getGoodsSkuId());
                    agentGoodsDto.setQuantity(goods1.getQuantity());
                    agentGoodsDto.setDeposit(goods1.getDeposit());
                    agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                }else if(agentOrder.getReduceSkuId() == null && agentOrder.getType() == TradeType.RETURN && agentOrder.getGoodsSkuId() != null){
                    // 同标退货订单则显示原有规格和押金
                    ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getGoodsSkuId());
                    agentGoodsDto.setQuantity(goods1.getQuantity());
                    agentGoodsDto.setDeposit(goods1.getDeposit());
                    agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                }
                list.add(agentGoodsDto);
            }
        }
        return new Page<AgentOrderAdminDto>(page.getNumber(), page.getSize(), page.getTotalElements()).setRecords(list);
    }

    /**
     * 分页查询订单列表
     *
     * @param query 条件 {@link AgentTradePageQuery}
     * @return 交易列表  {@link IPage<AgentOrderAdminDto>}
     */
    public IPage<AgentOrderAdminDto> queryPersonal(AgentTradePageQuery query, LoginInfo loginInfo){
        if (!elasticsearchTemplate.indexExists(ClsMarketEtcEsTrade.class)) {
            return new Page<>();
        }

        //判断登录人的手机号在供应商是否有数据，无数据返回空，有数据返回登录人及下级供应商的数据
        ClsMarketEtcAgent clsMarketEtcAgent = agentDao.findOneByPhone(loginInfo.getPhone());
        if (clsMarketEtcAgent == null) {
            return new Page<>();
        }
        List<ClsMarketEtcAgent> clsMarketEtcAgents = new ArrayList<>();
        //判断是否增加自己的数据
        clsMarketEtcAgents = addAgent(clsMarketEtcAgent, clsMarketEtcAgents, query);
        //查询下级代理商的数据
        clsMarketEtcAgents = findChildList(clsMarketEtcAgent, clsMarketEtcAgents, query);

        //判断查询到的数据为空的时候，直接返回空
        if (clsMarketEtcAgents.size() == 0) {
            return new Page<>();
        }
        //对数据进行去重
        clsMarketEtcAgents = clsMarketEtcAgents.stream().distinct().collect(Collectors.toList());
        IPage<AgentOrderAdminDto> page = new Page<>(query.getPageNum(), query.getPageSize());
        try {
            List<AgentOrderAdminDto> agentOrderAdminDtos =batchDeal(clsMarketEtcAgents,query);
            log.info("=============查询出一共========"+agentOrderAdminDtos.size()+"条订单");
            //对数据进行排序
            agentOrderAdminDtos = agentOrderAdminDtos.stream().sorted(Comparator.comparing(AgentOrderAdminDto::getCreateTime)).collect(Collectors.toList());
            //对数据进行去重
            agentOrderAdminDtos = agentOrderAdminDtos.stream().distinct().collect(Collectors.toList());
            log.info("=============去重后查询出一共========"+agentOrderAdminDtos.size()+"条订单");
            page.setTotal(agentOrderAdminDtos.size());
            agentOrderAdminDtos = pageBySubList(agentOrderAdminDtos,query.getPageSize(),query.getPageNum());
            page.setRecords(agentOrderAdminDtos);
        }catch (Exception e){
            log.info("查询订单失败");
        }
        return page;
    }


    /**
     * 利用subList方法进行分页
     * @param list 分页数据
     * @param pagesize  页面大小
     * @param currentPage   当前页面
     */
    private static List<AgentOrderAdminDto> pageBySubList(List<AgentOrderAdminDto> list, int pagesize, int currentPage) {
        List<AgentOrderAdminDto> subList=null;
        if(list.size()>0 && pagesize!=0 && currentPage!=0){
            int totalcount = list.size();
            int pagecount = 0;

            if(pagesize > totalcount){//页面数量大于数据总数直接返回全部数据
                return list;
            }
            else {
                int m = totalcount % pagesize;//最后的余数
                if (m > 0) {
                    pagecount = totalcount / pagesize + 1;
                } else {
                    pagecount = totalcount / pagesize;
                }
                if (m == 0) {
                    subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
                } else {
                    if (currentPage == pagecount) {
                        subList = list.subList((currentPage - 1) * pagesize, totalcount);
                    } else {
                        subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
                    }
                }
            }}
        return subList;
    }

    //利用递归查询下级数据
    public List<ClsMarketEtcAgent> findChildList(ClsMarketEtcAgent clsMarketEtcAgent, List<ClsMarketEtcAgent> clsMarketEtcAgents, AgentTradePageQuery query) {
        if (AgentRole.AGENT_LEVEL_3 != clsMarketEtcAgent.getRole()) {
            LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.hasText(query.getRealName())) {
                queryWrapper.eq(ClsMarketEtcAgent::getRealName, query.getRealName());
            }
            if (StringUtils.hasText(query.getPhone())) {
                queryWrapper.eq(ClsMarketEtcAgent::getPhone, query.getPhone());
            }
            if (query.getRole() != null) {
                queryWrapper.eq(ClsMarketEtcAgent::getRole, query.getRole());
            }
            queryWrapper.orderByDesc(ClsMarketEtcAgent::getCreateTime);
            if (AgentRole.STAFF == clsMarketEtcAgent.getRole()) {
                queryWrapper.eq(ClsMarketEtcAgent::getStaffId, clsMarketEtcAgent.getId());
            } else if (AgentRole.AGENT_LEVEL_1 == clsMarketEtcAgent.getRole()) {
                queryWrapper.eq(ClsMarketEtcAgent::getAgent1Id, clsMarketEtcAgent.getId());
            } else if (AgentRole.AGENT_LEVEL_2 == clsMarketEtcAgent.getRole()) {
                queryWrapper.eq(ClsMarketEtcAgent::getAgent2Id, clsMarketEtcAgent.getId());
            }
            List<ClsMarketEtcAgent> list = agentDao.selectList(queryWrapper);
            clsMarketEtcAgents.addAll(list);
            for (ClsMarketEtcAgent clsMarketEtcAgent1 : list) {
                findChildList(clsMarketEtcAgent1, clsMarketEtcAgents, query);
            }
        }
        return clsMarketEtcAgents;
    }


    //判断是否需要显示自己的数据
    public List<ClsMarketEtcAgent> addAgent(ClsMarketEtcAgent clsMarketEtcAgent, List<ClsMarketEtcAgent> clsMarketEtcAgents, AgentTradePageQuery query) {
        if (StringUtils.hasText(query.getRealName()) && !query.getRealName().equals(clsMarketEtcAgent.getRealName())) {
            return clsMarketEtcAgents;
        }
        if (StringUtils.hasText(query.getPhone()) && !query.getPhone().equals(clsMarketEtcAgent.getPhone())) {
            return clsMarketEtcAgents;
        }
        if (query.getRole() != null && query.getRole() != clsMarketEtcAgent.getRole()) {
            return clsMarketEtcAgents;
        }

        clsMarketEtcAgents.add(clsMarketEtcAgent);
        return clsMarketEtcAgents;

    }


    /**
     * 确认已缴纳金额
     *
     * @param command 命令 {@link ConfirmPaidCommand}
     */
    @Transactional
    public void confirmPaid(LoginInfo loginInfo, ConfirmPaidCommand command) {
        // 查询当前订单是否存在
        ClsMarketEtcAgentOrder agentOrder = Optional.ofNullable(agentOrderDao.findOneNew(command.getId()))
                .orElseThrow(() -> new RRException("订单不存在"));
        ClsMarketEtcAgentGoods agentGoods = Optional.ofNullable(agentGoodsDao.findOneNew(agentOrder.getAgentGoodsId()))
                .orElseThrow(() -> new RRException("代理商品信息不存在"));
        if (agentGoods.getIsEnabled() != Boolean.TRUE) {///GLD
            throw new RRException("代理商品信息已失效");
        }
        BigDecimal paidDeposit = command.getPaidDeposit();
        ClsMarketEtcGoodsSku auditGoodsSku = null;
        if (agentGoods.getStatus() == DepositStatus.PAID) {
            throw new RRException("该订单押金已缴纳");
        }
        if (agentGoods.getStatus() == DepositStatus.RETURN) {
            throw new RRException("该订单押金已退还");
        }
        if (TradeType.ORDER.equals(agentOrder.getType())) {
            if (agentGoods.getDeposit().compareTo(paidDeposit) != 0) {
                throw new RRException("实缴纳押金与需缴纳金额不一致");
            }
        } else if (TradeType.REPLENISH_UP.equals(agentOrder.getType())) {
            // 校验押金缴纳状态
            if (!DepositStatus.WAIT_FILLED.equals(agentGoods.getStatus())) {
                throw new RRException("代理商品押金状态异常");
            }
            // 查询目标规格最新须缴纳押金是否和业务输入的实缴纳押金保持一致
             auditGoodsSku = Optional.ofNullable(goodsSkuDao.findOne(agentGoods.getAuditGoodsSkuId()))
                    .orElseThrow(() -> new RRException("代理商品信息待审核商品规格异常"));
            if (paidDeposit.compareTo(auditGoodsSku.getDeposit().subtract(agentGoods.getPaidDeposit())) != 0) {
                throw new RRException("实缴纳押金与待审核须缴纳押金不一致");
            }
        }
        //执行逻辑_更新实缴纳押金(元)
        agentGoods.setPaidDeposit(agentGoods.getPaidDeposit().add(command.getPaidDeposit()));
        agentGoods.setStatus(DepositStatus.PAID);
        agentGoodsDao.save(agentGoods);
        agentOrder.setStatus(OrderStatus.WAIT_DELIVER);
        agentOrderDao.save(agentOrder,false);
    }

    /**
     * 发货
     *
     * @param command 命令 {@link DeliverCommand}
     */
    public void deliver(LoginInfo loginInfo, DeliverCommand command) throws RRException {
        // 查询当前订单是否存在
        ClsMarketEtcAgentOrder agentOrder = Optional.ofNullable(agentOrderDao.findOneNew(command.getId()))
                .orElseThrow(() -> new RRException("订单不存在"));
        ClsMarketEtcAgentGoods agentGoods = Optional.ofNullable(agentGoodsDao.findOneNew(agentOrder.getAgentGoodsId()))
                .orElseThrow(() -> new RRException("订单不存在"));
        if (agentGoods.getIsEnabled() != Boolean.TRUE) {///GLD
            throw new RRException("代理商品信息已失效");
        }
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOneNew(agentGoods.getAgentId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            throw new RRException("仅允许一级代理商采购设备");
        }
        if (agentGoods.getStatus() == DepositStatus.RETURN) {
            throw new RRException("代理商已退还押金");
        }
        if (agentGoods.getStatus() != DepositStatus.PAID) {
            throw new RRException("代理商还未缴纳押金");
        }
        if (command.getCardList().size() != agentOrder.getCardQuantity() ||
                command.getDeviceList().size() != agentOrder.getDeviceQuantity()) {
            throw new RRException("发货数量与申请数量不一致，应该发货：" +
                    "卡" + agentOrder.getCardQuantity() + "张，设备" + agentOrder.getDeviceQuantity() + "部");
        }
        try {
            checkAllowRepleshByTradeType(agentOrder, agentGoods, agent);
        } catch (Exception e) {
            log.error("发货失败,系统发生异常", e);
            throw new RRException(e.getMessage());
        }
        // 满足发货条件
        // 校验ETC卡号是否已存在
        if(command.getCardList().size()>0){
            List<String> existCardList = agentOrderGoodsDao.cardIsExist(command.getCardList());
            if (!existCardList.isEmpty()) {
                StringJoiner sj = new StringJoiner("，");
                existCardList.forEach(sj::add);
                throw new RRException("发货失败,以下ETC卡号已存在：" + sj);
            }
        }
        if(command.getDeviceList().size()>0){
            // 校验OBU标签号是否已存在
            List<String> existDeviceList = agentOrderGoodsDao.deviceIsExist(command.getDeviceList());
            if (!existDeviceList.isEmpty()) {
                StringJoiner sj = new StringJoiner("，");
                existDeviceList.forEach(sj::add);
                throw new RRException("发货失败,以下OBU标签号已存在：" + sj);
            }
        }

        // 发货
        List<ClsMarketEtcAgentOrderGoods> cardOrderGoodsList = new ArrayList<>(command.getCardList().size());
        for (String sn : command.getCardList()) {
            // 创建卡发货信息
            ClsMarketEtcAgentOrderGoods orderGoods = new ClsMarketEtcAgentOrderGoods();
            orderGoods.setOrderId(agentOrder.getId());
            orderGoods.setAgentId(agent.getId());
            orderGoods.setStaffId(agent.getStaffId());
            orderGoods.setAgent1Id(agent.getAgent1Id());
            orderGoods.setAgent2Id(agent.getAgent2Id());
            orderGoods.setGoodsId(agentOrder.getGoodsId());
            orderGoods.setCardSn(sn);
            orderGoods.setCardStatus(ProductStatus.NO_ACTIVE);
            cardOrderGoodsList.add(orderGoods);
        }
        List<ClsMarketEtcAgentOrderGoods> deviceOrderGoodsList = new ArrayList<>(command.getDeviceList().size());
        for (String sn : command.getDeviceList()) {
            // 创建卡发货信息
            ClsMarketEtcAgentOrderGoods orderGoods = new ClsMarketEtcAgentOrderGoods();
            orderGoods.setOrderId(agentOrder.getId());
            orderGoods.setAgentId(agent.getId());
            orderGoods.setStaffId(agent.getStaffId());
            orderGoods.setAgent1Id(agent.getAgent1Id());
            orderGoods.setAgent2Id(agent.getAgent2Id());
            orderGoods.setGoodsId(agentOrder.getGoodsId());
            orderGoods.setDeviceSn(sn);
            orderGoods.setDeviceStatus(ProductStatus.NO_ACTIVE);
            deviceOrderGoodsList.add(orderGoods);
        }
        try {
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 如果是提标补货订单更新代理人代理商品信息规格
                if (TradeType.REPLENISH_UP.equals(agentOrder.getType()) && agentGoodsDao.getMapper().confirmGoodsSkuAndDeposit(
                        agentGoods.getAuditGoodsSkuId(),
                        agentGoods.getQuantity(),
                        agentGoods.getPaidDeposit(),
                        agentGoods.getId()) != 1) {
                    throw new Exception("更新代理人代理商品信息规格失败");
                }
                // 保存订单商品信息-卡
                for (int i = 0; i < cardOrderGoodsList.size(); i++) {
                    ClsMarketEtcAgentOrderGoods orderGoods = cardOrderGoodsList.get(i);
                    orderGoods = agentOrderGoodsDao.save(orderGoods, false);
                    cardOrderGoodsList.set(i, orderGoods);
                }
                // 保存订单商品信息-设备
                for (int i = 0; i < deviceOrderGoodsList.size(); i++) {
                    ClsMarketEtcAgentOrderGoods orderGoods = deviceOrderGoodsList.get(i);
                    orderGoods = agentOrderGoodsDao.save(orderGoods, false);
                    deviceOrderGoodsList.set(i, orderGoods);
                }
                // 增加商品累计销量并扣减库存
                if (goodsDao.getMapper().increaseSalesAndDecreaseStock(
                        agentGoods.getGoodsId(),
                        cardOrderGoodsList.size()) != 1) {
                    throw new Exception("增加商品累计销量失败");
                }
                // 扣减申请的卡数量和设备数量并更新订单状态
                if (agentOrderDao.getMapper().decreaseQuantityOfCardAndDevice(
                        agentOrder.getId(),
                        cardOrderGoodsList.size(),
                        deviceOrderGoodsList.size(),
                        OrderStatus.SHIPPED,
                        loginInfo.getUsername()) != 1) {
                    throw new Exception("扣减申请的卡数量和设备数量并更新订单状态失败");
                }
                // 提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
        } catch (Exception e) {
            log.error("发货失败,系统发生异常", e);
            throw new RRException("发货失败,系统发生异常");
        }
        // 重新加载代理商信息到缓存
        agentDao.reloadToCache(agent.getId());
        // 重新加载商品信息到缓存
        goodsDao.reloadToCache(agentGoods.getGoodsId());
        // 重新加载代理商品信息到缓存
        agentGoodsDao.reloadToCache(agentGoods.getId());
        // 重新加载订单信息到缓存
        agentOrderDao.reloadToCache(agentOrder.getId());
    }

    private void checkAllowRepleshByTradeType(ClsMarketEtcAgentOrder order, ClsMarketEtcAgentGoods agentGoods, ClsMarketEtcAgent agent) throws RRException {
        if (TradeType.REPLENISH.equals(order.getType())) {
            if (order.getCardQuantity() > 0) {
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
                if (order.getCardQuantity().compareTo((agentGoods.getQuantity() - (noActiveQuantity + brokenQuantity))) > 0) {
                    throw new RRException("申请补货的卡数量超过当前规格可补货卡数量");
                }
            }
            if (order.getDeviceQuantity() > 0) {
                // 查询代理商手中剩余的未激活数量
                int noActiveObuQuantity = agentOrderGoodsDao.countOfNoActiveObu(agent.getId(), agentGoods.getGoodsId());
                // 计算满足补货条件时最多应该剩余的数量 = 订货申请的设备数量 - 1
                int shouldRemainObuQuantity = BigDecimal.valueOf(agentGoods.getQuantity())
                        .subtract(BigDecimal.ONE)
                        .intValue();
                int brokenObuQuantity = agentOrderGoodsDao.countOfBrokenObu(agent.getId(), agentGoods.getGoodsId());
                // 判断剩余未激活数量是否满足补货条件
                if (shouldRemainObuQuantity < noActiveObuQuantity) {
                    throw new RRException("不满足补货条件,至少还需激活 " + (noActiveObuQuantity - shouldRemainObuQuantity) + " 个OBU后才能补货");
                }
                if (order.getDeviceQuantity().compareTo((agentGoods.getQuantity() - (noActiveObuQuantity + brokenObuQuantity))) > 0) {
                    throw new RRException("申请补货的OBU数量超过当前规格可补货卡数量");
                }
            }
        } else if (TradeType.REPLENISH_UP.equals(order.getType())) {
            ClsMarketEtcGoodsSku auditGoodsSku = Optional.ofNullable(goodsSkuDao.findOne(agentGoods.getAuditGoodsSkuId()))
                    .orElseThrow(() -> new RRException("待提标商品规格异常"));
            if (!agentGoods.getAgentId().equals(agent.getId())) {
                throw new RRException("提标补货发货订单与代理人代理商品信息不匹配");
            }
            // 查询代理商手中已激活设备卡数量
            int activatedQuantity = agentOrderGoodsDao.countOfActivated(agent.getId(), agentGoods.getGoodsId());
            // 查询代理商手中剩余的未激活卡数量
            int noActiveQuantity = agentOrderGoodsDao.countOfNoActive(agent.getId(), agentGoods.getGoodsId());
            // 询代理商手中剩余的已损坏未退回卡数量
            int brokenQuantity = agentOrderGoodsDao.countOfBroken(agent.getId(), agentGoods.getGoodsId());
            int replenishQuantity = auditGoodsSku.getQuantity() - (noActiveQuantity + brokenQuantity);
            // 查询代理商手中剩余的未激活OBU数量
            int noActiveObuQuantity = agentOrderGoodsDao.countOfNoActiveObu(agent.getId(), agentGoods.getGoodsId());
            // 查询代理商手中剩余的已损坏未退OBU数量
            int brokenObuQuantity = agentOrderGoodsDao.countOfBrokenObu(agent.getId(), agentGoods.getGoodsId());
            int replenishObuQuantity = auditGoodsSku.getQuantity() - (noActiveObuQuantity + brokenObuQuantity);
            // 再次计算代理商提标后最多可补货的数量
            int maxReplenishQuantity = Math.min(replenishQuantity, replenishObuQuantity);
            // 校验自定义补货数量必须 <= 代理商提标后最多可补货的数量
            if (order.getCardQuantity() > maxReplenishQuantity) {
                throw new RRException("自定义补货数量不能超过代理商提标后最多可补货的数量");
            }
            // 查询目标规格最新须缴纳押金
            BigDecimal auditDeposit = auditGoodsSku.getDeposit();
            BigDecimal paidDepostit = agentGoods.getPaidDeposit();
            if (auditDeposit.compareTo(paidDepostit) != 0) {
                throw new RRException("代理商id=" + agent.getId() + "待审核须缴纳押金与实际缴纳押金不匹配");
            }
            // 更新代理商提标补货代理商品规格
            agentGoods.setQuantity(auditGoodsSku.getQuantity());
        }
    }

    /**
     * 计算可补货数量
     */
    private int calculateReplenishQuantity(ClsMarketEtcAgent agent, ClsMarketEtcAgentGoods agentGoods) {
        // 查询代理商手中剩余的未激活设备数量
        int noActiveQuantity = agentOrderGoodsDao.countOfNoActive(agent.getId(), agentGoods.getGoodsId());
        // 计算满足补货条件时最多应该剩余的数量 = 订货申请的设备数量 * (1 - 补货比例)
        int shouldRemainQuantity = BigDecimal.valueOf(agentGoods.getQuantity())
                .multiply(BigDecimal.ONE.subtract(agentGoods.getReplRatio()))
                .intValue();
        // 可补货数量
        int replenishQuantity = 0;
        // 判断剩余未激活数量是否满足补货条件
        if (shouldRemainQuantity >= noActiveQuantity) {
            if (agentGoods.getFullRepl()) {
                // 全补
                // 计算可补货的数量 = 订货申请的设备数量
                replenishQuantity = agentGoods.getQuantity();
            } else {
                // 计算可补货的数量 = (订货申请的设备数量 - 未激活设备数量)
                replenishQuantity = agentGoods.getQuantity() - noActiveQuantity;
            }
        }
        return replenishQuantity;
    }

    /**
     * 根据id查询订单
     *
     * @param loginInfo 手机号
     * @param query     条件 {@link IdQuery}
     * @return 订单  {@link AgentOrderAdminDto}
     */
    public AgentOrderAdminDto detail(LoginInfo loginInfo, IdQuery query) {
        // 查询当前订单是否存在
        ClsMarketEtcAgentOrder order = Optional.ofNullable(agentOrderDao.findOne(query.getId()))
                //.filter(t -> t.getAgentId().equals(agent.getId())) 待代理商后管用户id 和 agentId 确定转换关系
                .orElseThrow(() -> new RRException("该订单不存在"));
        // 查询历史订单信息详情不过滤is_enable
        ClsMarketEtcAgentGoods agentGoods = Optional.ofNullable(agentGoodsDao.findOne(order.getAgentGoodsId()))
                .orElseThrow(() -> new RRException("代理商品信息异常"));
        AgentOrderAdminDto agentGoodsDto = DtoService.INSTANCE.toDto(agentGoods);
        LambdaQueryWrapper<ClsMarketEtcGoodsSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcGoodsSku::getGoodsId,agentGoods.getGoodsId());
        List<ClsMarketEtcGoodsSku> clsMarketEtcGoodsSkus = goodsSkuDao.selectList(wrapper);
        List<Long> list =new ArrayList<>();
        clsMarketEtcGoodsSkus.forEach(t->{
            if(agentGoods.getQuantity()>t.getQuantity()){
                list.add(t.getId());
            }
        });
        agentGoodsDto.setGoodsSkuIds(list);
        agentGoodsDto.setId(order.getId());
        agentGoodsDto.setType(order.getType());
        agentGoodsDto.setDepositStatus(agentGoods.getStatus());
        agentGoodsDto.setCardQuantity(order.getCardQuantity());
        agentGoodsDto.setDeviceQuantity(order.getDeviceQuantity());
        agentGoodsDto.setConsignee(order.getConsignee());
        agentGoodsDto.setConsigneePhone(order.getConsigneePhone());
        agentGoodsDto.setConsigneeAddress(order.getConsigneeAddress());
        agentGoodsDto.setOrderStatus(order.getStatus());
        agentGoodsDto.setCreateTime(order.getCreateTime());
        agentGoodsDto.setAuditGoodsSkuId(getAuditGoodsSkuId(order));
        agentGoodsDto.setOperator(order.getOperator());
        agentGoodsDto.setIsReduceQuantity(order.getIsReduceQuantity());
        if (TradeType.ORDER.equals(order.getType())) {
            agentGoodsDto.setQuantity(order.getCardQuantity());
        }
        if (TradeType.REPLENISH_UP.equals(order.getType())) {
            ClsMarketEtcAgentUpperOrder agentUpperOrder = agentUpperOrderDao.getMapper().findOneByOrderId(order.getId());
            ClsMarketEtcGoodsSku auditSku = goodsSkuDao.findOne(agentUpperOrder.getAuditGoodsSkuId());
            agentGoodsDto.setQuantity(auditSku.getQuantity());
        }
        if (TradeType.RETURN.equals(order.getType())) {
            if ("1".equals(String.valueOf(order.getIsReduceQuantity()))) {
                ClsMarketEtcGoodsSku reduceSku = goodsSkuDao.findOne(order.getReduceSkuId());
                agentGoodsDto.setQuantity(reduceSku.getQuantity());
            }
        }
        List<OrderDetailGoodsAdminDto> returnDtoList = getOrderDetailGoodsAdminDtoList(order);
        agentGoodsDto.setOrderDetailGoodsList(returnDtoList);
        return agentGoodsDto;
    }

    private Long getAuditGoodsSkuId(ClsMarketEtcAgentOrder order) {
        LambdaQueryWrapper<ClsMarketEtcAgentUpperOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentUpperOrder::getOrderId, order.getId());
        List<ClsMarketEtcAgentUpperOrder> upperOrder = Optional.ofNullable(agentUpperOrderDao.selectList(queryWrapper))
                .orElse(null);
        if (!upperOrder.isEmpty()) {
            return upperOrder.get(0).getAuditGoodsSkuId();
        }
        return null;
    }

    private List<OrderDetailGoodsAdminDto> getOrderDetailGoodsAdminDtoList(ClsMarketEtcAgentOrder order) {
        List<OrderDetailGoodsAdminDto> returnDtoList = new ArrayList<>();

        if (TradeType.REPLENISH_UP.equals(order.getType())) {
            LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getOrderId, order.getId());
            List<ClsMarketEtcAgentOrderGoods> returnGoods = Optional.ofNullable(agentOrderGoodsDao.selectList(queryWrapper))
                    .orElseThrow(() -> new RRException("补货订单详情异常"));
            returnGoods.forEach(t -> {
                OrderDetailGoodsAdminDto dto = new OrderDetailGoodsAdminDto();
                dto.setGoodsType(StringUtils.hasText(t.getCardSn()) ? GoodsType.CARD : GoodsType.DEVICE);
                dto.setCardSn(t.getCardSn());
                dto.setCardStatus(t.getCardStatus());
                dto.setCardStatusStr(null == t.getCardStatus() ? null : ProductStatus.valueByCode(t.getCardStatus().getValue()).getDescribe());
                dto.setDeviceSn(t.getDeviceSn());
                dto.setDeviceStatus(t.getDeviceStatus());
                dto.setDeviceStatusStr(null == t.getDeviceStatus() ? null : ProductStatus.valueByCode(t.getDeviceStatus().getValue()).getDescribe());
                returnDtoList.add(dto);
            });
        }
        if (TradeType.RETURN.equals(order.getType())) {
            LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ClsMarketEtcAgentReturnGoods::getOrderId, order.getId());
            List<ClsMarketEtcAgentReturnGoods> returnGoods = Optional.ofNullable(agentReturnGoodsDao.selectList(queryWrapper))
                    .orElseThrow(() -> new RRException("退货商品详情异常"));
            returnGoods.forEach(t -> {
                OrderDetailGoodsAdminDto dto = new OrderDetailGoodsAdminDto();
                dto.setGoodsType(StringUtils.hasText(t.getCardSn()) ? GoodsType.CARD : GoodsType.DEVICE);
                dto.setCardSn(t.getCardSn());
                dto.setCardStatus(t.getCardStatus());
                dto.setCardStatusStr(null == t.getCardStatus() ? null : ProductStatus.valueByCode(t.getCardStatus().getValue()).getDescribe());
                dto.setDeviceSn(t.getDeviceSn());
                dto.setDeviceStatus(t.getDeviceStatus());
                dto.setDeviceStatusStr(null == t.getDeviceStatus() ? null : ProductStatus.valueByCode(t.getDeviceStatus().getValue()).getDescribe());
                returnDtoList.add(dto);
            });
        }
        return returnDtoList;
    }

    /**
     * 根据id取消订单
     *
     * @param query 订单id {@link IdQuery}
     */
    public void cancel(LoginInfo loginInfo, IdQuery query) {
        // 查询当前订单是否存在
        ClsMarketEtcAgentOrder order = Optional.ofNullable(agentOrderDao.findOne(query.getId()))
                //.filter(t -> t.getAgentId().equals(agent.getId())) 待代理商后管用户id 和 agentId 确定转换关系
                .orElseThrow(() -> new RRException("该订单不存在"));
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(order.getAgentId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (OrderStatus.SHIPPED.getValue().equals(order.getStatus().getValue())
                || OrderStatus.DENIED.getValue().equals(order.getStatus().getValue())
                || OrderStatus.CANCELLED.getValue().equals(order.getStatus().getValue())) {
            throw new RRException("该订单不能执行取消");
        }
        // 查询代理商品信息
        ClsMarketEtcAgentGoods agentGoods = Optional.ofNullable(agentGoodsDao.findOne(order.getAgentGoodsId()))
                .orElseThrow(() -> new RRException("代理商品信息异常"));
        if (agentGoods.getIsEnabled() != Boolean.TRUE) {///GLD
            throw new RRException("代理商品信息已失效");
        }
        // 如果是提标补货订单已经缴纳押金，则不允许取消
        if (TradeType.REPLENISH_UP.equals(order.getType()) && agentGoods.getStatus() == DepositStatus.PAID) {
            throw new RRException("该代理商品已缴纳押金无法取消");
        }
        // 如果是退货订单查询出退货订单关联实体列表
        List<ClsMarketEtcAgentReturnGoods> returnGoodsList = null;
        if (TradeType.RETURN.equals(order.getType())) {
            returnGoodsList = agentReturnGoodsDao.selectByOrderId(order.getId());
        }
        try {
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 如果是订货订单失效代理人代理商品信息
                if (TradeType.ORDER.equals(order.getType()) && agentGoodsDao.getMapper().disEnableAgentGoods(
                        false, agentGoods.getId()) != 1) {
                    throw new Exception("订货订单关联代理人代理商品信息失效失败");
                }
                // 如果是提标补货订单回退代理人代理商品信息
                if (TradeType.REPLENISH_UP.equals(order.getType()) && agentGoodsDao.getMapper().revertGoodsSkuAndStatus(
                        agentGoods.getId()) != 1) {
                    throw new Exception("回退提标补货关联代理人代理商品信息失败");
                }
                // 如果是退货订单更新退货订单关联实体信息
                if (TradeType.RETURN.equals(order.getType())) {
                    // 退货订单关联表
                    for (int i = 0; i < returnGoodsList.size(); i++) {
                        ClsMarketEtcAgentReturnGoods returnGoods = returnGoodsList.get(i);
                        returnGoods.setStatus(1);
                        agentReturnGoodsDao.save(returnGoods, false);
                    }
                }
                // 保存订单信息(禁止保存到缓存)
                order.setStatus(OrderStatus.CANCELLED);
                order.setOperator(loginInfo.getUsername());
                agentOrderDao.save(order, false);
                // 提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
        } catch (Exception e) {
            log.error("取消订单失败,系统发生异常", e);
            throw new RRException("取消订单失败,系统发生异常");
        }
        // 重新加载代理商信息到缓存
        agentDao.reloadToCache(agent.getId());
        // 重新加载商品信息到缓存
        goodsDao.reloadToCache(agentGoods.getGoodsId());
        // 重新加载代理商品信息到缓存
        agentGoodsDao.reloadToCache(agentGoods.getId());
        // 重新加载订单信息到缓存
        agentOrderDao.reloadToCache(order.getId());
    }

    /**
     * 处理退货订单
     *
     * @param command 命令 {@link DealReturnCommand}
     */
    public void dealReturn(LoginInfo loginInfo, DealReturnCommand command) {
        // 查询当前订单是否存在
        ClsMarketEtcAgentOrder agentOrder = Optional.ofNullable(agentOrderDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("订单不存在"));
        ClsMarketEtcAgentGoods agentGoods = Optional.ofNullable(agentGoodsDao.findOne(agentOrder.getAgentGoodsId()))
                .orElseThrow(() -> new RRException("代理商品信息不存在"));
        if (agentGoods.getIsEnabled() != Boolean.TRUE) {///GLD
            throw new RRException("代理商品信息已失效");
        }
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(agentGoods.getAgentId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (!OrderStatus.APPLY_RETURN.getValue().equals(agentOrder.getStatus().getValue())) {
            throw new RRException("该订单不能执行退货处理");
        }
        LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgentReturnGoods::getOrderId, agentOrder.getId());
        // 校验待退货列表
        List<ClsMarketEtcAgentReturnGoods> returnGoodsList = Optional.ofNullable(agentReturnGoodsDao.selectList(queryWrapper))
                .orElseThrow(() -> new RRException("退货商品详情异常"));
        if (!GoodsType.CARD.equals(command.getGoodsType()) && !GoodsType.DEVICE.equals(command.getGoodsType())) {
            throw new RRException("审核退货订单商品类型异常");
        }
        String cardSn = returnGoodsList.get(0).getCardSn();
        String deviceSn = returnGoodsList.get(0).getDeviceSn();
        if (GoodsType.CARD.equals(command.getGoodsType()) && StringUtils.isEmpty(cardSn)) {
            throw new RRException("审核退货订单商品类型异常");
        }
        if (GoodsType.DEVICE.equals(command.getGoodsType()) && StringUtils.isEmpty(deviceSn)) {
            throw new RRException("审核退货订单商品类型异常");
        }

        ClsMarketEtcGoodsSku newGoodsSku = null;
        // 校验
        if ("1".equals(String.valueOf(command.getIsReduceQuantity()))) {
            if (null == command.getNewSkuId()) {
                throw new RRException("降低后规格不能为空");
            }
            // 校验代理商是否正在代理同等规格
            if (agentGoods.getGoodsSkuId().equals(command.getNewSkuId())) {
                throw new RRException("您正在代理该商品的同等规格");
            }
            // 获取要降低规格代理的商品规格信息
            newGoodsSku = Optional.ofNullable(goodsSkuDao.findOne(command.getNewSkuId()))
                    .orElseThrow(() -> new RRException("要降低规格代理的商品规格信息不存在"));
            if(agentGoods.getQuantity()<newGoodsSku.getQuantity()){
                throw new RRException("不允许选择比当前代理商商品规格高的进行降标退货");
            }

            // 用户退货完成后库存超降低后标准，无法进行降低标准
            OrderGoodsMngAppPageQuery query = new OrderGoodsMngAppPageQuery();
            query.setType(GoodsType.CARD);
            IPage<OrderGoodsAppDto> cardPage = orderGoodsAppService.query4Manage(agentOrder.getAgentId(), query);
            query.setType(GoodsType.DEVICE);
            IPage<OrderGoodsAppDto> devicePage = orderGoodsAppService.query4Manage(agentOrder.getAgentId(), query);
            long criticalQuantity = Math.max(cardPage.getTotal(), devicePage.getTotal());
            //查询退货的卡数量
            LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(ClsMarketEtcAgentReturnGoods::getOrderId, agentOrder.getId());
            queryWrapper1.isNotNull(ClsMarketEtcAgentReturnGoods::getCardSn);
            int carnNum = agentReturnGoodsDao.getMapper().selectCount(queryWrapper1);
            //查询退货设备的数量
            LambdaQueryWrapper<ClsMarketEtcAgentReturnGoods> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(ClsMarketEtcAgentReturnGoods::getOrderId, agentOrder.getId());
            queryWrapper2.isNotNull(ClsMarketEtcAgentReturnGoods::getDeviceSn);
            int deviceSnNum =agentReturnGoodsDao.getMapper().selectCount(queryWrapper2);

            if (criticalQuantity < 0) {
                throw new RRException("代理商当前商品数量异常");
            }
            if (cardPage.getTotal() - carnNum > newGoodsSku.getQuantity()) {
                throw new RRException("用户退货完成后库存超降低后标准，无法进行降低标准");
            }
            if (devicePage.getTotal() - deviceSnNum > newGoodsSku.getQuantity()) {
                throw new RRException("用户退货完成后库存超降低后标准，无法进行降低标准");
            }
            agentOrder.setIsReduceQuantity(1);
            agentOrder.setReduceSkuId(newGoodsSku.getId());
        }
        // 确定退货信息
        List<ClsMarketEtcAgentOrderGoods> cardOrderGoodsList = new ArrayList<>();
        List<ClsMarketEtcAgentOrderGoods> deviceOrderGoodsList = new ArrayList<>();
        if (GoodsType.CARD.equals(command.getGoodsType())) {
            for (ClsMarketEtcAgentReturnGoods card : returnGoodsList) {
                // 更新卡退货信息
                ClsMarketEtcAgentOrderGoods orderGoods = agentOrderGoodsDao.findOneByCardSn(card.getCardSn());
                orderGoods.setCardStatus(ProductStatus.NO_ACTIVE.equals(orderGoods.getCardStatus()) ? ProductStatus.NO_ACTIVE_RETURNED : ProductStatus.BROKEN_RETURNED);
                cardOrderGoodsList.add(orderGoods);
            }
        } else {
            for (ClsMarketEtcAgentReturnGoods device : returnGoodsList) {
                // 更新设备退货信息
                ClsMarketEtcAgentOrderGoods orderGoods = agentOrderGoodsDao.findOneByDeviceSn(device.getDeviceSn());
                orderGoods.setDeviceStatus(ProductStatus.NO_ACTIVE.equals(orderGoods.getDeviceStatus()) ? ProductStatus.NO_ACTIVE_RETURNED : ProductStatus.BROKEN_RETURNED);
                deviceOrderGoodsList.add(orderGoods);
            }
        }

        try {
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 更新审核退货订单
                agentOrder.setStatus(OrderStatus.RETURNED);
                // 更新操作员
                agentOrder.setOperator(loginInfo.getUsername());

                // 保存退货订单
                if (agentOrderDao.save(agentOrder, false) == null) {
                    throw new RRException("系统异常，退货审核失败");
                }
                // 如果是降低标准退货订单更新代理人代理商品信息规格
                if ("1".equals(String.valueOf(command.getIsReduceQuantity())) && agentGoodsDao.getMapper().confirmGoodsSkuAndDeposit4Retrun(
                        newGoodsSku.getId(),
                        newGoodsSku.getQuantity(),
                        newGoodsSku.getDeposit(),
                        newGoodsSku.getDeposit(),
                        agentGoods.getId()) != 1) {
                    throw new RRException("降低标准退货订单-更新代理人代理商品信息规格-失败");
                }
                // 保存退货订单关联表
                for (int i = 0; i < returnGoodsList.size(); i++) {
                    ClsMarketEtcAgentReturnGoods returnGoods = returnGoodsList.get(i);
                    agentReturnGoodsDao.save(returnGoods, false);
                }
                // 保存申请退货商品信息-卡
                for (int i = 0; i < cardOrderGoodsList.size(); i++) {
                    ClsMarketEtcAgentOrderGoods orderGoods = cardOrderGoodsList.get(i);
                    orderGoods.setOrderId(agentOrder.getId());
                    agentOrderGoodsDao.save(orderGoods, false);
                }
                // 保存订单商品信息-设备
                for (int i = 0; i < deviceOrderGoodsList.size(); i++) {
                    ClsMarketEtcAgentOrderGoods orderGoods = deviceOrderGoodsList.get(i);
                    orderGoods.setOrderId(agentOrder.getId());
                    agentOrderGoodsDao.save(orderGoods, false);
                }
                // 后处理商品累计销量和库存
                if (goodsDao.getMapper().decreaseSalesAndIncreaseStock(
                        agentGoods.getGoodsId(),
                        cardOrderGoodsList.size() > 0 ? cardOrderGoodsList.size() : null,
                        deviceOrderGoodsList.size() > 0 ? deviceOrderGoodsList.size() : null) != 1) {
                    throw new Exception("后处理商品累计销量和库存失败");
                }
                // 提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
        } catch (Exception e) {
            log.error("退货审核失败,系统发生异常", e);
            throw new RRException("退货审核失败,系统发生异常");
        }
        // 重新加载代理商信息到缓存
        agentDao.reloadToCache(agent.getId());
        // 重新加载商品信息到缓存
        goodsDao.reloadToCache(agentGoods.getGoodsId());
        // 重新加载代理商品信息到缓存
        agentGoodsDao.reloadToCache(agentGoods.getId());
        // 重新加载订单信息到缓存
        agentOrderDao.reloadToCache(agentOrder.getId());
    }

    /**
     * 拒绝订单
     *
     * @param query 订单id {@link IdQuery}
     */
    public void refuse(LoginInfo loginInfo, IdQuery query) {
        // 查询当前订单是否存在
        ClsMarketEtcAgentOrder order = Optional.ofNullable(agentOrderDao.findOne(query.getId()))
                //.filter(t -> t.getAgentId().equals(agent.getId())) 待代理商后管用户id 和 agentId 确定转换关系
                .orElseThrow(() -> new RRException("该订单不存在"));
        // 查询当前代理商人是否存在
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(order.getAgentId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (OrderStatus.SHIPPED.getValue().equals(order.getStatus().getValue())
                || OrderStatus.DENIED.getValue().equals(order.getStatus().getValue())
                || OrderStatus.CANCELLED.getValue().equals(order.getStatus().getValue())) {
            throw new RRException("该订单不能执行拒绝");
        }
        // 查询代理商品信息
        ClsMarketEtcAgentGoods agentGoods = Optional.ofNullable(agentGoodsDao.findOne(order.getAgentGoodsId()))
                .orElseThrow(() -> new RRException("代理商品信息异常"));
        if (agentGoods.getIsEnabled() != Boolean.TRUE) {///GLD
            throw new RRException("代理商品信息已失效");
        }
        // 如果是退货订单查询出退货订单关联实体列表
        List<ClsMarketEtcAgentReturnGoods> returnGoodsList = null;
        if (TradeType.RETURN.equals(order.getType())) {
            returnGoodsList = agentReturnGoodsDao.selectByOrderId(order.getId());
        }
        // 已缴纳押金可以拒绝
        //if (agentGoods.getStatus() == DepositStatus.PAID) {
        //    throw new RRException("该代理商品已缴纳押金无法拒绝");
        //}
        try {
            // 开启事务(⚠️事务中保存数据时禁止保存到缓存)
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
            try {
                // 如果是订货订单失效代理人代理商品信息
                if (TradeType.ORDER.equals(order.getType()) && agentGoodsDao.getMapper().disEnableAgentGoods(
                        false, agentGoods.getId()) != 1) {
                    throw new Exception("订货订单关联代理人代理商品信息失效失败");
                }
                // 如果是提标补货订单回退代理人代理商品信息
                if (TradeType.REPLENISH_UP.equals(order.getType()) && agentGoodsDao.getMapper().revertGoodsSkuAndStatus(
                        agentGoods.getId()) != 1) {
                    throw new Exception("回退提标补货关联代理人代理商品信息失败");
                }
                // 如果是退货订单更新退货订单关联实体信息
                if (TradeType.RETURN.equals(order.getType())) {
                    // 退货订单关联表
                    for (int i = 0; i < returnGoodsList.size(); i++) {
                        ClsMarketEtcAgentReturnGoods returnGoods = returnGoodsList.get(i);
                        returnGoods.setStatus(1);
                        agentReturnGoodsDao.save(returnGoods, false);
                    }
                }
                // 保存订单信息(禁止保存到缓存)
                order.setStatus(OrderStatus.DENIED);
                agentOrderDao.save(order, false);
                // 提交事务
                platformTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 执行异常,回滚事务
                platformTransactionManager.rollback(transactionStatus);
                throw e;
            }
        } catch (Exception e) {
            log.error("拒绝订单失败,系统发生异常", e);
            throw new RRException("拒绝订单失败,系统发生异常");
        }
        // 重新加载代理商信息到缓存
        agentDao.reloadToCache(agent.getId());
        // 重新加载商品信息到缓存
        goodsDao.reloadToCache(agentGoods.getGoodsId());
        // 重新加载代理商品信息到缓存
        agentGoodsDao.reloadToCache(agentGoods.getId());
        // 重新加载订单信息到缓存
        agentOrderDao.reloadToCache(order.getId());
    }

    public List<AgentOrderAdminDto> batchDeal(List<ClsMarketEtcAgent> data,AgentTradePageQuery query) throws InterruptedException {
        List<AgentOrderAdminDto> agentOrderAdminDtos =new CopyOnWriteArrayList<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(20,
                20,
                30,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        );

        try {
            CountDownLatch countDownLatch = new CountDownLatch(data.size());
            for(ClsMarketEtcAgent clsMarketEtcAgent:data){
                Runnable runnable =new Runnable() {
                    @Override
                    public void run() {
                        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
                        searchQuery.withIndices("cls_market_etc");
                        searchQuery.withTypes("agent_order");
                        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                        // 查询条件-代理商姓名
                        Optional.ofNullable(query.getRealName()).filter(StringUtils::hasText)
                                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("realName", t)));
                        // 查询条件-代理商手机号
                        Optional.ofNullable(query.getPhone()).filter(StringUtils::hasText)
                                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.termQuery("phone", t)));
                        // 查询条件-代理商级别
                        Optional.ofNullable(query.getRole())
                                .ifPresent(t -> boolQueryBuilder.must(QueryBuilders.termQuery("role", t.getValue())));
                        boolQueryBuilder.must(QueryBuilders.termQuery("agentId",clsMarketEtcAgent.getId()));
                        // 查询条件-订单类型(订货/补货)
                        // 查询条件-订单类型(订货/补货)
                        if (query.getType() == TradeType.ORDER || query.getType() == TradeType.REPLENISH
                                || query.getType() == TradeType.REPLENISH_UP || query.getType() == TradeType.RETURN) {
                            boolQueryBuilder.must(QueryBuilders.termQuery("type", query.getType().getValue()));
                        } else {
                            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
                            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.ORDER.getValue()));
                            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.REPLENISH.getValue()));
                            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.REPLENISH_UP.getValue()));
                            boolQueryBuilder1.should(QueryBuilders.termQuery("type", TradeType.RETURN.getValue()));
                            boolQueryBuilder.must(boolQueryBuilder1);
                        }
                        // 查询条件-交易时间
                        if (query.getBeginDate() != null && query.getEndDate() != null) {
                            LocalDateTime beginDate = query.getBeginDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
                            LocalDateTime endDate = query.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1).atStartOfDay();
                            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime")
                                    .from(beginDate.toInstant(ZoneOffset.of("+8")).toEpochMilli(), true)
                                    .to(endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli(), true));
                        }
                        searchQuery.withQuery(boolQueryBuilder);
                        searchQuery.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
                        List<ClsMarketEtcEsTrade> trades = elasticsearchTemplate.queryForList(searchQuery.build(), ClsMarketEtcEsTrade.class);
                        List<AgentOrderAdminDto> list = new ArrayList<>();
                        Map<Long, ClsMarketEtcAgent> agentMap = new HashMap<>();
                        Map<Long, ClsMarketEtcGoods> goodsMap = new HashMap<>();
                        for (ClsMarketEtcEsTrade trade : trades) {
                            ClsMarketEtcAgentOrder agentOrder = agentOrderDao.findOne(trade.getTradeId());
                            if (agentOrder == null) {
                                continue;
                            }
                            ClsMarketEtcAgentGoods agentGoods = agentGoodsDao.findOne(agentOrder.getAgentGoodsId());
                            if (agentGoods == null) {
                                continue;
                            }
                            AgentOrderAdminDto agentGoodsDto = DtoService.INSTANCE.toDto(agentGoods);
                            agentGoodsDto.setId(agentOrder.getId());
                            agentGoodsDto.setType(agentOrder.getType());
                            agentGoodsDto.setDepositStatus(agentGoods.getStatus());
                            agentGoodsDto.setCardQuantity(agentOrder.getCardQuantity());
                            agentGoodsDto.setDeviceQuantity(agentOrder.getDeviceQuantity());
                            agentGoodsDto.setConsignee(agentOrder.getConsignee());
                            agentGoodsDto.setConsigneePhone(agentOrder.getConsigneePhone());
                            agentGoodsDto.setConsigneeAddress(agentOrder.getConsigneeAddress());
                            agentGoodsDto.setOrderStatus(agentOrder.getStatus());
                            agentGoodsDto.setCreateTime(agentOrder.getCreateTime());
                            agentGoodsDto.setAuditGoodsSkuId(agentGoods.getAuditGoodsSkuId());
                            agentGoodsDto.setIsReduceQuantity(agentOrder.getIsReduceQuantity());
                            if(agentOrder.getGoodsSkuId()!=null){
                                agentGoodsDto.setGoodsSkuId(agentOrder.getGoodsSkuId());
                            }
                            if (TradeType.ORDER.equals(agentOrder.getType())) {
                                agentGoodsDto.setQuantity(agentOrder.getCardQuantity());
                            }
                            if (TradeType.REPLENISH_UP.equals(agentOrder.getType())) {
                                ClsMarketEtcAgentUpperOrder agentUpperOrder = agentUpperOrderDao.getMapper().findOneByOrderId(agentOrder.getId());
                                ClsMarketEtcGoodsSku auditSku = goodsSkuDao.findOne(agentUpperOrder.getAuditGoodsSkuId());
                                agentGoodsDto.setQuantity(auditSku.getQuantity());
                            }
                            if (TradeType.RETURN.equals(agentOrder.getType())) {
                                if ("1".equals(String.valueOf(agentOrder.getIsReduceQuantity()))) {
                                    ClsMarketEtcGoodsSku reduceSku = goodsSkuDao.findOne(agentOrder.getReduceSkuId());
                                    agentGoodsDto.setQuantity(reduceSku.getQuantity());
                                }
                            }

                            ClsMarketEtcAgent agent;
                            if (!agentMap.containsKey(agentOrder.getAgentId())) {
                                agent = agentDao.findOne(agentOrder.getAgentId());
                                agentMap.put(agentOrder.getAgentId(), agent);
                            } else {
                                agent = agentMap.get(agentOrder.getAgentId());
                            }
                            if (agent != null) {
                                agentGoodsDto.setAgentRealName(agent.getRealName());
                                agentGoodsDto.setAgentPhone(agent.getPhone());
                                agentGoodsDto.setAgentRole(agent.getRole());
                                if (agentGoods.getStatus() == DepositStatus.PAID) {
                                    // 已缴纳押金,说明正在代理该商品,检测是否可补货
                                    agentGoodsDto.setCanReplenish(calculateReplenishQuantity(agent, agentGoods) > 0);
                                }
                            }
                            ClsMarketEtcGoods goods;
                            if (!goodsMap.containsKey(agentOrder.getGoodsId())) {
                                goods = goodsDao.findOne(agentOrder.getGoodsId());
                                goodsMap.put(agentOrder.getGoodsId(), goods);
                            } else {
                                goods = goodsMap.get(agentOrder.getGoodsId());
                            }
                            if (goods != null) {
                                agentGoodsDto.setBrand(goods.getBrand());
                                agentGoodsDto.setModel(goods.getModel());
                            }
                            if(agentOrder.getGoodsSkuId()!=null&&agentOrder.getType()==TradeType.REPLENISH){
                                ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getGoodsSkuId());
                                agentGoodsDto.setQuantity(goods1.getQuantity());
                                agentGoodsDto.setDeposit(goods1.getDeposit());
                                agentGoodsDto.setGoodsSkuId(agentOrder.getGoodsSkuId());
                            }else if(agentOrder.getReduceSkuId()!=null&&agentOrder.getType()==TradeType.RETURN){
                                ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getReduceSkuId());
                                agentGoodsDto.setQuantity(goods1.getQuantity());
                                agentGoodsDto.setDeposit(goods1.getDeposit());
                                agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                            }else if(agentOrder.getReduceSkuId()!=null&&agentOrder.getType()==TradeType.REPLENISH_UP&&
                                    (agentOrder.getStatus()==OrderStatus.WAIT_DELIVER||agentOrder.getStatus()==OrderStatus.SHIPPED)){
                                ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getReduceSkuId());
                                agentGoodsDto.setQuantity(goods1.getQuantity());
                                agentGoodsDto.setDeposit(goods1.getDeposit());
                                agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                            }else if(agentOrder.getReduceSkuId()!=null&&agentOrder.getType()==TradeType.REPLENISH_UP&&(
                                    agentOrder.getStatus()==OrderStatus.APPLY_REPLENISH||agentOrder.getStatus()==OrderStatus.CANCELLED||
                                            agentOrder.getStatus()==OrderStatus.DENIED)){
                                ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getGoodsSkuId());
                                agentGoodsDto.setQuantity(goods1.getQuantity());
                                agentGoodsDto.setDeposit(goods1.getDeposit());
                                agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                            }else if(agentOrder.getReduceSkuId()==null&&agentOrder.getType()==TradeType.RETURN&&agentOrder.getGoodsSkuId()!=null){
                                ClsMarketEtcGoodsSku goods1 = goodsSkuDao.findOneNew(agentOrder.getGoodsSkuId());
                                agentGoodsDto.setQuantity(goods1.getQuantity());
                                agentGoodsDto.setDeposit(goods1.getDeposit());
                                agentGoodsDto.setGoodsSkuId(agentOrder.getReduceSkuId());
                            }
                            list.add(agentGoodsDto);
                            agentOrderAdminDtos.addAll(list);
                        }
                        countDownLatch.countDown();
                    }

                };
                executor.execute(runnable);
            }
            countDownLatch.await();

        } finally {
            // 关闭线程池，释放资源
            executor.shutdown();
        }
        return agentOrderAdminDtos;
    }



}



