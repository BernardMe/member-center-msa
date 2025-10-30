package com.zzjdyf.market.domain.entity;

import com.zzjdyf.market.domain.entity.enums.ActiveMethod;
import com.zzjdyf.market.domain.entity.enums.BonusStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代理商推广记录信息表
 *
 * @author 阿隆
 * Created on 2021/8/11 10:10 上午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentPromoteHistory extends BaseEntity {
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 上级内部员工id
     */
    private Long staffId;
    /**
     * 上级代理(一级)id
     */
    private Long agent1Id;
    /**
     * 上级代理(二级)id
     */
    private Long agent2Id;
    /**
     * 激活的用户id
     */
    private Long userId;
    /**
     * 激活渠道
     */
    private String channel;
    /**
     * 激活方式
     */
    private ActiveMethod method;
    /**
     * 激活的车牌号
     */
    private String carNo;
    /**
     * 车牌号颜色
     */
    private String carColor;
    /**
     * 激活的ECT账户
     */
    private String etcAccount;
    /**
     * 激活的ETC卡号
     */
    private String cardSn;
    /**
     * 激活的OBU标签号
     */
    private String deviceSn;
    /**
     * 激活时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activeTime;
    /**
     * 激活奖励金额(元)
     */
    private BigDecimal activeBonus = BigDecimal.ZERO;
    /**
     * 激活奖励状态(待结算|已结算)
     */
    private BonusStatus activeBonusStatus;
    /**
     * 首次消费订单号
     */
    private String firstConsumeOrderSn;
    /**
     * 首次消费时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date firstConsumeTime;
    /**
     * 首次消费奖励金额(元)
     */
    private BigDecimal firstConsumeBonus = BigDecimal.ZERO;
    /**
     * 首次消费奖励状态(待结算|已结算)
     */
    private BonusStatus firstConsumeBonusStatus;
    /**
     * 员工奖励金额(元)
     */
    private BigDecimal staffBonus = BigDecimal.ZERO;

    /**
     * 超过规定套数后，是否分过额外奖励状态，0位分，1已分
     */
    private String extraStatus;
}
