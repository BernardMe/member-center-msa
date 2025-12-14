package com.cheshun.dto.commonquestion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("查询分类")
@Data
public class QueryQuestionTypeDTO implements Serializable {

    @ApiModelProperty(value = "页码")
    private String page;

    @ApiModelProperty(value = "每页数量")
    private String pageSize;

    @ApiModelProperty(value = "分类名称")
    private String typeName;

    @ApiModelProperty(value = "是否显示")
    private Integer isDisplay;


}
