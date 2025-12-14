package com.cheshun.entity.admin.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "管理员查看会员修改信息参数")
public class AdminMemberInfoDTO {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "修改日期")
    private String editDate;

    @ApiModelProperty(value = "页码")
    private String page;

    @ApiModelProperty(value = "每页数据量")
    private String pageSize;
}
