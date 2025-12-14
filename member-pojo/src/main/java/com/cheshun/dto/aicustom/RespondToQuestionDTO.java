package com.cheshun.dto.aicustom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("传入问题参数")
public class RespondToQuestionDTO implements Serializable {

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;



}
