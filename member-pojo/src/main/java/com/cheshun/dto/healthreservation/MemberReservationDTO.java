package com.cheshun.dto.healthreservation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员预约入参")
public class MemberReservationDTO {

    @ApiModelProperty("方案编号")
    private String optionNo;

    @ApiModelProperty("项目编码")
    private String proId;

    @ApiModelProperty("会员卡号")
    private String memberCardNo;

    @ApiModelProperty("预约门店编号")
    private String storeCode;

    @ApiModelProperty("预约门店名称")
    private String storeName;

    @ApiModelProperty("预约日期")
    private String reserDate;

    @ApiModelProperty("预约时间范围")
    private String scopeTime;

    @ApiModelProperty("预约姓名")
    private String memberName;

    @ApiModelProperty("预约手机号")
    private String memberPhone;

    @ApiModelProperty("预约项目名称")
    private String proName;

    @ApiModelProperty("预约项目内容")
    private String proComment;

    @ApiModelProperty(value = "是否计入资源")
    private String isResources;

}
