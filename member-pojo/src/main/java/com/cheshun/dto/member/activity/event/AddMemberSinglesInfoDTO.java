package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "添加会员晒单内容")
@Data
public class AddMemberSinglesInfoDTO implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "会员名称")
    private String memberName;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "晒单内容")
    private String reviewContent;

    @ApiModelProperty(value = "晒单图片")
    private String reviewImages;

    @ApiModelProperty(value = "会员姓名")
    private String username;

    @ApiModelProperty(value = "楼层")
    private Long floor;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "方案编号eventsId")
    private String eventsId;
}
