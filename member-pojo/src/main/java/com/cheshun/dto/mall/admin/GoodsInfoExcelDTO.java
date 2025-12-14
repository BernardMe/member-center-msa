package com.cheshun.dto.mall.admin;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class GoodsInfoExcelDTO {
    @ExcelProperty("商品ID")
    private Integer goodsId;

    @ExcelProperty("商品编码")
    private String goodsCode;

    @ExcelProperty("商品名称")
    private String productName;

    @ExcelProperty("副标题/描述")
    private String subTitle;

    @ExcelProperty("规格")
    private String specification;

    @ExcelProperty("药品性状")
    private String drugDescription;

    @ExcelProperty("批准文号")
    private String drugApprovalNo;

    @ExcelProperty("剂型")
    private String dosageForm;

    @ExcelProperty("厂家")
    private String manufacturer;

    @ExcelProperty("产地")
    private String originAddress;

    @ExcelProperty("商品69码")
    private String ean13;

    @ExcelProperty("药品类别")
    private String drugCategory;

    @ExcelProperty("是否保税仓")
    private Integer isBonded;

    @ExcelProperty("首页图")
    private String coverImage;

    @ExcelProperty("主图")
    private String mainImage;

    @ExcelProperty("商品明细图集")
    private String images;

    @ExcelProperty("富文本")
    private String context;

    @ExcelProperty("边框图片")
    private String borderImage;

    @ExcelProperty("状态")
    private Integer status;

    @ExcelProperty("审核状态")
    private Integer reviewStatus;

    @ExcelProperty("轮播图集")
    private String drugCarousel;

    @ExcelProperty("创建时间")
    private String createtime;

    @ExcelProperty("修改时间")
    private String modifytime;

    @ExcelProperty("商品关键词")
    private String goodsKeywords;

    @ExcelProperty("SPU")
    private String spu;

    @ExcelProperty("SKU")
    private String sku;

    @ExcelProperty("零售价")
    private Integer retailPrice;

    @ExcelProperty("功效")
    private String effect;

    @ExcelProperty("用法用量")
    private String usageDosage;

    @ExcelProperty("支持O2O")
    private Integer supportO2o;

    @ExcelProperty("支持B2C")
    private Integer supportB2c;

    @ExcelProperty("搜索标签")
    private Integer searchTag;

    @ExcelProperty("储存方式")
    private String storage;

    @ExcelProperty("设备日期")
    private String equipmentDate;

    @ExcelProperty("中类")
    private String middleCategory;

}
