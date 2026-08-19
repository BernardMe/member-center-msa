package com.cheshun.payment.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 客户端下单请求参数
 */
@Data
public class CreateOrderRequest {

    @NotNull(message = "userId 不能为空")
    private Long userId;

    @NotNull(message = "amount 不能为空")
    @DecimalMin(value = "0.01", message = "amount 必须大于 0")
    private BigDecimal amount;

    /** 支付渠道：alipay / wechat */
    @NotBlank(message = "payChannel 不能为空")
    private String payChannel;

    /** 商品标题，用于展示 */
    private String subject;
}
