package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@ApiModel("新增用户")
public class AddAccountCommand {



    @NotNull(message = "请选择角色")
    @ApiModelProperty("角色id")
    private Long roleId;


    @NotNull(message = "请输入登录账号")
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    @ApiModelProperty("登录账号")
    private String phone;

    @NotNull(message = "请输入用户姓名")
    @Size(min = 1, max = 100, message = "用户姓名为1位到100位")
    @ApiModelProperty("用户姓名")
    private String accountName;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty("状态")
    private String status;


}
