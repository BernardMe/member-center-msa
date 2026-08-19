package com.cheshun.payment.task;

import com.cheshun.payment.entity.Order;
import com.cheshun.payment.enums.OrderStatus;
import com.cheshun.payment.repository.OrderRepository;
import com.cheshun.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 兜底定时任务：周期性扫描处于 PAYING 状态超过一定时间的订单，
 * 主动向第三方回查支付状态，防止因回调丢失导致订单状态永远卡在 PAYING。
 * 生产环境建议用分布式调度（如 xxl-job）替代 @Scheduled，避免多实例重复执行。
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentStatusScheduler {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Value("${payment.poll.enabled:true}")
    private boolean enabled;

    @Scheduled(fixedDelayString = "${payment.poll.fixed-delay-ms:30000}")
    public void pollPendingOrders() {
        if (!enabled) {
            return;
        }
        List<Order> payingOrders = orderRepository.findByStatus(OrderStatus.PAYING);
        if (payingOrders.isEmpty()) {
            return;
        }
        log.info("兜底回查第三方支付状态, 待处理订单数={}", payingOrders.size());
        for (Order order : payingOrders) {
            try {
                orderService.syncStatusFromThirdParty(order.getOrderNo());
            } catch (Exception e) {
                log.error("回查订单支付状态失败, orderNo={}", order.getOrderNo(), e);
            }
        }
    }
}
