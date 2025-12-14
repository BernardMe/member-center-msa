package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加备用抽奖次数-请求信息")
public class AdminAddPlayTimesRequestVO {
    @ApiModelProperty(value = "转盘ID")
    private Integer turntableId;

    @ApiModelProperty(value = "卡号")
    private String cardCode;

    @ApiModelProperty(value = "次数")
    private Integer times;
}
