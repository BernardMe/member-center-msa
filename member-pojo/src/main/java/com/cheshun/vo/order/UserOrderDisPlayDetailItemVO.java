package com.cheshun.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("用户端-订单展示接口(商品详情)")
public class UserOrderDisPlayDetailItemVO extends UserOrderDetailItemVO {

    @ApiModelProperty(value = "商品原价")
    private String retailPrice;

    @ApiModelProperty(value = "商品原总价格")
    private String retailAmount;

    @ApiModelProperty(value = "商品折扣价格金额 ")
    private String discountAmount;

    @ApiModelProperty(value = "商品折扣价 展示用这个")
    private String discountPrice;
}
