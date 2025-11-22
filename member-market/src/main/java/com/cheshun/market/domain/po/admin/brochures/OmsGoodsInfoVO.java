package com.cheshun.market.domain.po.admin.brochures;

import java.util.List;

public class OmsGoodsInfoVO {
	/**
     * 商品类型
     */
    private String type;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品助记码
     */
    private String goodsMnemonicCode;
    /**
     * 商品名称,该名称可能会根据业务和状态出现特殊字符,通常显示商品别名.
     */
    private String goodsName;
    /**
     * 商品别名,也是商品通用名.
     */
    private String goodsAliasName;
    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 生产公司
     */
    private String productionCompany;
    /**
     * 单位编号
     */
    private String unitCode;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 规格
     */
    private String specifications;
	/**
	 * 剂型
	 */
	private String dosage;
    /**
     * 批准文号
     */
    private String approvalNo;
    /**
     * 单位
     */
    private String unit;
    /**
     * 进价,默认不查询
     */
    private Double purchasePrice;
    /**
     * 配送价,默认不查询
     */
    private Double deliveryPrice;
    /**
     * 零售价
     */
    private Double retailPrice;
    /**
     * 会员价
     */
    private Double memberPrice;
    /**
     * 验收标准
     */
    private String acceptanceCriteria;
    /**
     * 性状
     */
    private String character;
    /**
     * 功效
     */
    private String effect;
    /**
     * 存储条件
     */
    private String storageCondition;
    /**
     * 药品类别,该类别会显示OTC等药品信息.
     */
    private String category;
    /**
     * 是否禁用
     */
    private Integer isForbidden;
    /**
     * 是否停售
     */
    private Integer isStopSale;
    /**
     * 是否停购
     */
    private Integer isStopBuy;
    /**
     * 条码
     */
    private String barCode;
    /**
     * 大类
     */
    private String bigType;
    /**
     * 中类
     */
    private String simpleType;
    /**
     * 小类
     */
    private String littleType;
    /**
     * 子类
     */
    private String childType;
    private String storeCode;
	private Double stock;
	private String achievements;
	//商品图像
	private String goodsImage;
	//商品小图标
	private String goodsIcon;
	//商品轮播图
	private List<String> goodsBannerImages;
	//商品详情图
	private List<String> goodsDetailImages;

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getAchievements() {
		return achievements;
	}
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsMnemonicCode() {
		return goodsMnemonicCode;
	}
	public void setGoodsMnemonicCode(String goodsMnemonicCode) {
		this.goodsMnemonicCode = goodsMnemonicCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsAliasName() {
		return goodsAliasName;
	}
	public void setGoodsAliasName(String goodsAliasName) {
		this.goodsAliasName = goodsAliasName;
	}
	public String getProductionPlace() {
		return productionPlace;
	}
	public void setProductionPlace(String productionPlace) {
		this.productionPlace = productionPlace;
	}
	public String getProductionCompany() {
		return productionCompany;
	}
	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getApprovalNo() {
		return approvalNo;
	}
	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Double getMemberPrice() {
		return memberPrice;
	}
	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}
	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}
	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getStorageCondition() {
		return storageCondition;
	}
	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getIsForbidden() {
		return isForbidden;
	}
	public void setIsForbidden(Integer isForbidden) {
		this.isForbidden = isForbidden;
	}
	public Integer getIsStopSale() {
		return isStopSale;
	}
	public void setIsStopSale(Integer isStopSale) {
		this.isStopSale = isStopSale;
	}
	public Integer getIsStopBuy() {
		return isStopBuy;
	}
	public void setIsStopBuy(Integer isStopBuy) {
		this.isStopBuy = isStopBuy;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getSimpleType() {
		return simpleType;
	}
	public void setSimpleType(String simpleType) {
		this.simpleType = simpleType;
	}
	public String getLittleType() {
		return littleType;
	}
	public void setLittleType(String littleType) {
		this.littleType = littleType;
	}
	public String getChildType() {
		return childType;
	}
	public void setChildType(String childType) {
		this.childType = childType;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	public String getGoodsIcon() {
		return goodsIcon;
	}
	public void setGoodsIcon(String goodsIcon) {
		this.goodsIcon = goodsIcon;
	}
	public List<String> getGoodsBannerImages() {
		return goodsBannerImages;
	}
	public void setGoodsBannerImages(List<String> goodsBannerImages) {
		this.goodsBannerImages = goodsBannerImages;
	}
	public List<String> getGoodsDetailImages() {
		return goodsDetailImages;
	}
	public void setGoodsDetailImages(List<String> goodsDetailImages) {
		this.goodsDetailImages = goodsDetailImages;
	}
}
