package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
@ApiModel("修改角色和功能权限")
public class UpdateMeunMoCommand {


    @NotNull
    @ApiModelProperty("roleId")
    private Long roleId;

    @NotNull
    @ApiModelProperty("meunId")
    private Long meunId;


    @ApiModelProperty("meunMoCommands")
    private List<MeunMoCommand> meunMoCommands;

    @NotNull
    @ApiModelProperty("0-有权限  1-无权限")
    private Integer type;


}
