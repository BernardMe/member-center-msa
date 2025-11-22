package com.cheshun.market.vo.dto;

import com.cheshun.market.domain.entity.enums.AgentAuditStatus;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.GeneralStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("代理商信息")
public class AgentAdminDto extends BaseDto {
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
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private AgentAuditStatus auditStatus;
    /**
     * 审核拒绝原因
     */
    @ApiModelProperty("审核拒绝原因")
    private String refuseReason;
    /**
     * 状态(正常|禁用)
     */
    @ApiModelProperty("状态(正常|禁用)")
    private GeneralStatus status;
    /**
     * 禁用原因
     */
    @ApiModelProperty("禁用原因")
    private String disableReason;
    /**
     * 累计激活奖励金额(元)
     */
    @ApiModelProperty("累计激活奖励金额(元)")
    private BigDecimal totalActiveBonus;
    /**
     * 累计首次消费奖励金额(元)
     */
    @ApiModelProperty("累计首次消费奖励金额(元)")
    private BigDecimal totalFirstConsumeBonus;
    /**
     * 累计提现金额(元)
     */
    @ApiModelProperty("累计提现金额(元)")
    private BigDecimal totalWithdrawAmount;
    /**
     * 是否可提现
     */
    @ApiModelProperty("是否可提现")
    private boolean enableWithdraw;
    /**
     * 余额(元)
     */
    @ApiModelProperty("余额(元)")
    private BigDecimal balance;
    /**
     * 登陆token
     */
    @ApiModelProperty("登陆token")
    private String token;
}
