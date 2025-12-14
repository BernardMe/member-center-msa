package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("订单查询参数")
@Data
public class MallOrderQueryDTO {
    @ApiModelProperty("订单号")
    private String number;
    @ApiModelProperty("下单开始时间")
    private String startOrderTime;
    @ApiModelProperty("下单结束时间")
    private String endOrderTime;
    @ApiModelProperty("手机号")
    private String pickupPhone;
    @ApiModelProperty("门店编码")
    private String storeCode;
    @ApiModelProperty("订单状态 1待付款 2待接单 3已接单 4已完成 5已取消 6已退款 7售后中 8配送中")
    private Integer status;
    @ApiModelProperty("页码")
    private Integer pageNum;
    @ApiModelProperty("每页条数")
    private Integer pageSize;
    @ApiModelProperty("支付方式")
    private String payMethod;
    @ApiModelProperty("配送方式")
    private String tranType;
    @ApiModelProperty("快递单号")
    private String courierNumber;
}
