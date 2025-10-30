package com.zzjdyf.common.crminterface;

/**
 * 小程序相关接口
 */
public class WechatRelated {

    // 微信登录接口 get
    public static final String WE_CHAT_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    // 检验登录态 get
    public static final String WE_CHAT_CHECK_LOGIN = "https://api.weixin.qq.com/wxa/checksession?access_token=ACCESS_TOKEN&signature=SIGNATURE&openid=OPENID&sig_method=SIG_METHOD";

    // 获取接口调用凭据 get
    public static final String WE_CHAT_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
}
