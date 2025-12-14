package com.cheshun.dto.commonquestion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询常见问题")
public class QueryQuestionDTO {

    @ApiModelProperty(value = "页码")
    private String page;

    @ApiModelProperty(value = "每页数量")
    private String pageSize;

    @ApiModelProperty(value = "问题类型id")
    private String typeId;

    @ApiModelProperty(value = "问题")
    private String question;
}
