package com.cheshun.cloudpos;

import lombok.*;

import java.math.BigDecimal;

/**
 * 云pos-订单下账-请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreOrderDetail {
    /**
     * 商品编码
     */
    private String goodsNo;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 商品原价
     * 4位小数
     */
    private BigDecimal retailPrice;

    /**
     * 商品总金额，2位小数；原价*数量
     */
    private BigDecimal retailAmount;

    /**
     * 折扣，默认0 ;
     *
     * 折扣是指此商品明细折扣了多少钱，是原零售总额-实际销售额度的差，两位小数
     */
    private BigDecimal discount;

    /**
     * 折扣价，4位小数
     *
     * 折扣价是指此商品原零售价经过折扣后的一个价格
     */
    private BigDecimal discountPrice;

    /**
     * 折扣金额，2位小数
     *
     * 折扣金额实际指的是折扣后的金额，是指此商品经过折扣后的一个金额额度
     */
    private BigDecimal discountAmount;

    /**
     * 分摊单价，实际售价
     *
     * 分摊单价即这个商品实际最终售价，四位小数
     */
    private BigDecimal sharePrice;

    /**
     * 分摊金额
     *
     * 分摊金额指的是该商品的实际销售的总额，两位小数
     */
    private BigDecimal shareAmount;

    /**
     * 分摊折扣，默认0 ;
     *
     * 和discount字段相同
     */
    private BigDecimal shareDiscount;
}
