package com.cheshun.po.integral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 云POS-修改会员积分余额接口请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipIntegralUpdateBalanceRequestPO {
    /**
     * 企业号
     */
    private String entId;
    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 变化积分
     */
    private Double changePoint;
    /**
     * 通用积分类型
     */
    private String pointGeneralType;
    /**
     *渠道号 5.会员小程序 6.商城小程序
     */
    private String sourceCode;
    /**
     * 单据号
     */
    private String billNo;
    /**
     * 幂等字段
     */
    private String seqNo;
}
