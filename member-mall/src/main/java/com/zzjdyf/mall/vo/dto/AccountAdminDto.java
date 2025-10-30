package com.zzjdyf.mall.vo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzjdyf.mall.domain.entity.enums.AccountStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("员工信息")
public class AccountAdminDto extends BaseDto{


    @ApiModelProperty("角色id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;

    @ApiModelProperty("姓名")
    private String accountName;

    @ApiModelProperty("登录账户")
    private String phone;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("状态")
    private AccountStatus status;

    @ApiModelProperty("角色名称")
    private String roleName;
}
