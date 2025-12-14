package com.cheshun.vo.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "筛选条件参数")
public class FilterKeyVO implements Serializable {

    @ApiModelProperty(value = "厂家，多个用逗号分隔")
    private String originManufStr;

    @ApiModelProperty(value = "是否处方药")
    private String isRx;

    @ApiModelProperty(value = "症状标签")
    private String symptomsLabel;

    @ApiModelProperty(value = "发货类型(0:默认 1:O2O 2:B2C)")
    private Byte deliveryType;

}
