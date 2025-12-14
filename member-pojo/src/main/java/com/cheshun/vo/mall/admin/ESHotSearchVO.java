package com.cheshun.vo.mall.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ESHotSearchVO {

    /**
     * 指定主键
     */
    @ApiModelProperty("指定主键")
    private String id;
    /**
     * 指定字段类型为文本
     */
    @ApiModelProperty("词语")
    private String words;
    /**
     * 指定商品名称类型为文本
     */
    @ApiModelProperty("商品名")
    private String goodsName;  // 商品名称（全文检索）
    /**
     * 指定适用症状类型为文本列表
     */
    @ApiModelProperty("适用症")
    private List<String> symptoms;  // 适用症状（全文检索，多标签）
    /**
     * 点击数类型为整数
     */
    @ApiModelProperty("点击数")
    private int clickCount;
    /**
     * 权重类型为整数
     */
    @ApiModelProperty("权重")
    private int score;

    @Override
    public String toString() {
        return "ESHotSearchVO{" +
                "id='" + id + '\'' +
                ", words='" + words + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", symptoms=" + symptoms +
                ", clickCount=" + clickCount +
                ", score=" + score +
                '}';
    }
}
