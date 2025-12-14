package com.cheshun.dto.commonquestion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "添加常见问题")
public class AddQuestionDTO implements Serializable {

    @ApiModelProperty(value = "问题id")
    private String id;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "问题类型id")
    private Integer typeId;
}
