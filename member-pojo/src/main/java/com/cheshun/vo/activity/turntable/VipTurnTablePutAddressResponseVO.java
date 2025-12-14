package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户查询收货信息-返回信息")
public class VipTurnTablePutAddressResponseVO {
    @ApiModelProperty(value = "收货人名")
    private String name;
    @ApiModelProperty(value = "收货人手机号")
    private String phone;
    @ApiModelProperty(value = "收货人地址")
    private String address;
}
