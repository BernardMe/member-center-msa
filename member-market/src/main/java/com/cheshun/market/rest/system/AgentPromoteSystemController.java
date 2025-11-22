package com.cheshun.market.rest.system;

import com.cheshun.market.service.system.AgentPromoteSystemService;
import com.cheshun.market.vo.command.ActiveNotifyCommand;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.NoToken;
import com.cheshun.common.annotation.SystemApi;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/8/12 9:32 上午.
 */
@Slf4j
@RestController
@RequestMapping("clsapi/market/etc/sys/agent/promote")
@AllArgsConstructor
@Api(tags = "系统-代理商推广API")
public class AgentPromoteSystemController {
    private final AgentPromoteSystemService agentPromoteSystemService;

    /**
     * 设备激活结果通知
     *
     * @param command 命令 {@link ActiveNotifyCommand}
     */
    @NoToken
    @SystemApi
    @PostMapping("active/notify")
    @ApiOperation(value = "设备激活结果通知")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "success",
                    response = String.class,
                    examples = @Example(@ExampleProperty(value = "ok"))
            )
    })
    public String activeNotify(@ApiParam("角色id: 1表示系统角色") @RequestHeader(Const.ROLE) Long roleId,
                               @RequestBody @Valid ActiveNotifyCommand command) {
        return agentPromoteSystemService.activeNotify(command);
    }
}
