package com.cheshun.vo.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "门店位置信息参数")
public class MZBNearbyInfoVO implements Serializable {

    @ApiModelProperty(value = "门诊部名称")
    private String subName;

    @ApiModelProperty(value = "馆长个人企微二维码")
    private String qrCode;

    @ApiModelProperty(value = "门诊部编号")
    private String subbh;

    @ApiModelProperty(value = "门诊部地址")
    private String mzbAdress;

    @ApiModelProperty(value = "馆长")
    private String curator;
}
