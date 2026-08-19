package com.cheshun.payment.entity;

import com.cheshun.payment.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 业务订单号，客户端下单时生成，全局唯一 */
    @Column(unique = true, nullable = false, length = 64)
    private String orderNo;

    /** 下单用户 */
    private Long userId;

    /** 订单金额，单位：元 */
    private BigDecimal amount;

    /** 订单状态 */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /** 第三方支付渠道返回的支付流水号，回调/查询后回填 */
    private String thirdPartyTradeNo;

    /** 支付渠道，如 alipay / wechat */
    private String payChannel;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        if (this.status == null) {
            this.status = OrderStatus.CREATED;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
