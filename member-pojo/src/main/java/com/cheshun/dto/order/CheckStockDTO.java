package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

@ApiModel(value = "判断门店对应商品库存是否充足入参")
@Data
public class CheckStockDTO {

    @ApiModelProperty(value = "门店编号")
    @NonNull
    private String storeCode;

    @ApiModelProperty(value = "商品编号")
    @NonNull
    private String goodsCode;

    @ApiModelProperty(value = "商品数量")
    @NonNull
    private Integer goodsNumber;


    public CheckStockDTO() {
    }
}
