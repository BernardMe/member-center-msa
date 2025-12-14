package com.cheshun.po.mall.admin;

import com.cheshun.entity.mall.GoodsTag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsCategoryAndGoodsQueryPO {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    @ApiModelProperty(value = "货号")
    private String goodsCode;
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "排序")
    private Integer sortOrder;
    @ApiModelProperty(value = "分类图标")
    private String icon;
    @ApiModelProperty(value = "分类状态")
    private Integer categoryStatus;
    @ApiModelProperty(value = "是否热门")
    private Integer isHot;
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    @ApiModelProperty(value = "副标题/描述")
    private String subTitle;
    @ApiModelProperty(value = "规格")
    private String specification;
    @ApiModelProperty(value = "药品性状")
    private String drugDescription;
    @ApiModelProperty(value = "批准文号")
    private String drugApprovalNo;
    @ApiModelProperty(value = "剂型")
    private String dosageForm;
    @ApiModelProperty(value = "厂家")
    private String manufacturer;
    @ApiModelProperty(value = "产地")
    private String originAddress;
    @ApiModelProperty(value = "商品69码")
    private String ean13;
    @ApiModelProperty(value = "药品类别")
    private String drugCategory;
    @ApiModelProperty(value = "是否保税仓")
    private Integer isBonded;
    @ApiModelProperty(value = "首页图")
    private String coverImage;
    @ApiModelProperty(value = "主图")
    private String mainImage;
    @ApiModelProperty(value = "边框图片")
    private String borderImage;
    @ApiModelProperty(value = "商品明细图集")
    private List<GoodsInfosPO.ImgUrl> images;
    @ApiModelProperty(value = "状态：0-下架，1-上架")
    private Integer goodsStatus;
    @ApiModelProperty(value = "轮播图集")
    private List<GoodsInfosPO.ImgUrl> drugCarousel;
    @ApiModelProperty(value = "关键词")
    private String goodsKeywords;
    private String spu;
    private String sku;
    @ApiModelProperty(value = "零售价")
    private Integer retailPrice;
    @ApiModelProperty(value = "标签")
    private List<GoodsTag> goodsTag;

}
