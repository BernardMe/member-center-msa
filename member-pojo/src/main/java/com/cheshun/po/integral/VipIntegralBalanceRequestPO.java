package com.cheshun.po.integral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipIntegralBalanceRequestPO {
    /**
     * 企业号
     */
    private String entId;
    /**
     * 会员卡号
     */
    private String memberCardNo;
}
