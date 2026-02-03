package com.cheshun.order.service;

import com.cheshun.order.dto.CreateOrderRequest;

/**
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 创建订单（使用TCC分布式事务）
     * @param request 创建订单请求
     * @return 订单号
     */
    String createOrder(CreateOrderRequest request);
}