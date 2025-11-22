package com.cheshun.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentOrderGoods;
import com.cheshun.market.domain.entity.ClsMarketEtcGoods;
import com.cheshun.market.domain.entity.enums.ProductStatus;
import com.cheshun.market.service.DtoService;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.config.common.LoginInfo;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.domain.dao.AgentOrderGoodsDao;
import com.cheshun.market.domain.dao.GoodsDao;
import com.cheshun.market.vo.dto.OrderGoodsAdminDto;
import com.cheshun.market.vo.query.AgentPageQuery;
import com.cheshun.market.vo.query.IdQuery;
import com.cheshun.market.vo.query.OrderGoodsPageQuery;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/7/28 11:33 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderGoodsAdminService {
    private final AgentDao agentDao;
    private final AgentOrderGoodsDao agentOrderGoodsDao;
    private final GoodsDao goodsDao;
    private final AgentAdminService agentAdminService;

    /**
     * 分页查询订单商品
     *
     * @param query 条件 {@link OrderGoodsPageQuery}
     * @return 订单商品列表  {@link IPage< OrderGoodsAdminDto >}
     */
    public IPage<OrderGoodsAdminDto> query(OrderGoodsPageQuery query) {
        boolean joinAgent = StringUtils.hasText(query.getRealName()) ||
                StringUtils.hasText(query.getPhone()) ||
                query.getRole() != null;
        QueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(query.getRealName())) {
            queryWrapper.eq("agent.real_name", query.getRealName());
        }
        if (StringUtils.hasText(query.getPhone())) {
            queryWrapper.eq("agent.phone", query.getPhone());
        }
        if (query.getRole() != null) {
            queryWrapper.eq("agent.role", query.getRole());
        }
        switch (query.getType()) {
            case CARD:
                // 查询卡
                if (StringUtils.hasText(query.getCardSn())) {
                    queryWrapper.eq("og.card_sn", query.getCardSn());
                }
                if (query.getCardStatus() != null) {
                    queryWrapper.eq("og.card_status", query.getCardStatus());
                }
                queryWrapper.isNotNull("og.card_sn");
                break;
            case DEVICE:
                // 查询设备
                if (StringUtils.hasText(query.getDeviceSn())) {
                    queryWrapper.eq("og.device_sn", query.getDeviceSn());
                }
                if (query.getDeviceStatus() != null) {
                    queryWrapper.eq("og.device_status", query.getDeviceStatus());
                }
                queryWrapper.isNull("og.card_sn");
                queryWrapper.isNotNull("og.device_sn");
                break;
        }
        queryWrapper.orderByDesc("og.create_time");
        IPage<ClsMarketEtcAgentOrderGoods> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentOrderGoodsDao.getMapper().queryPage(page, joinAgent, queryWrapper);
        List<OrderGoodsAdminDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<OrderGoodsAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    /**
     * 确定损坏的卡/设备已退还
     *
     * @param query 条件 {@link IdQuery}
     */
    public void confirmBrokenReturned(IdQuery query) {
        ClsMarketEtcAgentOrderGoods orderGoods = Optional.ofNullable(agentOrderGoodsDao.findOne(query.getId()))
                .orElseThrow(() -> new RRException("该设备不存在"));
        if (StringUtils.hasText(orderGoods.getCardSn())) {
            if (orderGoods.getCardStatus() == ProductStatus.BROKEN_RETURNED) {
                return;
            }
            if (orderGoods.getCardStatus() != ProductStatus.BROKEN) {
                throw new RRException("该卡未损坏");
            }
            orderGoods.setCardStatus(ProductStatus.BROKEN_RETURNED);
        } else if (StringUtils.hasText(orderGoods.getDeviceSn())) {
            if (orderGoods.getDeviceStatus() == ProductStatus.BROKEN_RETURNED) {
                return;
            }
            if (orderGoods.getDeviceStatus() != ProductStatus.BROKEN) {
                throw new RRException("该设备未损坏");
            }
            orderGoods.setDeviceStatus(ProductStatus.BROKEN_RETURNED);
        }
        agentOrderGoodsDao.save(orderGoods);
    }

    private OrderGoodsAdminDto toDto(ClsMarketEtcAgentOrderGoods entity) {
        OrderGoodsAdminDto dto = DtoService.INSTANCE.toDto(entity);
        ClsMarketEtcAgent agent = agentDao.findOne(dto.getAgentId());
        if (agent != null) {
            dto.setAgentPhone(agent.getPhone());
            dto.setAgentRealName(agent.getRealName());
            dto.setAgentRole(agent.getRole());
        }
        ClsMarketEtcGoods goods = goodsDao.findOne(dto.getGoodsId());
        if (goods != null) {
            dto.setBrand(goods.getBrand());
            dto.setModel(goods.getModel());
        }
        return dto;
    }


    public IPage<OrderGoodsAdminDto> queryPersonal(OrderGoodsPageQuery query, LoginInfo loginInfo) {
        IPage<ClsMarketEtcAgentOrderGoods> page = new Page<>(query.getPageNum(), query.getPageSize());
        AgentPageQuery agentPageQuery =new AgentPageQuery();
        agentPageQuery.setRealName(query.getRealName());
        agentPageQuery.setPhone(query.getPhone());
        agentPageQuery.setRole(query.getRole());
            //判断登录人的手机号在供应商是否有数据，无数据返回空，有数据返回登录人及下级供应商的数据
        ClsMarketEtcAgent clsMarketEtcAgent = agentDao.findOneByPhone(loginInfo.getPhone());
        if(clsMarketEtcAgent==null){
            return new Page<OrderGoodsAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(null);
        }
        List<ClsMarketEtcAgent> clsMarketEtcAgents =new ArrayList<>();
        //判断是否增加自己的数据
        clsMarketEtcAgents = agentAdminService.addAgent(clsMarketEtcAgent,clsMarketEtcAgents,agentPageQuery);
        //查询下级代理商的数据
        clsMarketEtcAgents = agentAdminService.findChildList(clsMarketEtcAgent,clsMarketEtcAgents,agentPageQuery);
        //判断查询到的数据为空的时候，直接返回空
        if(clsMarketEtcAgents.size()==0){
            return new Page<OrderGoodsAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(null);
        }
        //对数据进行去重
        clsMarketEtcAgents = clsMarketEtcAgents.stream().distinct().collect(Collectors.toList());

        List<ClsMarketEtcAgentOrderGoods> clsMarketEtcAgentOrderGoods =new ArrayList<>();
        for(ClsMarketEtcAgent marketEtcAgent:clsMarketEtcAgents){
            LambdaQueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getAgentId, marketEtcAgent.getId());
            switch (query.getType()) {
                case CARD:
                    // 查询卡
                    if (StringUtils.hasText(query.getCardSn())) {
                        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getCardSn, query.getCardSn());
                    }
                    if (query.getCardStatus() != null) {
                        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getCardStatus, query.getCardStatus());
                    }
                    queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getCardStatus);
                    break;
                case DEVICE:
                    // 查询设备
                    if (StringUtils.hasText(query.getDeviceSn())) {
                        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getDeviceSn, query.getDeviceSn());
                    }
                    if (query.getDeviceStatus() != null) {
                        queryWrapper.eq(ClsMarketEtcAgentOrderGoods::getDeviceStatus, query.getDeviceStatus());
                    }
                    queryWrapper.isNotNull(ClsMarketEtcAgentOrderGoods::getDeviceStatus);
                    queryWrapper.isNull(ClsMarketEtcAgentOrderGoods::getCardSn);

                    break;
            }
            List<ClsMarketEtcAgentOrderGoods> clsMarketEtcAgentGoods = agentOrderGoodsDao.selectList(queryWrapper);
            clsMarketEtcAgentOrderGoods.addAll(clsMarketEtcAgentGoods);
        }

        //判断查询到的数据为空的时候，直接返回空
        if(clsMarketEtcAgentOrderGoods.size()==0){
            return new Page<OrderGoodsAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(null);
        }
        //对数据进行去重
        clsMarketEtcAgentOrderGoods = clsMarketEtcAgentOrderGoods.stream().distinct().collect(Collectors.toList());
        //对数据进行排序
        clsMarketEtcAgentOrderGoods = clsMarketEtcAgentOrderGoods.stream().sorted(Comparator.comparing(ClsMarketEtcAgentOrderGoods::getCreateTime).reversed()).collect(Collectors.toList());

        page.setTotal(clsMarketEtcAgentOrderGoods.size());
        clsMarketEtcAgentOrderGoods = pageBySubList(clsMarketEtcAgentOrderGoods,query.getPageSize(),query.getPageNum());
        page.setRecords(clsMarketEtcAgentOrderGoods);
        List<OrderGoodsAdminDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<OrderGoodsAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }


    /**
     * 利用subList方法进行分页
     * @param list 分页数据
     * @param pagesize  页面大小
     * @param currentPage   当前页面
     */
    public static List<ClsMarketEtcAgentOrderGoods> pageBySubList(List<ClsMarketEtcAgentOrderGoods> list, int pagesize, int currentPage) {
        List<ClsMarketEtcAgentOrderGoods> subList=null;
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

}
