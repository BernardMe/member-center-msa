package com.zzjdyf.market.domain.entity;

import com.zzjdyf.market.domain.entity.enums.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 已发货商品信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentOrderGoods extends BaseEntity {
    /**
     * 订单id
     */
    private Long orderId;
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
     * 商品id
     */
    private Long goodsId;
    /**
     * ETC卡号
     */
    private String cardSn;
    /**
     * 卡状态(待激活|已激活｜已损坏｜已损坏并退还)
     */
    private ProductStatus cardStatus;
    /**
     * OBU标签号
     */
    private String deviceSn;
    /**
     * 设备状态(待激活|已激活｜已损坏｜已损坏并退还)
     */
    private ProductStatus deviceStatus;
}