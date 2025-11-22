package com.cheshun.market.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cheshun.market.domain.entity.enums.AgentAuditStatus;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.GeneralStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 代理商信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgent extends BaseEntity {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 角色
     */
    private AgentRole role;
    /**
     * 上级内部员工id
     */
    private Long staffId;
    /**
     * 上级代理(一级)id
     */
    private Long agent1Id;
    /**
     * 上级代理(二级)id
     */
    private Long agent2Id;
    /**
     * 审核状态(待审核|已拒绝|已通过|已申请解约|已解约)
     */
    private AgentAuditStatus auditStatus;
    /**
     * 审核拒绝原因
     */
    private String refuseReason;
    /**
     * 状态(正常|禁用)
     */
    private GeneralStatus status;
    /**
     * 禁用原因
     */
    private String disableReason;
    /**
     * 累计激活奖励金额(元)
     */
    private BigDecimal totalActiveBonus = BigDecimal.ZERO;
    /**
     * 累计首次消费奖励金额(元)
     */
    private BigDecimal totalFirstConsumeBonus = BigDecimal.ZERO;
    /**
     * 累计提现金额(元)
     */
    private BigDecimal totalWithdrawAmount = BigDecimal.ZERO;
    /**
     * 是否可提现
     */
    private boolean enableWithdraw = true;
    /**
     * 余额(元)
     */
    @TableField(exist = false)
    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * openId
     * @return
     */
    private String openId;

    public BigDecimal getBalance() {
        return totalActiveBonus.add(totalFirstConsumeBonus).subtract(totalWithdrawAmount);
    }
}
