package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户填写/修改收货信息-请求信息")
public class VipTurnTablePutAddressRequestVO {
    @ApiModelProperty(value = "中奖记录ID")
    private Integer recordId;
    @ApiModelProperty(value = "收货人名")
    private String name;
    @ApiModelProperty(value = "收货人手机号")
    private String phone;
    @ApiModelProperty(value = "收货人地址")
    private String address;
}
