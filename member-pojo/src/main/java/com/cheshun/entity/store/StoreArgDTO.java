package com.cheshun.entity.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StoreArgDTO {
    @ApiModelProperty("门店编号/名称")
    private String storeArg;

    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面大小")
    private int pageSize;
}
