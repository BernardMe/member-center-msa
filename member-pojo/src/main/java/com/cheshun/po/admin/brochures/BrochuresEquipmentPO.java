package com.cheshun.po.admin.brochures;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class BrochuresEquipmentPO {

    @ExcelIgnore
    private Integer id;
    /**
     * 货号
     */
    @ExcelProperty(value = "货号")
    @ColumnWidth(20)
    private String goodsCode;
    /**
     * 父级级目录名称
     */
    @ExcelIgnore
    private String superCategoryCode;
    /**
     * 父级目录名称
     */
    @ExcelProperty(value = "父级目录名称")
    @ColumnWidth(50)
    private String superCategoryName;
    /**
     * 子级目录名称
     */
    @ExcelIgnore
    private String categoryCode;
    /**
     * 目录名称
     */
    @ExcelProperty(value = "子级目录名称")
    @ColumnWidth(50)
    private String categoryName;
    /**
     * 品名
     */
    @ExcelProperty(value = "品名")
    @ColumnWidth(20)
    private String equipmentName;
    /**
     * 规格
     */
    @ExcelProperty(value = "型号")
    @ColumnWidth(100)
    private String specification;
    /**
     * 厂家
     */
    @ExcelProperty(value = "厂家")
    @ColumnWidth(200)
    private String manufacturer;
    /**
     * 产品优势
     */
    @ExcelProperty(value = "产品优势（按条换行Alt+Enter）")
    @ColumnWidth(1500)
    private String advantages;
    /**
     * 主图url
     */
    @ExcelIgnore
    private String mainImage;
    /**
     * 轮播图url
     */
    @ExcelIgnore
    private String bannerImages;
    /**
     * 详情图url
     */
    @ExcelIgnore
    private String detailImages;
    /**
     * 备注说明
     */
    @ExcelProperty(value = "备注说明")
    @ColumnWidth(200)
    private String remark;
    /**
     * 零售价
     */
    @ExcelProperty(value = "零售价")
    private String goodsPrice;
    /**
     * 频次
     */
    @ExcelProperty(value = "频次")
    private Integer frequency;
    /**
     * 状态(0禁用, 1正常)
     */
    @ExcelIgnore
    private Byte status;
    /**
     * 创建时间
     */
    @ExcelIgnore
    private String createTimeStr;
    /**
     * 修改时间
     */
    @ExcelIgnore
    private String updateTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getBannerImages() {
        return bannerImages;
    }

    public void setBannerImages(String bannerImages) {
        this.bannerImages = bannerImages;
    }

    public String getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(String detailImages) {
        this.detailImages = detailImages;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    @Override
    public String toString() {
        return "BrochuresEquipmentPO{" +
                "id=" + id +
                ", goodsCode='" + goodsCode + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", advantages='" + advantages + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", bannerImages='" + bannerImages + '\'' +
                ", detailImages='" + detailImages + '\'' +
                ", remark='" + remark + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", frequency='" + frequency + '\'' +
                ", status=" + status +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }
}
