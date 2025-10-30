package com.zzjdyf.auth.service.user.login;

import com.zzjdyf.auth.vo.dto.*;
import com.zzjdyf.common.result.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 微信登录
     * @param param
     * @return
     */
    Result<UserLoginVO> wxLogin(WeChatLoginParam param);

    Result<UserTokenInfo> reflush(String refreshToken);

    Result logout();

    Result wechatPhoneLogin(WechatMinappPhoneVPO phoneData, HttpServletRequest request);

    Result register(RegisterInfoPO registerInfoPO, HttpServletRequest request);

    Result disable(String memberCardNo, String unionid);
}
