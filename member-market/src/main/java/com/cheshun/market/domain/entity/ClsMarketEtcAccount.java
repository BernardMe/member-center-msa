package com.cheshun.market.domain.entity;


import com.cheshun.market.domain.entity.enums.AccountStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAccount extends BaseEntity {

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("姓名")
    private String accountName;

    @ApiModelProperty("登录账户")
    private String phone;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("状态")
    private AccountStatus status;

    @ApiModelProperty("创建人id")
    private Long createUseId;

    @ApiModelProperty("token")
    private String token;
}
