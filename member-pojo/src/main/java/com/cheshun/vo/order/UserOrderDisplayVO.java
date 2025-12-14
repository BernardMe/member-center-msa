package com.cheshun.vo.order;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("用户端-订单展示接口")
@Data
public class UserOrderDisplayVO {


    @ApiModelProperty("订单号")
    private String orderNumber;

    /**
     * 订单金额 对应 real_pay_amount
     */
    @ApiModelProperty("订单金额")
    private String orderAmount;

    @ApiModelProperty(value = "配送方式 0：自提 1：闪送 2：邮寄发货")
    private Integer deliveryType;

    @ApiModelProperty("订单状态 0: 全部 1：未付款 2：待发货 3:待收货 4:已完成 5已取消 6已退款 7.售后中")
    private Integer orderStatus;

    @ApiModelProperty("商品详情")
    private List<UserOrderDetailItemVO> userOrderDetailItemVO;

    @ApiModelProperty("门店编号")
    private String storeCode;

    @ApiModelProperty("门店名称")
    private String storeName;





}
