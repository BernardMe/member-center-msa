package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@ApiModel("新增角色")
public class AddRoleCommand {

    @NotNull(message = "请输入角色名称")
    @Size(min = 1, max = 100, message = "角色名称长度为1-100位")
    @ApiModelProperty("角色名称")
    private String roleName;

    @NotNull(message = "请输入角色描述")
    @Size(min = 1, max = 200, message = "角色描述长度为1-200位")
    @ApiModelProperty("角色描述")
    private String roleDesc;

    @NotNull(message = "请输入限制人数")
    @Size(min = 1, max = 3, message = "限制人数为1-999")
    @ApiModelProperty("限制人数")
    private String limitNumber;
}
