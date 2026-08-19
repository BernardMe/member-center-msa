package com.cheshun.payment.service.impl;

import com.cheshun.payment.dto.CreateOrderRequest;
import com.cheshun.payment.dto.CreateOrderResponse;
import com.cheshun.payment.dto.PaymentStatusDTO;
import com.cheshun.payment.entity.Order;
import com.cheshun.payment.enums.OrderStatus;
import com.cheshun.payment.repository.OrderRepository;
import com.cheshun.payment.service.OrderService;
import com.cheshun.payment.service.PaymentGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentGatewayService paymentGatewayService;

    /**
     * ============ 客户端下单 ============
     * 只做两件事：1) 落库生成本地订单  2) 调第三方下单拿到拉起支付的参数
     * 绝不在这里直接判断"支付是否成功"——支付结果只能来自第三方的回调或服务端主动查询。
     */
    @Override
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setUserId(request.getUserId());
        order.setAmount(request.getAmount());
        order.setPayChannel(request.getPayChannel());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.save(order);

        String payParams;
        try {
            payParams = paymentGatewayService.createThirdPartyOrder(order);
            order.setStatus(OrderStatus.PAYING);
        } catch (Exception e) {
            log.error("调用第三方下单失败, orderNo={}", order.getOrderNo(), e);
            order.setStatus(OrderStatus.FAILED);
            orderRepository.save(order);
            throw new IllegalStateException("下单失败，请稍后重试");
        }
        orderRepository.save(order);

        return new CreateOrderResponse(order.getOrderNo(), payParams, order.getStatus().name());
    }

    @Override
    public PaymentStatusDTO getOrderStatus(String orderNo) {
        Order order = orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));

        PaymentStatusDTO dto = new PaymentStatusDTO();
        dto.setOrderNo(order.getOrderNo());
        dto.setStatus(order.getStatus().name());
        dto.setThirdPartyTradeNo(order.getThirdPartyTradeNo());
        return dto;
    }

    /**
     * ============ 服务端对接第三方：处理异步回调 ============
     * 这是第三方支付平台主动调用我方的接口，不是客户端调用的。
     */
    @Override
    @Transactional
    public void handleThirdPartyCallback(Map<String, String> callbackParams) {
        Assert.isTrue(paymentGatewayService.verifyCallbackSign(callbackParams), "回调签名校验失败");

        String orderNo = callbackParams.get("outTradeNo");
        String tradeStatus = callbackParams.get("tradeStatus");
        String tradeNo = callbackParams.get("tradeNo");

        Order order = orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new IllegalArgumentException("回调订单不存在: " + orderNo));

        // 幂等：已经是终态就不再处理，防止第三方重复推送回调
        if (order.getStatus() == OrderStatus.PAID || order.getStatus() == OrderStatus.FAILED) {
            log.info("订单已是终态，忽略重复回调, orderNo={}, status={}", orderNo, order.getStatus());
            return;
        }

        order.setThirdPartyTradeNo(tradeNo);
        order.setStatus("SUCCESS".equals(tradeStatus) ? OrderStatus.PAID : OrderStatus.FAILED);
        orderRepository.save(order);

        log.info("处理第三方回调完成, orderNo={}, newStatus={}", orderNo, order.getStatus());
        // TODO: 这里可以发 MQ 消息通知业务方（如发货、开通服务等），做业务解耦
    }

    /**
     * ============ 服务端对接第三方：主动查询（对账/兜底） ============
     */
    @Override
    @Transactional
    public void syncStatusFromThirdParty(String orderNo) {
        Order order = orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));

        if (order.getStatus() != OrderStatus.PAYING) {
            return;
        }

        PaymentGatewayService.ThirdPartyPayStatus status = paymentGatewayService.queryThirdPartyStatus(order);
        switch (status) {
            case SUCCESS:
                order.setStatus(OrderStatus.PAID);
                orderRepository.save(order);
                break;
            case FAILED:
                order.setStatus(OrderStatus.FAILED);
                orderRepository.save(order);
                break;
            case PROCESSING:
            case NOT_FOUND:
            default:
                // 保持 PAYING，等待下一次回查或回调
                break;
        }
    }

    private String generateOrderNo() {
        return "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6);
    }
}
