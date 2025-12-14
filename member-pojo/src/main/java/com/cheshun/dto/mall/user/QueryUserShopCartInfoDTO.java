package com.cheshun.dto.mall.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询用户购物车信息")
public class QueryUserShopCartInfoDTO {

    /**
     * 配送方式 0：自提 1：闪送 2：邮寄发货
     */
    @ApiModelProperty(value = "购物车类型(配送方式) 0：自提 1：闪送 2：邮寄发货")
    private Integer deliveryMethod;


    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;
}
