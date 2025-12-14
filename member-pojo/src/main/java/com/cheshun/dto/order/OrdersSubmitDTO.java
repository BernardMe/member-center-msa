package com.cheshun.dto.order;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("订单提交参数")
@Data
public class OrdersSubmitDTO implements Serializable {
    //地址簿id
    @ApiModelProperty("地址簿id (闪送、邮寄传)")
    private Long addressBookId;

    @ApiModelProperty("自提预留手机号 -自提传")
    private String pickupPhone;
    //付款方式
    @ApiModelProperty("付款方式 目前默认为1 微信支付 可不传")
    private int payMethod;
    //备注
    @ApiModelProperty("备注")
    private String remark;
//    //预计送达时间
//    @ApiModelProperty("预计送达时间")
//    private String estimatedDeliveryTime;

    @ApiModelProperty("提交的商品信息")
    private List<OrderGoodsItems> orderGoodsItems;

//    @ApiModelProperty("下单时间")
//    private String orderTime;
//
    @ApiModelProperty("订单来源 0：页面 1: 购物车")
    private Integer orderSource;

    @ApiModelProperty("自提时间")
    private String pickTimeSlot;

    @ApiModelProperty(value = "配送方式 0：自提 1：闪送 2：邮寄发货")
    private Integer deliveryType;

    @ApiModelProperty("门店编码")
    private String storeCode;
}
