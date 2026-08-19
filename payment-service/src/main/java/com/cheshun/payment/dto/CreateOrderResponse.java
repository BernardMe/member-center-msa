package com.cheshun.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户端下单响应：返回本地订单号 + 客户端拉起支付所需的参数（如支付跳转链接/预支付串）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {

    private String orderNo;

    /** 客户端唤起第三方支付所需的凭证，例如支付宝的 orderString、微信的 prepay_id 等 */
    private String payParams;

    private String status;
}
