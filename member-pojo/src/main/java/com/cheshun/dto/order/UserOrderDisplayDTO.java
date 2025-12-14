package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户端订单展示参数")
@Data
public class UserOrderDisplayDTO {

    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    @ApiModelProperty("订单状态 0: 全部 1：未付款 2：待发货 3:待收货 4:已完成  5: 已取消 6已退款 7.售后中")
    private Integer orderStatus;
}
