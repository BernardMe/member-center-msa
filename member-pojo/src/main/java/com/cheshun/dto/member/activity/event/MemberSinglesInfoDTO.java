package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "会员晒单内容")
@Data
public class MemberSinglesInfoDTO implements Serializable {

    @ApiModelProperty(value = "起始页")
    private String page;

    @ApiModelProperty(value = "每页条数")
    private String pageSize;

    @ApiModelProperty(value = "排序类型 1:时间倒序排序 2:点赞数量排序")
    private String sortType;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "方案编号eventsId")
    private String eventsId;

}
