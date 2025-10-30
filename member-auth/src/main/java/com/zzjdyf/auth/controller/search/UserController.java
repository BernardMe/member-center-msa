package com.zzjdyf.auth.controller.search;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zzjdyf.auth.service.user.login.UserService;
import com.zzjdyf.auth.vo.dto.*;
import com.zzjdyf.common.annotation.MemberBaseParam;
import com.zzjdyf.common.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user/member")
@Api(tags = "C端用户相关接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    @AfterLoginCheck
    @PostMapping("/loginForCode")
    @ApiOperation("微信登录")
    @ApiOperationSupport(order = 1)
    @ApiResponses({
            @ApiResponse(code = 10000, message = "需获取手机号获取会员信息", response = UserLoginVO.class),
            @ApiResponse(code = 1, message = "登录成功", response = Result.class),
            @ApiResponse(code = 0, message = "微信认证失败，重新登录", response = Result.class),
            @ApiResponse(code = 500, message = "服务器内部错误", response = Result.class)
    })
    public Result<UserLoginVO> loginForCode(@RequestBody WeChatLoginParam param) {
        log.info("微信用户登录：{}", param.getCode());
        return userService.wxLogin(param);
    }

//    @AfterLoginCheck
    @ApiOperation(value = "微信用户手机号登录", notes = "当微信账号登录时，可能存在已经是会员，但是没有进行微信账号绑定的情况，此时使用微信手机号登录。")
    @PostMapping("/login/phone")
    @ApiOperationSupport(order = 2)
    @ApiResponses({
            @ApiResponse(code = 10001, message = "未查询到会员信息，去注册", response = UserLoginVO.class),
            @ApiResponse(code = 1, message = "登录成功", response = Result.class),
            @ApiResponse(code = 0, message = "微信认证失败，重新登录", response = Result.class),
            @ApiResponse(code = 500, message = "服务器内部错误", response = Result.class)
    })
    public Result wechatPhoneLogin(
            @RequestBody WechatMinappPhoneVPO phoneData, HttpServletRequest request) {
        return userService.wechatPhoneLogin(phoneData, request);
    }

//    @AfterLoginCheck
    @PostMapping("/logout")
    @ApiOperation("微信登出")
    @ApiOperationSupport(order = 3)
    public Result logout() {
        return userService.logout();
    }

    @GetMapping("/reflush")
    @ApiOperation("获取新的accesstoken")
    @ApiOperationSupport(order = 4)
    public Result<UserTokenInfo> reflush(@RequestParam String refreshToken) {
        return userService.reflush(refreshToken);
    }

    @PostMapping("/register")
    @ApiOperation("注册办卡")
    @ApiOperationSupport(order = 5)
    public Result register(@RequestBody RegisterInfoPO registerInfoPO, HttpServletRequest request) {
        return userService.register(registerInfoPO, request);
    }

    @GetMapping("/disable")
    @ApiOperation("禁用会员卡")
    @ApiOperationSupport(order = 6)
    public Result disable(@MemberBaseParam MemberLoginInfo memberLoginInfo, String memberCardNo) {
        if (!memberLoginInfo.getMemberCardNo().equals(memberCardNo))
            return Result.error("不能注销别人的卡号哦！");
        return userService.disable(memberCardNo, memberLoginInfo.getUnionid());
    }
}
