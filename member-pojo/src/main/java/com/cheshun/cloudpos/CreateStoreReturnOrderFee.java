package com.cheshun.cloudpos;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreReturnOrderFee {
    /**
     * 费用编码
     */
    private String feeCode;

    /**
     * POS中支付方式的代码
     */
    private String payTypeCode;

    /**
     * 费用金额
     */
    private BigDecimal amount;

    /**
     * 支付方式
     */
    private String feeType;

    /**
     * 是否是优惠
     * 0：否
     * 1：是
     */
    private String isCoupon;
}
