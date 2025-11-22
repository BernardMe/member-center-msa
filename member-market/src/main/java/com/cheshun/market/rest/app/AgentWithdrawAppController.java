package com.cheshun.market.rest.app;

import com.cheshun.market.service.app.AgentWithdrawAppService;
import com.cheshun.market.vo.command.AgentWithdrawCommand;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.NoToken;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/8/4 3:08 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/app/withdraw")
@Api(tags = "移动端-提现API")
@AllArgsConstructor
public class AgentWithdrawAppController {
    private final AgentWithdrawAppService agentWithdrawAppService;

    /**
     * 提现
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param command   命令 {@link AgentWithdrawCommand}
     */
    @PostMapping
    @ApiOperation("提现")
    public void withdraw(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                         @RequestBody @Valid AgentWithdrawCommand command) {
        agentWithdrawAppService.withdraw(loginInfo.getId(), command);
    }

    /**
     * 提现结果通知
     *
     * @param data      通知参数
     * @param mess      通知参数
     * @param timestamp 通知参数
     * @param sign      通知参数
     * @param sign_type 通知参数
     * @param response  {@link HttpServletResponse}
     */
    @NoToken
    @PostMapping(value = "notify", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ApiOperation("提现结果通知")
    public void notify(String data, String mess, String timestamp, String sign, String sign_type, HttpServletResponse response) {
        agentWithdrawAppService.notify(data, mess, timestamp, sign, sign_type, response);
    }
}
