package com.cheshun.vo.mall.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "商城用户购物车信息")
public class MallShopUserCartInfoVO {

    /**
     * 配送方式 0：自提 1：闪送 2：邮寄发货
     */
    @ApiModelProperty(value = "配送方式 0：自提 1：闪送 2：邮寄发货")
    private Integer shopMethod;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "购物车明细-上架")
    private List<MallShopCartInfoVO> mallShopCartInfoVOList;

    @ApiModelProperty(value = "购物车明细-下架")
    private List<MallShopCartInfoVO> mallShopCartInfoVOListDown;

    /**
     * 门店编号
     */
    @ApiModelProperty(value = "门店编号")
    private String storeCode;

    @ApiModelProperty(value = "总金额")
    private Long totalAmount;

}
