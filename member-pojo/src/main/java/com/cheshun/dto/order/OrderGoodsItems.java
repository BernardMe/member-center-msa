package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "订单商品项")
@Data
public class OrderGoodsItems {

    @ApiModelProperty(value = "商品货号")
    private String goodsCode;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;
}
