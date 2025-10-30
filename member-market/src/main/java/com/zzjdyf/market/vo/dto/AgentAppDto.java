package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.AgentAuditStatus;
import com.zzjdyf.market.domain.entity.enums.AgentRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author wangzhuo
 * Created on 20210729
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("个人信息")
public class AgentAppDto extends BaseDto {
    /**
     * 含前缀的代理商id
     */
    @ApiModelProperty("含前缀的代理商id")
    private String agentId;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;
    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idNo;
    /**
     * 角色
     */
    @ApiModelProperty("角色")
    private AgentRole role;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;
    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private AgentAuditStatus auditStatus;
    /**
     * 审核状态名称
     */
    @ApiModelProperty("审核状态名称")
    private String auditStatusName;
    /**
     * 累计激活奖励金额(元)
     */
    @ApiModelProperty("累计激活奖励金额(元)")
    private BigDecimal totalActiveBonus = BigDecimal.ZERO;
    /**
     * 累计首次消费奖励金额(元)
     */
    @ApiModelProperty("累计首次消费奖励金额(元)")
    private BigDecimal totalFirstConsumeBonus = BigDecimal.ZERO;
    /**
     * 累计提现金额(元)
     */
    @ApiModelProperty("累计提现金额(元)")
    private BigDecimal totalWithdrawAmount = BigDecimal.ZERO;
    /**
     * 余额(元)
     */
    @ApiModelProperty("余额(元)")
    private BigDecimal balance = BigDecimal.ZERO;
    /**
     * 累计获得分佣(元)
     */
    @ApiModelProperty("累计获得分佣(元)")
    private BigDecimal totalBonus = BigDecimal.ZERO;
    /**
     * 是否可提现
     */
    @ApiModelProperty("是否可提现")
    private Boolean canWithdraw = Boolean.FALSE;
    /**
     * 登陆token
     */
    @ApiModelProperty("登陆token")
    private String token;
}
