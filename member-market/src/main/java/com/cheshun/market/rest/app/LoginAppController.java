package com.cheshun.market.rest.app;

import com.cheshun.market.service.app.LoginAppService;
import com.cheshun.market.service.common.JwtService;
import com.cheshun.market.service.common.SmsService;
import com.cheshun.market.vo.command.GetSmsCodeCommand;
import com.cheshun.market.vo.command.SmsLoginCommand;
import com.cheshun.market.vo.dto.AgentAppDto;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.NoToken;
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
@RequestMapping("clsapi/market/etc/app")
@Api(tags = "移动端-登陆API")
@AllArgsConstructor
public class LoginAppController {
    private final LoginAppService loginAppService;
    private final JwtService jwtService;
    private final SmsService smsService;

    /**
     * 获取短信验证码
     *
     * @param command 命令 {@link GetSmsCodeCommand}
     */
    @NoToken
    @PostMapping("smsCode")
    @ApiOperation("获取短信验证码")
    public void getSmsCode(@RequestBody @Valid GetSmsCodeCommand command) {
        smsService.send(command.getPhone());
    }

    /**
     * 手机号短信登陆
     *
     * @param command 命令 {@link SmsLoginCommand}
     * @return 代理商信息 {@link AgentAppDto}
     */
    @NoToken
    @PostMapping("login")
    @ApiOperation("手机号短信登陆")
    public AgentAppDto login(@RequestBody @Valid SmsLoginCommand command) {
        return loginAppService.login(command);
    }

    /**
     * 退出登陆
     *
     * @param token 登陆token
     */
    @NoToken
    @PostMapping("logout")
    @ApiOperation("退出登陆")
    public void logout(@RequestHeader(value = Const.TOKEN, required = false) String token) {
        jwtService.delete(token);
    }
}
