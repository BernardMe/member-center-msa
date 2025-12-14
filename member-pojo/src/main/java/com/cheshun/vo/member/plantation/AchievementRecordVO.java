package com.cheshun.vo.member.plantation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: new_shop
 * @description: 我的成就植株信息
 * @author: clf
 * @create: 2024-08-14 11:34
 **/
@ApiModel(description = "我的成就植株信息")
@Data
public class AchievementRecordVO {

    //1:种植中,2:已收获,3 未种植
    public static final Short IS_PLANT = 1;
    public static final Short GET_PLANT = 2;
    public static final Short IS_NOT_PLANT = 3;
    @ApiModelProperty(value = "植株id")
    private Integer plantId;

    @ApiModelProperty(value = "中药材ID")
    private Integer herbId;

    @ApiModelProperty(value = "植株状态(1:种植中,2:已收获,3 未种植)")
    private Short plantState;

    @ApiModelProperty(value = "中药材名称")
    private String herbName;

    @ApiModelProperty(value = "中药材列表图片URL")
    private String herbImgUrl;

    @ApiModelProperty(value = "中药材详情图片URL")
    private String herbImgDetailUrl;

    @ApiModelProperty(value = "中药材详情")
    private String herbDetail;
}
