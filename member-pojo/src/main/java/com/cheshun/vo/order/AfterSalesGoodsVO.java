package com.cheshun.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("售后商品信息")
@Data
public class AfterSalesGoodsVO {

    @ApiModelProperty("商品编号")
    private String goodsCode;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品图片")
    private String goodsImage;

    @ApiModelProperty("退款数量")
    private Integer refundQuantity;

}
