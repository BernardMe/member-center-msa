package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询备用抽奖次数-请求信息")
public class AdminGetPlayTimesRequestVO {

    @ApiModelProperty(value = "转盘ID")
    private Integer turntableId;

    @ApiModelProperty(value = "卡号")
    private String cardCode;
}
