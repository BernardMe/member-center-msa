package com.cheshun.market.domain.entity;

import com.cheshun.market.domain.entity.enums.OrderStatus;
import com.cheshun.market.domain.entity.enums.TradeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 代理商补货订单信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentOrder extends BaseEntity {
    /**
     * 类型(订货|补货|提现|提标补货|退货)
     */
    private TradeType type;
    /**
     * 代理商品id
     */
    private Long agentGoodsId;
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 申请卡数量
     */
    private Integer cardQuantity;
    /**
     * 申请设备数量
     */
    private Integer deviceQuantity;
    /**
     * 收货人姓名
     */
    private String consignee;
    /**
     * 收货人电话
     */
    private String consigneePhone;
    /**
     * 收货地址
     */
    private String consigneeAddress;
    /**
     * 状态(待发货|已发货|申请补货|已拒绝|已取消|申请退货|已退货)
     */
    private OrderStatus status;
    /**
     * 操作人员
     */
    private String operator;
    /**
     * 是否降低规格
     */
    private Integer isReduceQuantity;
    /**
     * 申请提升的规格id或降低后规格id
     */
    private Long reduceSkuId;

    /**
     * 订单创建时的规格id
     */
    private Long goodsSkuId;
}