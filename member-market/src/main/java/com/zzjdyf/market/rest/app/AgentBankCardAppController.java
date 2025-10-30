package com.zzjdyf.market.rest.app;

import com.zzjdyf.common.Const;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.service.app.AgentBankCardAppService;
import com.zzjdyf.market.vo.command.AddBankCardCommand;
import com.zzjdyf.market.vo.command.DeleteBankCardCommand;
import com.zzjdyf.market.vo.dto.AgentBankCardDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/8/4 11:25 上午.
 */
@RestController
@RequestMapping("clsapi/market/etc/app/bankCard")
@Api(tags = "移动端-银行卡管理API")
@AllArgsConstructor
public class AgentBankCardAppController {
    private final AgentBankCardAppService agentBankCardAppService;

    /**
     * 添加银行卡
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param command   命令 {@link AddBankCardCommand}
     */
    @PostMapping("add")
    @ApiOperation("添加银行卡")
    public void add(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                    @RequestBody @Valid AddBankCardCommand command) {
        agentBankCardAppService.add(loginInfo.getId(), command);
    }

    /**
     * 删除银行卡
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param command   命令 {@link DeleteBankCardCommand}
     */
    @PostMapping("delete")
    @ApiOperation("删除银行卡")
    public void delete(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                       @RequestBody @Valid DeleteBankCardCommand command) {
        agentBankCardAppService.delete(loginInfo.getId(), command);
    }

    /**
     * 查询银行卡列表
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @return 银行卡列表 {@link List< AgentBankCardDto >}
     */
    @PostMapping
    @ApiOperation("查询银行卡列表")
    public List<AgentBankCardDto> query(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        return agentBankCardAppService.query(loginInfo.getId());
    }
}
