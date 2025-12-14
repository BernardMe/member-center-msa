package com.cheshun.vo.activity.diabetes;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiabetesShareCreateVO {
    @ApiModelProperty(value = "活动名称")
    private String name;
    @ApiModelProperty(value = "背景图")
    private String pictureUrl;
    @ApiModelProperty(value = "规则图")
    private String rulesUrl;
    @ApiModelProperty(value = "手动开关  0、关闭，1、打开")
    private String manualSwitch;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;
    @ApiModelProperty(value = "创建人")
    private String createUser;
}
