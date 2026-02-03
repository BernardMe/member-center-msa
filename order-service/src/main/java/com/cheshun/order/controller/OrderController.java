package com.cheshun.order.controller;

import com.cheshun.order.dto.CreateOrderRequest;
import com.cheshun.order.service.OrderService;
import com.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单服务控制器
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public Result<String> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            log.info("收到创建订单请求: {}", request);
            String orderNo = orderService.createOrder(request);
            return Result.success(orderNo);
        } catch (Exception e) {
            log.error("创建订单失败", e);
            return Result.error("创建订单失败: " + e.getMessage());
        }
    }
}