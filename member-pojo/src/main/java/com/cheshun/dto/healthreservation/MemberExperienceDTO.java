package com.cheshun.dto.healthreservation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员体验入参")
public class MemberExperienceDTO{

    @ApiModelProperty("方案编号")
    private String optionNo;

    @ApiModelProperty("项目编码")
    private String proId;

    @ApiModelProperty("会员卡号")
    private String memberCardNo;

    @ApiModelProperty("是否计入数量")
    private String isResources;

    @ApiModelProperty("消耗资源")
    private String resourcesNum;

    @ApiModelProperty("满意度 0:不满意 1:满意 2:非常满意")
    private String isSatisfaction;
}
