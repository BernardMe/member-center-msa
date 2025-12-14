package com.cheshun.po.mall;

import lombok.Data;

import java.util.List;

@Data
public class HotSearchPO {

    private String id;

    private String words;

    private String goodsName;  // 商品名称（全文检索）

    private List<String> symptoms;  // 适用症状（全文检索，多标签）

    private String createTimeStr;

    private int clickCount;

    private double score;

}
