package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel("订单支付参数")
public class OrdersPaymentDTO implements Serializable {
    //订单号
    @ApiModelProperty("订单号")
    private String orderNumber;
    //付款方式
    @ApiModelProperty("付款方式 目前默认为1 微信支付 可不传")
    private Integer payMethod;

//    @ApiModelProperty("用户备注")
//    private String remark;

}
