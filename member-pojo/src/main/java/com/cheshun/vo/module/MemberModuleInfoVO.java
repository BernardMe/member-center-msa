package com.cheshun.vo.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("小程序图标信息VO")
public class MemberModuleInfoVO implements Serializable {

    @ApiModelProperty("模板id")
    private String moduleId;

    @ApiModelProperty("是否启用 1:启用 0:禁用")
    private String enable;

    @ApiModelProperty("模板名称")
    private String moduleName;

    @ApiModelProperty("模板父id")
    private String parentId;

    @ApiModelProperty("模板排序")
    private String moduleSort;

    @ApiModelProperty("图片链接")
    private String pictureUrl;

    @ApiModelProperty("跳转小程序路径")
    private String jumpPath;

    @ApiModelProperty("页面类型 0：分区 1：详情 2：轮播图")
    private String pageType;

    private List<MemberModuleInfoVO> memberModuleInfoVOList;
}
