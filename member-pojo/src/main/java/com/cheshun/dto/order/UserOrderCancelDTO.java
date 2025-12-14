package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户端订单取消参数")
public class UserOrderCancelDTO {

//    @ApiModelProperty("订单id")
//    private Long orderId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("取消原因")
    private String cancelReason;
}
