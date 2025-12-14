package com.cheshun.dto.member.purse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员储值密码修改参数")
public class MemberPursePasswordDTO {

    @ApiModelProperty("会员卡号")
    private String memberCardNo;

    @ApiModelProperty("会员新储值密码")
    private String password;

}
