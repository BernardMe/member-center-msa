package com.zzjdyf.market.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcRoleMo extends BaseEntity{

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("菜单id")
    private Long meunId;

    @ApiModelProperty("mo值")
    private Long moValue;

    @ApiModelProperty("创建人")
    private Long createUse;

}
