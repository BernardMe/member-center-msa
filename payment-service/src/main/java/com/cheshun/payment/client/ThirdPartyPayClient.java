package com.cheshun.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 对接第三方支付网关的 Feign 客户端。
 * url 直接写死到第三方域名（third-party-pay.base-url），因为第三方不在本公司的注册中心里，
 * 不能走 Nacos 服务发现，所以不用 name+服务名的方式，而是用 url 属性直连。
 *
 * 实际项目中如果第三方支持国密/签名，需要在 FeignConfig 里加签名拦截器。
 */
@FeignClient(name = "third-party-pay-client", url = "${third-party-pay.base-url}")
public interface ThirdPartyPayClient {

    /**
     * 统一下单：调用第三方支付下单接口，拿到供客户端拉起支付用的参数
     */
    @PostMapping("/gateway/order/create")
    Map<String, Object> unifiedOrder(@RequestBody Map<String, Object> request);

    /**
     * 主动查询第三方支付状态（对账/回调兜底用，服务端内部调用，不对外暴露）
     */
    @PostMapping("/gateway/order/query")
    Map<String, Object> queryOrderStatus(@RequestBody Map<String, Object> request);
}
