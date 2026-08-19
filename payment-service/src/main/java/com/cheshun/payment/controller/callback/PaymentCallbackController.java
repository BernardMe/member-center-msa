package com.cheshun.payment.controller.callback;

import com.cheshun.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ============================================================
 * 服务端对接第三方支付：异步回调接口
 * 调用方是【第三方支付平台】，不是客户端！
 * 部署时要求：
 *   1) 这个路径要在网关白名单里放行第三方 IP 段，不能走用户态鉴权（第三方没有我们的用户token）
 *   2) 必须做签名校验（见 PaymentGatewayService#verifyCallbackSign），防止伪造回调
 *   3) 处理逻辑要幂等，因为第三方可能重复推送
 *   4) 按第三方约定的格式返回“成功”标识（如 "success" 纯文本），否则第三方会重试
 * ============================================================
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentCallbackController {

    private final OrderService orderService;

    @PostMapping("/api/internal/payment/callback")
    public String onThirdPartyCallback(@RequestParam Map<String, String> callbackParams) {
        log.info("收到第三方支付回调: {}", callbackParams);
        try {
            orderService.handleThirdPartyCallback(callbackParams);
            // 具体返回值以第三方文档为准，这里以支付宝风格的 "success" 为例
            return "success";
        } catch (Exception e) {
            log.error("处理第三方回调异常", e);
            // 返回非 success，第三方会按其重试策略重新推送回调
            return "fail";
        }
    }
}
