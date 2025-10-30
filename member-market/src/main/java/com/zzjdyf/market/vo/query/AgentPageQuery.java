package com.zzjdyf.market.vo.query;

import com.zzjdyf.market.domain.entity.enums.AgentAuditStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/7/20 9:20 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询代理商参数")
public class AgentPageQuery extends PageQuery {
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String realName;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 角色
     */
    @ApiModelProperty("角色")
    private Integer role;
    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private AgentAuditStatus auditStatus;
}
