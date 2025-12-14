package com.cheshun.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户端—订单状态统计")
public class OrderStatusCountVO {

    @ApiModelProperty("待付款数量")
    private Integer pendingPayment; // 待付款数量（带角标）
    @ApiModelProperty("待发货数量")
    private Integer pendingShipment; // 待发货数量
    @ApiModelProperty("待收货数量")
    private Integer pendingReceipt;  // 待收货数量

}
