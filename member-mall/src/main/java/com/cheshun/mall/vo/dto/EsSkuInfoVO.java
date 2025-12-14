package com.cheshun.mall.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class EsSkuInfoVO {

    /**
     * 指定sku主键
     */
    @ApiModelProperty("sku主键")
    private String skuId;
    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Integer goodsId;
    /**
     * 商品货号,不分词
     */
    @ApiModelProperty("商品货号")
    private String goodsCode;
    /**
     * 商品名称（全文检索）
     */
    @ApiModelProperty("商品名称")
    private String goodsName;
    /**
     * 商品副标题/描述（全文检索）
     */
    @ApiModelProperty("商品副标题")
    private String subTitle;
    /**
     * 零售价(单位厘)
     */
    @ApiModelProperty("零售价(单位厘)")
    private Integer retailPrice;
    /**
     * 商品主图
     */
    @ApiModelProperty("商品主图")
    private String mainImage;
    /**
     * 商品状态，0-正常，1-下架
     */
    @ApiModelProperty("商品状态，0-正常，1-下架")
    private Byte status;
    /**
     * 创建时间Str
     */
    @ApiModelProperty("创建时间Str")
    private String createTimeStr;
    /**
     * 更新时间Str
     */
    @ApiModelProperty("更新时间Str")
    private String updateTimeStr;
    /**
     * 是否默认
     */
    @ApiModelProperty("是否默认")
    private Byte isDefault;
    /**
     * 是否B2C
     */
    @ApiModelProperty("是否B2C,0否1是")
    private Byte isB2c;
    /**
     * SPU_ID
     */
    @ApiModelProperty("SPU_ID")
    private String spuId;
    /**
     * 类目ID
     */
    @ApiModelProperty("类目ID")
    private Integer categoryId;
    /**
     * 类目名称
     */
    @ApiModelProperty("类目名称")
    private String categoryName;
    /**
     * 品牌名称
     */
    @ApiModelProperty("品牌名称")
    private String originManuf;
    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String spec;
    /**
     * 规格参数
     */
    @ApiModelProperty("规格参数")
    private Map<String, Object> specMap;
    /**
     * 库存数量
     */
    @ApiModelProperty("库存数量")
    private Integer num;
    /**
     * 适用症状，多个用逗号分隔
     */
    @ApiModelProperty("适用症状，多个用逗号分隔")
    private String symptoms;
    /**
     * 门店编码，多个用逗号分隔
     */
    @ApiModelProperty("门店编码，多个用逗号分隔")
    private String subbhStr;

    @Override
    public String toString() {
        return "EsSkuInfoVO{" +
                "skuId='" + skuId + '\'' +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", retailPrice=" + retailPrice +
                ", mainImage='" + mainImage + '\'' +
                ", status=" + status +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", isDefault=" + isDefault +
                ", isB2c=" + isB2c +
                ", spuId='" + spuId + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", originManuf='" + originManuf + '\'' +
                ", spec='" + spec + '\'' +
                ", specMap=" + specMap +
                ", num=" + num +
                ", symptoms='" + symptoms + '\'' +
                ", subbhStr='" + subbhStr + '\'' +
                '}';
    }
}
