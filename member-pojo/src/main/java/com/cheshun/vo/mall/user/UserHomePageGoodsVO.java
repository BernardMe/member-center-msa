package com.cheshun.vo.mall.user;

import com.cheshun.entity.mall.GoodsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@ApiModel("用户端-首页商品信息")
@Data
public class UserHomePageGoodsVO {

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    @ApiModelProperty(value = "货号")
    private String goodsCode;
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "首页分类树商品排序")
    private Integer GoodsSort;


    @ApiModelProperty(value = "首页图")
    private String coverImage;
    @ApiModelProperty(value = "零售价")
    private Integer retailPrice;
    @ApiModelProperty(value = "标签")
    private List<GoodsTag> goodsTag;
    @ApiModelProperty(value = "边框图片")
    private String borderImage;

    @ApiModelProperty(value = "主图")
    private String mainImage;


}
