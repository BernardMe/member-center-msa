package com.cheshun.dto.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "小程序页面模块添加参数")
public class MemberModuleAddDTO {

    @ApiModelProperty(value = "模块id --在该模块下新增")
    private String moduleId;

    @ApiModelProperty(value = "启用标志 0:禁用 1:启用")
    private String enable;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "模块排序--越低排序越高")
    private Integer moduleSort;

    @ApiModelProperty(value = "模块图片")
    private String pictureUrl;

    @ApiModelProperty(value = "跳转路径")
    private String jumpPath;

    @ApiModelProperty(value = "页面类型 0：分区 1：详情 2：轮播图")
    private Integer pageType;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "修改人")
    private String modifyUser;
}
