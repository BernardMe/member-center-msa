package com.cheshun.market.service.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentOrderGoods;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.ProductStatus;
import com.cheshun.market.service.DtoService;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.domain.dao.AgentOrderGoodsDao;
import com.cheshun.market.vo.command.ConfirmStatusCommand;
import com.cheshun.market.vo.dto.OrderGoodsAppDto;
import com.cheshun.market.vo.query.IdQuery;
import com.cheshun.market.vo.query.OrderGoodsAppPageQuery;
import com.cheshun.market.vo.query.OrderGoodsMngAppPageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cheshun.market.domain.entity.enums.AgentRole.*;

/**
 * @author wangzhuo
 * Created on 20210802
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderGoodsAppService {
    private final AgentDao agentDao;
    private final AgentOrderGoodsDao agentOrderGoodsDao;

    /**
     * 分页查询我的或下级成员的订单商品列表
     *
     * @param id    代理商id
     * @param query 条件 {@link OrderGoodsAppPageQuery}
     * @return 订单商品列表  {@link IPage< OrderGoodsAppDto >}
     */
    public IPage<OrderGoodsAppDto> query(Long id, OrderGoodsAppPageQuery query) {
        // 检测用户是否是代理商
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (query.getAgentId() != null && !query.getAgentId().equals(agent.getId())) {
            // 校验要查看的代理商是否是当前代理商的下级
            ClsMarketEtcAgent member = Optional.ofNullable(agentDao.findOne(query.getAgentId()))
                    .orElseThrow(() -> new RRException("该用户不是代理商"));
            if (!member.getStaffId().equals(agent.getId()) &&
                    !member.getAgent1Id().equals(agent.getId()) &&
                    !member.getAgent2Id().equals(agent.getId())) {
                throw new RRException("该用户不是您的下级代理商");
            }
            // 当前代理商信息切换为要查询的下级成员
            agent = member;
        }
        QueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new QueryWrapper<>();
        switch (agent.getRole()) {
            case STAFF:
                // 当前代理商是员工,返回空列表
            case AGENT_LEVEL_2:
                // 当前代理商是二级代理商,返回空列表
            case AGENT_LEVEL_3:
                // 当前代理商是三级代理商,返回空列表
                return new Page<>();
            case AGENT_LEVEL_1:
                // 当前代理商是一级代理商
                queryWrapper.eq("og.agent_id", agent.getId());
                break;
        }
        switch (query.getType()) {
            case CARD:
                // 查询卡(ETC卡号不为空)
                queryWrapper.isNotNull("og.card_sn");
                if (query.getStatus() != null) {
                    if (!ProductStatus.BROKEN_RETURNED.equals(query.getStatus())) {
                        queryWrapper.eq("og.card_status", query.getStatus());
                    } else {
                        queryWrapper.in("og.card_status", ProductStatus.BROKEN_RETURNED, ProductStatus.NO_ACTIVE_RETURNED);
                    }
                }
                break;
            case DEVICE:
                // 查询设备(ETC卡号为空,OBU标签号不为空)
                queryWrapper.isNull("og.card_sn");
                queryWrapper.isNotNull("og.device_sn");
                if (query.getStatus() != null) {
                    if (!ProductStatus.BROKEN_RETURNED.equals(query.getStatus())) {
                        queryWrapper.eq("og.device_status", query.getStatus());
                    } else {
                        queryWrapper.in("og.device_status", ProductStatus.BROKEN_RETURNED, ProductStatus.NO_ACTIVE_RETURNED);
                    }
                }
                break;
        }
        queryWrapper.orderByDesc("og.create_time");
        IPage<ClsMarketEtcAgentOrderGoods> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentOrderGoodsDao.getMapper().queryPage4Agent(page, queryWrapper);
        List<OrderGoodsAppDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<OrderGoodsAppDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    /**
     * 分页查询我的订单商品管理列表
     *
     * @param id    代理商id
     * @param query     条件 {@link OrderGoodsMngAppPageQuery}
     * @return 我的订单商品管理列表  {@link IPage<OrderGoodsAppDto>}
     */
    public IPage<OrderGoodsAppDto> query4Manage(Long id, OrderGoodsMngAppPageQuery query) {
        // 检测用户是否是代理商
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (query.getAgentId() != null && !query.getAgentId().equals(agent.getId())) {
            // 校验要查看的代理商是否是当前代理商的下级
            ClsMarketEtcAgent member = Optional.ofNullable(agentDao.findOne(query.getAgentId()))
                    .orElseThrow(() -> new RRException("该用户不是代理商"));
            if (!member.getStaffId().equals(agent.getId()) &&
                    !member.getAgent1Id().equals(agent.getId()) &&
                    !member.getAgent2Id().equals(agent.getId())) {
                throw new RRException("该用户不是您的下级代理商");
            }
            // 当前代理商信息切换为要查询的下级成员
            agent = member;
        }
        QueryWrapper<ClsMarketEtcAgentOrderGoods> queryWrapper = new QueryWrapper<>();
        String snType = null;
        switch (agent.getRole()) {
            case STAFF:
                // 当前代理商是员工,返回空列表
            case AGENT_LEVEL_2:
                // 当前代理商是二级代理商,返回空列表
            case AGENT_LEVEL_3:
                // 当前代理商是三级代理商,返回空列表
                return new Page<>();
            case AGENT_LEVEL_1:
                // 当前代理商是一级代理商
                queryWrapper.eq("og.agent_id", agent.getId());
                break;
        }
        switch (query.getType()) {
            case CARD:
                // 查询卡(ETC卡号不为空)
                queryWrapper.isNotNull("og.card_sn");
                queryWrapper.and(wrapper ->
                        wrapper.eq("og.card_status", ProductStatus.NO_ACTIVE)
                        .or()
                        .eq("og.card_status", ProductStatus.BROKEN));
                snType = "card_sn";
                break;
            case DEVICE:
                // 查询设备(ETC卡号为空,OBU标签号不为空)
                queryWrapper.isNull("og.card_sn");
                queryWrapper.isNotNull("og.device_sn");
                queryWrapper.and(wrapper ->
                        wrapper.eq("og.device_status", ProductStatus.NO_ACTIVE)
                                .or()
                                .eq("og.device_status", ProductStatus.BROKEN));
                snType = "device_sn";
                break;
        }

        IPage<ClsMarketEtcAgentOrderGoods> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentOrderGoodsDao.getMapper().queryPage4AgentMng(page, queryWrapper, snType);
        List<OrderGoodsAppDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<OrderGoodsAppDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    /**
     * 确定损坏的卡/设备已退还
     *
     * @param id      代理商id
     * @param command 条件 {@link IdQuery}
     */
    @Transactional
    public void confirmBroken(Long id, ConfirmStatusCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        command.getIds().forEach(tt ->{
            ClsMarketEtcAgentOrderGoods orderGoods = Optional.ofNullable(agentOrderGoodsDao.findOne(tt))
                    .filter(t -> t.getAgentId().equals(agent.getId()))
                    .orElseThrow(() -> new RRException("该设备号："+tt+"不存在"));
            if (command.getType().equals(1)) {
                if (!StringUtils.hasText(orderGoods.getCardSn())) {
                    throw new RRException("该卡号："+tt+"卡sn异常");
                }
                if (orderGoods.getCardStatus() == ProductStatus.BROKEN) {
                    return;
                }
                if (orderGoods.getCardStatus() == ProductStatus.BROKEN_RETURNED) {
                    throw new RRException("该卡号："+tt+"卡sn已退回,不允许标记损坏");
                }
                orderGoods.setCardStatus(ProductStatus.BROKEN);
                agentOrderGoodsDao.save(orderGoods);
            } else if (command.getType().equals(2)) {
                if (!StringUtils.hasText(orderGoods.getDeviceSn())) {
                    throw new RRException("该设备号："+tt+"异常");
                }
                if (orderGoods.getDeviceStatus() == ProductStatus.BROKEN) {
                    return;
                }
                if (orderGoods.getCardStatus() == ProductStatus.BROKEN_RETURNED) {
                    throw new RRException("该设备号："+tt+"已退回,不允许标记损坏");
                }
                orderGoods.setDeviceStatus(ProductStatus.BROKEN);
                agentOrderGoodsDao.save(orderGoods);
            }
        });
    }

    private OrderGoodsAppDto toDto(ClsMarketEtcAgentOrderGoods entity) {
        OrderGoodsAppDto dto = DtoService.INSTANCE.toAppDto(entity);
        Optional.ofNullable(dto.getCardStatus()).ifPresent(t -> dto.setCardStatusName(t.getDescribe()));
        Optional.ofNullable(dto.getDeviceStatus()).ifPresent(t -> dto.setDeviceStatusName(t.getDescribe()));
        return dto;
    }
}
