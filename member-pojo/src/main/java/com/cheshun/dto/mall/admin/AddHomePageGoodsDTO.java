package com.cheshun.dto.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("添加首页商品参数")
@Data
public class AddHomePageGoodsDTO {

    @ApiModelProperty(value = "商品货号")
    private String goodsCode;

    @ApiModelProperty(value = "分类id")
    private Long moduleId;

    @ApiModelProperty(value = "排序")
    private Integer goodsSort;
}
