package com.cheshun.po.mall;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EsSkuInfoPO {

    /**
     * 指定sku主键
     */
    private String skuId;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 商品货号,不分词
     */
    private String goodsCode;
    /**
     * 商品名称（全文检索）
     */
    private String goodsName;
    /**
     * 商品副标题/描述（全文检索）
     */
    private String subTitle;
    /**
     * 零售价(单位厘)
     */
    private Integer retailPrice;
    /**
     * 商品主图
     */
    private String mainImage;
    /**
     * 边框图片
     */
    private String borderImage;
    /**
     * 商品状态，0-下架，1-正常
     */
    private Byte status;
    /**
     * 是否O2O
     */
    private Byte isO2o;
    /**
     * 是否B2C
     */
    private Byte isB2c;
    /**
     * 创建时间Str
     */
    private String createTimeStr;
    /**
     * 更新时间Str
     */
    private String updateTimeStr;
    /**
     * 是否默认
     */
    private Byte isDefault;
    /**
     * SPU_ID
     */
    private String spuId;
    /**
     * 类目ID
     */
    private Integer categoryId;
    /**
     * 类目名称,不分词
     */
    private String categoryName;
    /**
     * 品牌名称，不分词
     */
    private String originManuf;
    /**
     * 规格
     */
    private String spec;
    /**
     * 规格参数
     */
    private Map<String, Object> specMap;
    /**
     * 库存数量
     */
    private Integer num;
    /**
     * 适用症状（，多个用逗号分隔）
     */
    private String symptoms;
    /**
     * 门店编码，多个用逗号分隔
     */
    private String subbhStr;
}
