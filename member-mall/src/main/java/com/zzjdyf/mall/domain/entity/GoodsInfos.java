package com.zzjdyf.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品信息表
 *
 * @TableName goods_infos
 */
@TableName(value = "goods_infos")
@Data
public class GoodsInfos {
    @TableId(value = "goods_id")
    private Integer goodsId;

    @TableField(value = "goods_code")
    private String goodsCode;

    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 副标题/描述
     */
    @TableField(value = "sub_title")
    private String subTitle;

    /**
     * 规格
     */
    @TableField(value = "specification")
    private String specification;

    /**
     * 药品性状
     */
    @TableField(value = "drug_description")
    private String drugDescription;

    /**
     * 批准文号
     */
    @TableField(value = "drug_approval_no")
    private String drugApprovalNo;

    /**
     * 剂型
     */
    @TableField(value = "dosage_form")
    private String dosageForm;

    /**
     * 厂家
     */
    @TableField(value = "manufacturer")
    private String manufacturer;

    /**
     * 产地
     */
    @TableField(value = "origin_address")
    private String originAddress;

    /**
     * 商品69码
     */
    @TableField(value = "ean13")
    private String ean13;

    /**
     * 药品类别
     */
    @TableField(value = "drug_category")
    private String drugCategory;

    /**
     * 是否保税仓
     */
    @TableField(value = "is_bonded")
    private Integer isBonded;

    /**
     * 首页图
     */
    @TableField(value = "cover_image")
    private String coverImage;

    /**
     * 主图
     */
    @TableField(value = "main_image")
    private String mainImage;

    /**
     * 商品明细图集
     */
    @TableField(value = "images")
    private String images;

    /**
     * 富文本
     */
    @TableField(value = "context")
    private String context;

    /**
     * 状态：0-下架，1-上架
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 轮播图集
     */
    @TableField(value = "drug_carousel")
    private String drugCarousel;

    /**
     * 创建时间
     */
    @TableField(value = "createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    @TableField(value = "modifytime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifytime;

    @TableField(value = "goods_keywords")
    private String goodsKeywords;

    @TableField(value = "spu")
    private String spu;

    @TableField(value = "sku")
    private String sku;

    @TableField(value = "retail_price")
    private Integer retailPrice;
}