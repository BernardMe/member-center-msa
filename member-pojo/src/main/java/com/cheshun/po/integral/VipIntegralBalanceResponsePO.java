package com.cheshun.po.integral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipIntegralBalanceResponsePO {
    /**
     *是否成功
     */
    private Boolean status;
    /**
     *返回数据（成功时返回）
     */
    private String data;
    /**
     *错误提示信息
     */
    private String message;
    /**
     *错误码
     */
    private String errorCode;
}
