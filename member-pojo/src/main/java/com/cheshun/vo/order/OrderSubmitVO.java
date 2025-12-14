package com.cheshun.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("订单提交返回")
public class OrderSubmitVO implements Serializable {
    //订单id
    @ApiModelProperty("订单id")
    private Long id;
    //订单号
    @ApiModelProperty("订单号")
    private String orderNumber;
    //订单金额
    @ApiModelProperty("订单金额")
    private String orderAmount;

    @ApiModelProperty("配送方式 0：自提 1：闪送 2：邮寄发货")
    private Integer deliveryType;
    //下单时间
    @ApiModelProperty("下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String orderTime;

    @ApiModelProperty("门店编码")
    private String storeCode;

    @ApiModelProperty("支付参数")
    private OrderPaymentVO orderPaymentVO;
}
