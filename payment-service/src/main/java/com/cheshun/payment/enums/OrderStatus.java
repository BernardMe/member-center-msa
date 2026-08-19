package com.cheshun.payment.enums;

/**
 * 订单/支付状态
 * CREATED   - 订单已创建，尚未发起支付
 * PAYING    - 已调用第三方下单，等待支付结果
 * PAID      - 第三方确认支付成功
 * FAILED    - 第三方确认支付失败
 * CLOSED    - 超时或人工关闭
 */
public enum OrderStatus {
    CREATED,
    PAYING,
    PAID,
    FAILED,
    CLOSED
}
