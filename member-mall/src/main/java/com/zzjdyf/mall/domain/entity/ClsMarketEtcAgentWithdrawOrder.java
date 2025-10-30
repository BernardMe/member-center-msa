package com.zzjdyf.mall.domain.entity;

import com.zzjdyf.mall.domain.entity.enums.WithdrawStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代理商提现订单信息表
 *
 * @author 阿隆
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentWithdrawOrder extends BaseEntity {
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 订单号
     */
    private String sn;
    /**
     * 综合服务平台流水号
     */
    private String ref;
    /**
     * 综合服务平台打款交易流水号
     */
    private String brokerBankBill;
    /**
     * 提现平台
     */
    private String withdrawPlatform;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 收款账号
     */
    private String cardNo;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 综合服务主体打款金额(元)
     */
    private BigDecimal brokerAmount;
    /**
     * 综合服务主体服务费(元)
     */
    private BigDecimal brokerFee;
    /**
     * 订单状态码
     */
    private WithdrawStatus status;
    /**
     * 订单详细状态码
     */
    private String statusDetail;
    /**
     * 订单详细状态码描述
     */
    private String statusDetailMessage;
    /**
     * 订单完成时间
     */
    private Date finishedTime;
    /**
     * 备注
     */
    private String remark;
}
