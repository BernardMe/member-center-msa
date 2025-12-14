package com.cheshun.dto.commonquestion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("添加常见问题类型")
public class AddQuestionTypeDTO {

    @ApiModelProperty(value = "问题类型id")
    private String typeId;

    @ApiModelProperty(value = "问题类型名称")
    private String typeName;

    @ApiModelProperty(value = "是否展示")
    private Integer isDisplay;

    @ApiModelProperty(value = "权重 数值越大越排前")
    private Integer weightValue;
}
