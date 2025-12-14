package com.cheshun.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("售后商品详情信息")
@Data
public class AfterSalesGoodsDetailVO extends AfterSalesGoodsVO{

    @ApiModelProperty("退款金额")
    private String refundAmount;
}
