package com.cheshun.market.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/8/25 6:15 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("移动端-分页查询下级成员参数")
public class MemberPageQuery extends PageQuery {
    /**
     * 代理商id
     */
    @ApiModelProperty("代理商id")
    private Long agentId;
}
