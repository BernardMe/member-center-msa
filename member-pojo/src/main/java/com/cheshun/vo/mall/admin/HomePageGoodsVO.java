package com.cheshun.vo.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("商城首页药品信息")
@Data
public class HomePageGoodsVO {

    @ApiModelProperty(value = "分类下的商品分类id 后台作为删除修改用")
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty("商品编码")
    private String goodsCode;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品首页图片")
    private String coverImage;

    @ApiModelProperty(value = "商品排序 越小越前")
    private Integer goodsSort;

    @ApiModelProperty(value = "上下架状态")
    private Integer status;

    @ApiModelProperty(value = "边框图片")
    private String borderImage;

}
