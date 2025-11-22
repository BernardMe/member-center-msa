package com.cheshun.market.vo.command;

import com.cheshun.market.domain.entity.enums.DataAuth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
@ApiModel("修改角色和数据权限")
public class UpdateRoleDataCommand {


    @NotNull
    @ApiModelProperty("roleId")
    private Long roleId;

    @NotNull
    @ApiModelProperty("dataAuth")
    private DataAuth dataAuth;


}
