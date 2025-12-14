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
public class CreateStoreOrderFee {
    /**
     * 费用编码（默认可传递支付方式编码）
     */
    private String feeNo;

    /**
     * 支付方式编码（平台支付方式编码，在ERP对应）
     */
    private String payModeNo;

    /**
     * 支付金额
     * 这个amount 只是对于所有商品的实际支付金额
     */
    private BigDecimal amount;

    /**
     * 费用类型（默认传递1）
     */
    private String feeType;

    /**
     * 是否促销（默认传0）
     * 0：否
     * 1：是
     * 是否促销这个字段不仅仅是折扣，只要是参与促销产生的订单就需要传 是
     */
    private String isCoupon;
}
