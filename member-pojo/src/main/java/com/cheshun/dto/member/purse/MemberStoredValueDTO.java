package com.cheshun.dto.member.purse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberStoredValueDTO {

    @ApiModelProperty("储值卡号")
    private String prepaidCard;

    @ApiModelProperty("会员新储值密码")
    private String password;
}
