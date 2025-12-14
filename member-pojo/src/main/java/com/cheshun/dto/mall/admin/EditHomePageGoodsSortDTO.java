package com.cheshun.dto.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "修改分类商品排序")
public class EditHomePageGoodsSortDTO {

    @ApiModelProperty(value = "商品信息id")
    private Long id;

    @ApiModelProperty(value = "排序")
    private Integer goodsSort;
}
