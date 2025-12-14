package com.cheshun.dto.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分类查询首页商品信息参数")
public class QueryHomePageGoodsDTO {

    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面大小")
    private int pageSize;

    @ApiModelProperty("分类id")
    private Long moduleId;
}
