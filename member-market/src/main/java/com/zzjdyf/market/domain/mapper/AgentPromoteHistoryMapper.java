package com.zzjdyf.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.zzjdyf.market.domain.entity.enums.BonusStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 阿隆
 * Created on 2021/8/11 10:10 上午.
 */
@Mapper
@Repository
public interface AgentPromoteHistoryMapper extends BaseMapper<ClsMarketEtcAgentPromoteHistory> {
    /**
     * 分页查询需要查询卡号的推广记录列表
     *
     * @param page 分页信息
     * @return 推广记录列表
     */
    @Select("select * from cls_market_etc_agent_promote_history " +
            // 查询条件-ETC卡号不存在
            "where card_sn is null " +
            "order by id asc")
    IPage<ClsMarketEtcAgentPromoteHistory> queryCanQueryActiveCardList(
            IPage<ClsMarketEtcAgentPromoteHistory> page);

    /**
     * 分页查询可结算激活奖励的推广记录列表
     *
     * @param page              分页信息
     * @param activeBonusStatus 激活奖励待结算状态
     * @return 推广记录列表
     */
    @Select("select * from cls_market_etc_agent_promote_history " +
            // 查询条件-ETC卡号存在
            "where card_sn is not null " +
            // 查询条件-激活奖励未结算
            "and active_bonus_status = #{activeBonusStatus} " +
            "order by id asc")
    IPage<ClsMarketEtcAgentPromoteHistory> queryCanSettleActiveBonusList(
            IPage<ClsMarketEtcAgentPromoteHistory> page,
            @Param("activeBonusStatus") BonusStatus activeBonusStatus);

    /**
     * 分页查询可查询首次消费订单的推广记录列表
     *
     * @param page           分页信息
     * @param startQueryDays 开始日期
     * @param stopQueryDays  结束日期
     * @return 推广记录列表
     */
    @Select("select * from cls_market_etc_agent_promote_history " +
            // 查询条件-激活奖励已结算
            "where active_bonus_status = #{activeBonusStatus} " +
            // 查询条件-首次消费订单号不存在
            "and first_consume_order_sn is null " +
            // 查询条件-激活xx时间后开始查询首次消费，xx时间后停止查询首次消费，不可能一直查询
            "and now() between date_add(active_time, interval #{startQueryDays} day) and date_add(active_time, interval #{stopQueryDays} day) " +
            "order by id asc")
    IPage<ClsMarketEtcAgentPromoteHistory> queryCanQueryFirstConsumeList(
            IPage<ClsMarketEtcAgentPromoteHistory> page,
            @Param("activeBonusStatus") BonusStatus activeBonusStatus,
            @Param("startQueryDays") long startQueryDays,
            @Param("stopQueryDays") long stopQueryDays);

    /**
     * 分页查询可结算首次消费奖励的推广记录列表
     *
     * @param page                    分页信息
     * @param firstConsumeBonusStatus 首次消费奖励待结算状态
     * @return 推广记录列表
     */
    @Select("select * from cls_market_etc_agent_promote_history " +
            // 查询条件-激活奖励已结算
            "where active_bonus_status = #{activeBonusStatus} " +
            // 查询条件-首次消费订单号存在
            "and first_consume_order_sn is not null " +
            // 查询条件-首次消费未结算
            "and first_consume_bonus_status = #{firstConsumeBonusStatus} " +
            "order by id asc")
    IPage<ClsMarketEtcAgentPromoteHistory> queryCanSettleFirstConsumeBonusList(
            IPage<ClsMarketEtcAgentPromoteHistory> page,
            @Param("activeBonusStatus") BonusStatus activeBonusStatus,
            @Param("firstConsumeBonusStatus") BonusStatus firstConsumeBonusStatus);
}