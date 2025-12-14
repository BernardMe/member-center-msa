package com.cheshun.dto.member.purse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询会员钱包信息入参")
public class MemberPurseInfoDTO {

    @ApiModelProperty("会员卡号")
    private String memberCardNo;

}
