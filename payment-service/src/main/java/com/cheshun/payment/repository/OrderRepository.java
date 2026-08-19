package com.cheshun.payment.repository;

import com.cheshun.payment.entity.Order;
import com.cheshun.payment.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNo(String orderNo);

    /** 查出所有仍处于「支付中」状态、需要被定时任务回查第三方的订单 */
    List<Order> findByStatus(OrderStatus status);
}
