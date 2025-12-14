package com.cheshun.dto.mall.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询商品首页分类信息")
public class QueryGoodsMallPageDTO {

    @ApiModelProperty("页码 不必传pageSize 每次固定10条")
    private int pageNum;


    @ApiModelProperty("分类id")
    private Long moduleId;
}
