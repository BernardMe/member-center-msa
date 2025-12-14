package com.cheshun.dto.mall.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "删除购物车商品入参")
public class DeleteShopCartDTO {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "商品货号")
    private String goodsCode;
}
