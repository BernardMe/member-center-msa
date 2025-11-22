package com.cheshun.market.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询员工参数")
public class AccountPageQuery extends PageQuery {

    @ApiModelProperty("用户名称/登录账号")
    private String accountName;

    @ApiModelProperty("状态")
    private String status;

}
