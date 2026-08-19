package com.cheshun.payment.service.impl;

import com.cheshun.payment.client.ThirdPartyPayClient;
import com.cheshun.payment.entity.Order;
import com.cheshun.payment.service.PaymentGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    private final ThirdPartyPayClient thirdPartyPayClient;

    @Value("${third-party-pay.app-id}")
    private String appId;

    @Value("${third-party-pay.app-secret}")
    private String appSecret;

    @Value("${third-party-pay.notify-url}")
    private String notifyUrl;

    @Override
    public String createThirdPartyOrder(Order order) {
        Map<String, Object> request = new HashMap<>();
        request.put("appId", appId);
        request.put("outTradeNo", order.getOrderNo());
        request.put("amount", order.getAmount());
        request.put("payChannel", order.getPayChannel());
        request.put("notifyUrl", notifyUrl);
        request.put("sign", sign(request));

        Map<String, Object> response = thirdPartyPayClient.unifiedOrder(request);

        // 实际项目中这里要校验 response 的返回码/签名，此处简化
        Object payParams = response.get("payParams");
        log.info("第三方下单成功, orderNo={}, payParams={}", order.getOrderNo(), payParams);
        return payParams == null ? null : payParams.toString();
    }

    @Override
    public ThirdPartyPayStatus queryThirdPartyStatus(Order order) {
        Map<String, Object> request = new HashMap<>();
        request.put("appId", appId);
        request.put("outTradeNo", order.getOrderNo());
        request.put("sign", sign(request));

        Map<String, Object> response = thirdPartyPayClient.queryOrderStatus(request);
        String tradeStatus = String.valueOf(response.getOrDefault("tradeStatus", "NOT_FOUND"));

        switch (tradeStatus) {
            case "SUCCESS":
                return ThirdPartyPayStatus.SUCCESS;
            case "FAILED":
                return ThirdPartyPayStatus.FAILED;
            case "PROCESSING":
                return ThirdPartyPayStatus.PROCESSING;
            default:
                return ThirdPartyPayStatus.NOT_FOUND;
        }
    }

    @Override
    public boolean verifyCallbackSign(Map<String, String> callbackParams) {
        // 实际项目中：按第三方文档规则对 callbackParams 重新计算签名，
        // 与 callbackParams 中的 sign 字段比对，防止伪造回调。此处仅示意。
        String receivedSign = callbackParams.get("sign");
        String expectedSign = sign(new HashMap<>(callbackParams));
        return expectedSign.equals(receivedSign);
    }

    /** 示意用的签名算法，实际按第三方支付平台文档实现（如 RSA2/HMAC-SHA256） */
    private String sign(Map<String, ?> params) {
        return String.valueOf((appSecret + params).hashCode());
    }
}
