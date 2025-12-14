package com.cheshun.entity.aicustom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("AI客服参数")
public class AiCustomerDTO implements Serializable {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "问题")
    private String question;
}
