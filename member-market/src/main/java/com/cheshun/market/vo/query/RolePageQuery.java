package com.cheshun.market.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询角色参数")
public class RolePageQuery extends PageQuery {

    @ApiModelProperty("角色名称/创建人名称")
    private String roleName;

    @ApiModelProperty("状态")
    private Integer status;

}
