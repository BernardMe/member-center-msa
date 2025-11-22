package com.cheshun.common.enumeration;

public enum WechatErrorCode {

    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL", "失败"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"),
    INVALID_REQUEST("INVALID_REQUEST", "非法请求"),
    INVALID_PARAMETER("INVALID_PARAMETER", "参数错误"),
    INVALID_SIGN("INVALID_SIGN", "签名错误"),
    INVALID_AUTH("INVALID_AUTH", "无效授权"),
    INVALID_OPENID("INVALID_OPENID", "无效openid");

    private String errCode;
    private String errMsg;
    WechatErrorCode(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    public String getErrCode() {
        return errCode;
    }
    public String getErrMsg() {
        return errMsg;
    }



}
