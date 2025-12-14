package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询售后信息入参")
@Data
public class AfterSalesDTO {

    @ApiModelProperty("售后状态 0.全部 1.申请中 2.退款完成")
    private Integer afterSalesStatus;

    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("每页数量")
    private Integer pageSize;

}
