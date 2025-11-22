package com.cheshun.market.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcRoleMeun extends BaseEntity{

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("菜单id")
    private Long meunId;

    @ApiModelProperty("状态 0-启用 1-停用")
    private Integer status;
}
