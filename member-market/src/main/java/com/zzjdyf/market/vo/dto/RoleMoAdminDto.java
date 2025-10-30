package com.zzjdyf.market.vo.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("角色与Mo信息")
public class RoleMoAdminDto extends BaseDto{
    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("菜单id")
    private Long meunId;

    @ApiModelProperty("mo值")
    private Long moValue;

    @ApiModelProperty("创建人")
    private Long createUse;

    @ApiModelProperty("mo类型")
    private Integer moType;

    @ApiModelProperty("mo名称")
    private String moName;

}
