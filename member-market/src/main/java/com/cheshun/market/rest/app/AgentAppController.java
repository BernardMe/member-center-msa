package com.cheshun.market.rest.app;

import com.cheshun.market.service.app.AgentAppService;
import com.cheshun.market.vo.command.AddAgentCommand;
import com.cheshun.market.vo.dto.AgentAppDto;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.NoToken;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@RestController
@RequestMapping("clsapi/market/etc/app/agent")
@Api(tags = "移动端-代理商API")
@AllArgsConstructor
public class AgentAppController {
    private final AgentAppService agentAppService;

    /**
     * 根据用户id查询代理商信息
     *
     * @param loginInfo {@link LoginInfo}
     * @return 代理商信息 {@link AgentAppDto}
     */
    @PostMapping
    @ApiOperation("根据用户id查询代理商信息")
    public AgentAppDto get(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        return agentAppService.get(loginInfo.getId());
    }

    /**
     * 新增代理
     *
     * @param command {@link AddAgentCommand}
     * @return 代理商信息 {@link AgentAppDto}
     */
    @NoToken
    @PostMapping("add")
    @ApiOperation("新增代理")
    public AgentAppDto add(@RequestBody @Valid AddAgentCommand command) {
        return agentAppService.add(command);
    }

    /**
     * 代理商申请解约
     *
     * @param loginInfo {@link LoginInfo}
     */
    @PostMapping("terminate")
    @ApiOperation("代理商申请解约")
    public void terminated(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        agentAppService.terminate(loginInfo.getId());
    }
}
