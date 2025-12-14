package com.cheshun.vo.mall.admin;

import lombok.Data;

@Data
public class GoodsJoinCategoryUpdateVO {
    private Integer id;
    private Integer categoryId;
    private Integer sortOrder;
    private String icon;
    private Integer status;
    private Integer isHot;
    private String updateBy;

}