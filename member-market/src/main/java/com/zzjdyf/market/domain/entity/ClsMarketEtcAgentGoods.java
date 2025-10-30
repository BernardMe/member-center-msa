package com.zzjdyf.market.domain.entity;

import com.zzjdyf.market.domain.entity.enums.DepositStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 代理商代理商品信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentGoods extends BaseEntity {
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品规格id
     */
    private Long goodsSkuId;
    /**
     * 待审核商品规格id
     */
    private Long auditGoodsSkuId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 需缴纳押金(元)
     */
    private BigDecimal deposit;
    /**
     * 补货比例
     */
    private BigDecimal replRatio;
    /**
     * 补货时是否全补
     */
    private Boolean fullRepl;
    /**
     * 实缴纳押金(元)
     */
    private BigDecimal paidDeposit;
    /**
     * 状态(待缴纳押金|已缴纳押金|已退还押金)
     */
    private DepositStatus status;
    /**
     * 是否有效(0:无效, 1:有效)
     */
    private Boolean isEnabled;
}