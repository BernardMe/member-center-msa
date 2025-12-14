package com.cheshun.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.market.domain.entity.enums.DataAuth;
import com.cheshun.market.service.admin.AgentAdminService;
import com.cheshun.market.vo.command.*;
import com.cheshun.market.vo.dto.AgentAdminDto;
import com.cheshun.market.vo.dto.AgentDashboardAdminVo;
import com.cheshun.market.vo.query.AgentPageQuery;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.AdminApi;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/agent")
@Api(tags = "后管系统-代理商管理API")
@AllArgsConstructor
public class AgentAdminController {
    private final AgentAdminService agentAdminService;

    /**
     * 代理数量概况统计查询
     *
     * @return 概况统计 {@link AgentDashboardAdminVo}
     */
    @AdminApi
    @PostMapping("dashboard")
    @ApiOperation("代理数量概况统计查询")
    public AgentDashboardAdminVo dashboard(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        if(DataAuth.Personal == loginInfo.getDataAuth()){
            return agentAdminService.dashboardPersonal(loginInfo);
        }
        return agentAdminService.dashboard();
    }

    /**
     * 添加员工
     *
     * @param command {@link AddStaffCommand}
     */
    @AdminApi
    @PostMapping("add/staff")
    @ApiOperation("添加员工")
    public void addStaff(@RequestBody @Valid AddStaffCommand command) {
        agentAdminService.addStaff(command);
    }

    /**
     * 更新代理商
     *
     * @param command {@link UpdateAgentCommand}
     */
    @AdminApi
    @PostMapping("update")
    @ApiOperation("更新代理商")
    public void update(@RequestBody @Valid UpdateAgentCommand command) {
        agentAdminService.update(command);
    }

    /**
     * 分页查询代理商
     *
     * @param query 条件 {@link AgentPageQuery}
     * @return 代理商列表  {@link IPage<  AgentAdminDto  >}
     */
    @AdminApi
    @PostMapping("query")
    @ApiOperation("分页查询代理商")
    public IPage<AgentAdminDto> query(@RequestBody @Valid AgentPageQuery query,@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {

        if(DataAuth.Personal == loginInfo.getDataAuth()){
            return agentAdminService.queryPersonal(query,loginInfo);
        }
        return agentAdminService.query(query);
    }

    /**
     * 审核代理商
     *
     * @param command {@link AuditAgentCommand}
     */
    @AdminApi
    @PostMapping("audit")
    @ApiOperation("审核代理商")
    public void audit(@RequestBody @Valid AuditAgentCommand command) {
        agentAdminService.audit(command);
    }

    /**
     * 同意代理商的解约申请
     *
     * @param command {@link TerminatedCommand}
     */
    @AdminApi
    @PostMapping("terminated")
    @ApiOperation("同意代理商的解约申请")
    public void terminated(@RequestBody @Valid TerminatedCommand command) {
        agentAdminService.terminated(command);
    }

    /**
     * 拒绝代理商的解约申请
     *
     * @param command {@link TerminatedCommand}
     */
    @AdminApi
    @PostMapping("terminated/refuse")
    @ApiOperation("拒绝代理商的解约申请")
    public void refuseTerminated(@RequestBody @Valid TerminatedCommand command) {
        agentAdminService.refuseTerminated(command);
    }

    @AdminApi
    @PostMapping("batchUpdate")
    @ApiOperation("批量更新提现状态")
    public void batchUpdate(@RequestBody @Valid UpdateBatchAgentCommand updateBatchAgentCommand) {
        agentAdminService.batchUpdate(updateBatchAgentCommand);
    }


}
