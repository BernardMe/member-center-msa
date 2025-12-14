package com.cheshun.po.mall.admin;

import com.cheshun.entity.mall.GoodsTag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class GoodsInfosPO {
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    @ApiModelProperty(value = "货号")
    private String goodsCode;
    @ApiModelProperty(value = "商品名称")
    private String productName;
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
    @ApiModelProperty(value = "商品明细图集")
    private List<ImgUrl> images;
    @ApiModelProperty(value = "富文本")
    private String context;
    @ApiModelProperty(value = "边框图片")
    private String borderImage;
    @ApiModelProperty(value = "状态：0-下架，1-上架")
    private Integer status;
    @ApiModelProperty(value = "审核状态：0-未审核 1-审核通过 2-待审核")
    private Integer reviewStatus;
    @ApiModelProperty(value = "轮播图集")
    private List<ImgUrl> drugCarousel;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createtime;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifytime;
    @ApiModelProperty(value = "关键词")
    private String goodsKeywords;
    private String spu;
    private String sku;
    @ApiModelProperty(value = "零售价")
    private Integer retailPrice;
    @ApiModelProperty(value = "功效")
    private String effect;
    @ApiModelProperty(value = "用法用量")
    private String usageDosage;
    @ApiModelProperty(value = "存储方式")
    private String storage;
    @ApiModelProperty(value = "标签")
    private List<GoodsTag> goodsTag;
    @ApiModelProperty(value = "商品详细说明")
    private Map<String, String> goodsInfosDetail;
    @ApiModelProperty(value = "器械过期日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date equipmentDate;
    @ApiModelProperty(value = "二级分类")
    private String middleCategory;

    @Data
    public static class ImgUrl {
        private String url;
        private String enable;
    }
}