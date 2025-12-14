package com.cheshun.cloudpos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreReturnOrder {
    /**
     * 退单号
     */
    private String returnCode;

    /**
     * 原单号
     */
    private String orderCode;

    /**
     * 门店编号
     */
    private String storeCode;

    /**
     * 退款金额
     */
    private BigDecimal returnAmount;

    /**
     * 退单时间
     */
    private String createTime;

    /**
     * 收货电话
     */
    private String receivePhone;

    /**
     * 是否是整单退（0：否，部分退；1：是）
     */
    private String isFullRefund;

    /**
     * 退单类型（0：退货退款；1：仅退款，2：直赔）
     */
    private Integer returnType;
}
