package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("用户端-订单退款展示 仅允许待接单(待发货)和已接单(待收货)的订单进行售后申请")
public class UserRefundDisplayDTO {

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("退款凭证(图片 -最多三张)")
    private String refundEvidence;


    @ApiModelProperty("退款描述")
    private String refundDesc;

//    @ApiModelProperty("退款商品信息")
//    private List<UserOrderDisPlayDetailItemVO> userOrderDisPlayDetailItemVO;

    @ApiModelProperty("订单编号")
    private String orderNumber;



}
