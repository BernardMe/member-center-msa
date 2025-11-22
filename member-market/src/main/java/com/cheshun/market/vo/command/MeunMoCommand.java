package com.cheshun.market.vo.command;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("功能权限")
public class MeunMoCommand {


    @ApiModelProperty("moValue")
    private Long moValue;

}
