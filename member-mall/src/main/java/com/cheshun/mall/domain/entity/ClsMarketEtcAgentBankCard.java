package com.cheshun.mall.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 代理商银行卡信息表
 *
 * @author 阿隆
 * Created on 2021/8/2 2:44 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentBankCard extends BaseEntity {
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 银行卡开户姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行开户卡号
     */
    private String cardNo;
    /**
     * 手机号码
     */
    private String phoneNum;
}