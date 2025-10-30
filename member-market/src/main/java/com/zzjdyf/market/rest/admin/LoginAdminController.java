package com.zzjdyf.market.rest.admin;

import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.market.service.admin.LoginAdminService;
import com.zzjdyf.market.service.common.JwtService;
import com.zzjdyf.market.vo.command.GetSmsCodeCommand;
import com.zzjdyf.market.vo.command.SmsLoginCommand;
import com.zzjdyf.market.vo.dto.AdminUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/7/15 8:45 上午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin")
@Api(tags = "后管系统-登陆API")
@AllArgsConstructor
public class LoginAdminController {
    private final LoginAdminService sysManagerLoginService;
    private final JwtService jwtService;

    /**
     * 获取短信验证码
     *
     * @param command 命令 {@link GetSmsCodeCommand}
     */
    @NoToken
    @AdminApi
    @PostMapping("smsCode")
    @ApiOperation("获取短信验证码")
    public void getSmsCode(@RequestBody @Valid GetSmsCodeCommand command) {
        sysManagerLoginService.send(command.getPhone());
    }

    /**
     * 手机号短信登陆
     *
     * @param command 命令 {@link SmsLoginCommand}
     * @return 管理员登陆信息 {@link AdminUserDto}
     */
    @NoToken
    @AdminApi
    @PostMapping("login")
    @ApiOperation("手机号短信登陆")
    public AdminUserDto login(@RequestBody @Valid SmsLoginCommand command) {
        return sysManagerLoginService.login(command);
    }

    /**
     * 退出登陆
     *
     * @param token 登陆token
     */
    @AdminApi
    @PostMapping("logout")
    @ApiOperation("退出登陆")
    public void logout(@RequestHeader(Const.TOKEN) String token) {
        jwtService.delete(token);
    }
}
