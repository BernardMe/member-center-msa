package com.cheshun.price.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 计算机硬件商品报价实体
 */
public class HardwarePrice {

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

    /**
     * 爬取时间
     */
    private LocalDateTime crawlTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel == null ? null : productModel.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl == null ? null : productUrl.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public LocalDateTime getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(LocalDateTime crawlTime) {
        this.crawlTime = crawlTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", productModel=").append(productModel);
        sb.append(", productType=").append(productType);
        sb.append(", brand=").append(brand);
        sb.append(", platform=").append(platform);
        sb.append(", productUrl=").append(productUrl);
        sb.append(", price=").append(price);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", salesVolume=").append(salesVolume);
        sb.append(", shopName=").append(shopName);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", crawlTime=").append(crawlTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}