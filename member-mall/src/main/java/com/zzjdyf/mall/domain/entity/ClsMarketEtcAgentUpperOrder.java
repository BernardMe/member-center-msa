package com.zzjdyf.mall.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 提标补货订单关联表信息
 *
 * @author wangzhuo
 * Created on 20211112
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentUpperOrder extends BaseEntity {
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 商品规格id
     */
    private Long goodsSkuId;
    /**
     * 待审核商品规格id
     */
    private Long auditGoodsSkuId;
}