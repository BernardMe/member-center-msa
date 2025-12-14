package com.cheshun.dto.mall.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "添加用户购物车入参")
@Data
public class AddUserShopCartDTO {

    @ApiModelProperty(value = "商品货号")
    private String goodsCode;

    @ApiModelProperty(value = "商品数量 单个增加/删除不传")
    private Integer goodsNumber;

    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;
}
