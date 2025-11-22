package com.cheshun.market.domain.entity.brochures;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MedicalEquipmentDTO implements Serializable {
    private Integer id;

    private String goodsCode;

    private String equipmentName;

    private String specification;

    private String manufacturer;

    private String advantages;

    private String categoryCode;

    private String categoryName;

    private String mainImage;

    private String bannerImages;

    private String detailImages;

    private String remark;

    private Long goodsPrice;

    private Integer lookPeopleNum;

    private Integer frequency;

    private Byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

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
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages == null ? null : advantages.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getBannerImages() {
        return bannerImages;
    }

    public void setBannerImages(String bannerImages) {
        this.bannerImages = bannerImages == null ? null : bannerImages.trim();
    }

    public String getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(String detailImages) {
        this.detailImages = detailImages == null ? null : detailImages.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Long goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getLookPeopleNum() {
        return lookPeopleNum;
    }

    public void setLookPeopleNum(Integer lookPeopleNum) {
        this.lookPeopleNum = lookPeopleNum;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", specification=").append(specification);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", advantages=").append(advantages);
        sb.append(", categoryCode=").append(categoryCode);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", mainImage=").append(mainImage);
        sb.append(", bannerImages=").append(bannerImages);
        sb.append(", detailImages=").append(detailImages);
        sb.append(", remark=").append(remark);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", lookPeopleNum=").append(lookPeopleNum);
        sb.append(", frequency=").append(frequency);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}