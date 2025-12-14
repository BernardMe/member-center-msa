package com.cheshun.po.mall.admin;

import lombok.Data;

@Data
public class StoreJoinTagPO {
    private Integer id;
    private Integer tagId;
    private String tagName;
    private Integer tagOrder;
    private String storeId;
}