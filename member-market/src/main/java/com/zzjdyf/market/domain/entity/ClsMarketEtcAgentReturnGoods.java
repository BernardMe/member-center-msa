package com.zzjdyf.market.domain.entity;

import com.zzjdyf.market.domain.entity.enums.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 退货订单关联表信息
 *
 * @author wangzhuo
 * Created on 20211112
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentReturnGoods extends BaseEntity {
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 代理商id
     */
    private Long agentId;
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

    /**
     * 状态 0-正常  1-拒绝
     */
    private Integer status;
}