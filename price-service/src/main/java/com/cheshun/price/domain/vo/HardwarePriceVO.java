package com.cheshun.price.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 计算机硬件商品报价VO实体
 */
@Data
public class HardwarePriceVO {

    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品型号
     */
    private String productModel;

    /**
     * 商品类型: MOTHERBOARD-主板, CPU-处理器, GPU-显卡, RAM-内存, SSD-固态硬盘
     */
    private String productType;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 电商平台: TAOBAO-淘宝, JD-京东, PDD-拼多多
     */
    private String platform;

    /**
     * 商品链接
     */
    private String productUrl;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 销量
     */
    private Integer salesVolume;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 商品图片URL
     */
    private String imageUrl;

    /**
     * 商品描述
     */
    private String description;


}