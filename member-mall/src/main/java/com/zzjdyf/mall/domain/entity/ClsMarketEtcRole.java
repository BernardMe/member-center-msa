package com.zzjdyf.mall.domain.entity;

import com.zzjdyf.mall.domain.entity.enums.RoleStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcRole extends BaseEntity {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDesc;

    @ApiModelProperty("限制人数")
    private Integer limitNumber;

    @ApiModelProperty("创建人")
    private Long createId;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("状态")
    private RoleStatus status;


}
