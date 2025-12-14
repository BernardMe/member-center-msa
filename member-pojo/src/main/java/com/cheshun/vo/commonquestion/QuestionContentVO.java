package com.cheshun.vo.commonquestion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "问题内容")
public class QuestionContentVO {

    @ApiModelProperty(value = "问题id")
    private String id;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "问题类型id")
    private Integer typeId;

    @ApiModelProperty(value = "问题类型名称")
    private String typeName;

    @ApiModelProperty(value = "创建时间")
    private String createtime;

    @ApiModelProperty(value = "修改时间")
    private String modifytime;



}
