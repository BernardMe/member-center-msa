package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@Data
@ToString
@ApiModel("员工信息")
public class StuffCommand {

    /**
     * 姓名
     */
    @NotNull(message = "请输入姓名")
    @Size(min = 2, max = 20, message = "姓名长度为20位")
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 手机号
     */
    @NotNull(message = "请输入手机号")
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    @ApiModelProperty("手机号")
    private String phone;
}
