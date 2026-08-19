package com.cheshun.payment.dto;

import lombok.Data;

/**
 * 内部/前端查询订单支付状态的返回结构
 */
@Data
public class PaymentStatusDTO {
    private String orderNo;
    private String status;
    private String thirdPartyTradeNo;
}
