package com.cheshun.entity.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("根据经纬度查询附近门店")
public class StoreCoordinateDTO {

    @ApiModelProperty("经度")
    private double lng;
    @ApiModelProperty("维度")
    private double lat;
    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面大小")
    private int pageSize;
}
