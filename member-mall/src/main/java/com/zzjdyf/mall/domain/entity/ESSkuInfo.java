package com.zzjdyf.mall.domain.entity;

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
import java.util.List;

/**
 * SkuInfo 实体类来表示搜索日志
 * @author wangzhuo
 * @date 20251025
 */
@Document(indexName = "zzjdyf_sku_info")
public class ESSkuInfo {

    /**
     * 指定主键
     */
    @Id
    private String id;
    /**
     * 指定字段类型为文本
     */
    @Field(type = FieldType.Text)
    private String words;
    /**
     * 指定商品名称类型为文本
     */
    @Field(
            type = FieldType.Text,
            analyzer = "ik_max_word",  // 索引时分词（细粒度）
            searchAnalyzer = "ik_smart"  // 搜索时分词（粗粒度）
    )
    private String goodsName;  // 商品名称（全文检索）
    /**
     * 指定适用症状类型为文本列表
     */
    @Field(
            type = FieldType.Text,
            analyzer = "ik_max_word"
    )
    private List<String> symptoms;  // 适用症状（全文检索，多标签）
    /**
     * 指定字段类型为日期
     */
    @Field(type = FieldType.Text, pattern = "yyyy-MM-dd HH:mm:ss")
    private String timestamp;
    /**
     * 指定字段类型为关键字
     */
    @Field(type = FieldType.Keyword)
    private String userId;
    /**
     * 指定字段类型为整数
     */
    @Field(type = FieldType.Integer)
    private int clickCount;
    /**
     * 只用后两个注解就可以实现LocalDateTime不分成多个Field，但是格式不对。
     * 所以还需要添加前面两个注解去指定格式与时区
     **/
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;//创建时间

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;//更新时间

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}