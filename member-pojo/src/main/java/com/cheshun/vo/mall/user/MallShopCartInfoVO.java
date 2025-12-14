package com.cheshun.vo.mall.user;

import com.cheshun.entity.mall.GoodsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "商城购物车详细信息")
public class MallShopCartInfoVO {


    /**
     * 商品状态 1：正常 0：下架
     */
    @ApiModelProperty(value = "商品状态 1：正常 0：下架")
    private Integer goodsStatus;

    /**
     * 商品库存数量
     */
    @ApiModelProperty(value = "商品库存数量")
    private Integer goodsInventory;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String goodsImage;

    /**
     * 商品编号/货号
     */
    @ApiModelProperty(value = "商品编号/货号")
    private String goodsCode;

    /**
     * 商品全称
     */
    @ApiModelProperty(value = "商品全称")
    private String subTitle;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String specification;

    /**
     * 商品零售价
     */
    @ApiModelProperty(value = "商品零售价")
    private Integer retailPrice;

    /**
     * 是否处方药
     */
    @ApiModelProperty(value = "是否处方药 0:否 1:是")
    private Integer isPrescription;

    @ApiModelProperty(value = "主图")
    private String mainImage;

    @ApiModelProperty(value = "商品标签")
    private List<GoodsTag> goodsTag;



}
