package com.zzjdyf.market.domain.entity;

import com.zzjdyf.market.domain.entity.enums.DataAuth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcRoleDataAuth extends BaseEntity{

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("权限")
    private DataAuth dataAuth;

}
