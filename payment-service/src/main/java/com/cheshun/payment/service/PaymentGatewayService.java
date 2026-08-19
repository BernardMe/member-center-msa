package com.cheshun.payment.service;

import com.cheshun.payment.entity.Order;

/**
 * 专门负责「服务端 <-> 第三方支付」对接的领域服务。
 * 所有与第三方支付平台的交互（下单、查询、验签）都收敛在这里，
 * 上层 OrderController / OrderService 不直接感知第三方协议细节。
 */
public interface PaymentGatewayService {

    /**
     * 向第三方发起统一下单，返回客户端拉起支付所需的参数（JSON 字符串/预支付串等）
     */
    String createThirdPartyOrder(Order order);

    /**
     * 服务端主动向第三方查询某笔订单的支付状态（对账/回调兜底场景使用）。
     * 查询结果会更新本地订单状态，并返回第三方交易流水号。
     */
    ThirdPartyPayStatus queryThirdPartyStatus(Order order);

    /**
     * 校验第三方异步回调的签名，防止伪造回调
     */
    boolean verifyCallbackSign(java.util.Map<String, String> callbackParams);

    enum ThirdPartyPayStatus {
        SUCCESS, FAILED, PROCESSING, NOT_FOUND
    }
}
