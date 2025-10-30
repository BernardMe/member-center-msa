package com.zzjdyf.market.vo.dto;


import com.zzjdyf.market.domain.entity.enums.RoleStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("角色信息")
public class RoleAdminDto extends BaseDto {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDesc;

    @ApiModelProperty("限制人数")
    private Integer limitNumber;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("状态")
    private RoleStatus status;

    @ApiModelProperty("是否关联")
    private Integer relation =0;


}
