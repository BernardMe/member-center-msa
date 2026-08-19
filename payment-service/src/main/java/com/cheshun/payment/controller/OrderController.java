package com.cheshun.payment.controller;

import com.cheshun.payment.dto.CreateOrderRequest;
import com.cheshun.payment.dto.CreateOrderResponse;
import com.cheshun.payment.dto.PaymentStatusDTO;
import com.cheshun.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ============================================================
 * 客户端下单接口（面向 App/小程序/前端，需要网关鉴权、限流）
 * 职责：接收下单请求 -> 生成本地订单 -> 调第三方下单 -> 把拉起支付的参数返回给客户端
 * 绝不承担"判断支付是否成功"的职责，那是下面 PaymentCallbackController 的事。
 * ============================================================
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /** 客户端下单，拿到支付参数后自行拉起支付宝/微信SDK完成支付 */
    @PostMapping
    public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    /** 客户端支付完成后轮询订单状态（读的是本地库，不会直接触发查第三方） */
    @GetMapping("/{orderNo}/status")
    public PaymentStatusDTO getOrderStatus(@PathVariable String orderNo) {
        return orderService.getOrderStatus(orderNo);
    }
}
