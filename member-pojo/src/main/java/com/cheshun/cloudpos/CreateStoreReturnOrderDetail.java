package com.cheshun.cloudpos;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreReturnOrderDetail {
    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 退货数量
     */
    private BigDecimal returnCount;

    /**
     * 商品单价(实际退款的折后价)
     */
    private BigDecimal goodsPrice;

    /**
     * 商品总金额（退款金额=单价*退货数量）
     */
    private BigDecimal goodsAmount;

    /**
     * 是否是赠品
     * 0：否
     * 1：是
     */
    private Integer isGift;
}
