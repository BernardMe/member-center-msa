package com.cheshun.dto.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("添加首页分类数据")
public class AddHomePageDTO {

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("分类名称")
    private String moduleName;

    @ApiModelProperty("是否启用")
    private String enable;

    @ApiModelProperty("排序字段 越小越往前")
    private Integer moduleSort;

    @ApiModelProperty("图片")
    private String image;
}
