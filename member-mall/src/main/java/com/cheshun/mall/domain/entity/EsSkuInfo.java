package com.cheshun.mall.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * SkuInfo ES实体类(映射于表goods_infos)来表示商品sku信息
 * @author wangzhuo
 * @date 20251025
 */
@Document(indexName = "zzjdyf_sku_info")
public class EsSkuInfo {

    /**
     * 指定sku主键
     */
    @Id
    private String skuId;
    /**
     * 商品ID
     */
    @Field(type = FieldType.Integer)
    private Integer goodsId;
    /**
     * 商品货号,不分词
     */
    @Field(type = FieldType.Keyword)
    private String goodsCode;
    /**
     * 商品名称（全文检索）
     */
    @Field(
            type = FieldType.Text,
            analyzer = "ik_max_word",  // 索引时分词（细粒度）
            searchAnalyzer = "ik_smart"  // 搜索时分词（粗粒度）
    )
    private String goodsName;
    /**
     * 商品副标题/描述（全文检索）
     */
    @Field(
            type = FieldType.Text,
            analyzer = "ik_max_word",  // 索引时分词（细粒度）
            searchAnalyzer = "ik_smart"  // 搜索时分词（粗粒度）
    )
    private String subTitle;
    /**
     * 零售价(单位厘)
     */
    @Field(type = FieldType.Integer)
    private Long retailPrice;
    /**
     * 商品主图
     */
    @Field(type = FieldType.Text)
    private String mainImage;
    /**
     * 边框图片
     */
    @Field(type = FieldType.Text)
    private String borderImage;
    /**
     * 商品状态，0-下架，1-正常
     */
    @Field(type = FieldType.Integer)
    private Byte status;
    /**
     * 只用后两个注解就可以实现LocalDateTime不分成多个Field，但是格式不对。
     * 所以还需要添加前面两个注解去指定格式与时区
     **/
    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;//
    /**
     * 更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;
    /**
     * 是否默认
     */
    private Byte isDefault;
    /**
     * 是否O2O
     */
    @Field(type = FieldType.Integer)
    private Byte isO2o;
    /**
     * 是否B2C
     */
    @Field(type = FieldType.Integer)
    private Byte isB2c;
    /**
     * SPU_ID
     */
    @Field(type = FieldType.Keyword)
    private String spuId;
    /**
     * 类目ID
     */
    @Field(type = FieldType.Integer)
    private Integer categoryId;
    /**
     * 类目名称,不分词
     */
    @Field(type = FieldType.Keyword)
    private String categoryName;
    /**
     * 品牌名称，不分词
     */
    @Field(type = FieldType.Keyword)
    private String originManuf;
    /**
     * 规格
     */
    @Field(type = FieldType.Keyword)
    private String spec;
    /**
     * 规格参数
     */
    @Field(type = FieldType.Keyword)
    private Map<String, Object> specMap;
    /**
     * 库存数量
     */
    @Field(type = FieldType.Integer)
    private Integer num;
    /**
     * 适用症状（全文检索，多标签）,不分词
     */
    @Field(
            type = FieldType.Text,
            analyzer = "ik_max_word",  // 索引时分词（细粒度）
            searchAnalyzer = "ik_smart"  // 搜索时分词（粗粒度）
    )
    private String symptoms;
    /**
     * 门店编码（多个用逗号拼接）
     */
    @Field(
            type = FieldType.Text,
            analyzer = "ik_max_word",  // 索引时分词（细粒度）
            searchAnalyzer = "ik_smart"  // 搜索时分词（粗粒度）
    )
    private String subbhStr;

    // Getters and Setters


    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Long getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getBorderImage() {
        return borderImage;
    }

    public void setBorderImage(String borderImage) {
        this.borderImage = borderImage;
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

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Byte getIsO2o() {
        return isO2o;
    }

    public void setIsO2o(Byte isO2o) {
        this.isO2o = isO2o;
    }

    public Byte getIsB2c() {
        return isB2c;
    }

    public void setIsB2c(Byte isB2c) {
        this.isB2c = isB2c;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOriginManuf() {
        return originManuf;
    }

    public void setOriginManuf(String originManuf) {
        this.originManuf = originManuf;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Map<String, Object> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(Map<String, Object> specMap) {
        this.specMap = specMap;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSubbhStr() {
        return subbhStr;
    }

    public void setSubbhStr(String subbhStr) {
        this.subbhStr = subbhStr;
    }
}