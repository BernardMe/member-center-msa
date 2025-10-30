package com.zzjdyf.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.entity.enums.DataAuth;
import com.zzjdyf.market.service.admin.AgentOrderAdminService;
import com.zzjdyf.market.service.admin.SyncManageService;
import com.zzjdyf.market.vo.command.ConfirmPaidCommand;
import com.zzjdyf.market.vo.command.DealReturnCommand;
import com.zzjdyf.market.vo.command.DeliverCommand;
import com.zzjdyf.market.vo.dto.AgentOrderAdminDto;
import com.zzjdyf.market.vo.query.AgentTradePageQuery;
import com.zzjdyf.market.vo.query.IdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/order")
@Api(tags = "后管系统-订单管理API")
@AllArgsConstructor
public class AgentOrderAdminController {
    private final AgentOrderAdminService agentOrderAdminService;
    private final SyncManageService syncManageService;

    /**
     * 分页查询订单列表
     *
     * @param query 条件 {@link AgentTradePageQuery}
     * @return 订单列表  {@link IPage< AgentOrderAdminDto >}
     */
    @AdminApi
    @PostMapping("query")
    @ApiOperation("分页查询订单列表")
    public IPage<AgentOrderAdminDto> query(@RequestBody @Valid AgentTradePageQuery query,@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        if(DataAuth.Personal == loginInfo.getDataAuth()){
            return agentOrderAdminService.queryPersonal(query,loginInfo);
        }
        return agentOrderAdminService.query(query);
    }

    /**
     * 确认已缴纳金额
     *
     * @param command 登录信息 {@link LoginInfo}
     * @param command 命令 {@link ConfirmPaidCommand}
     */
    @AdminApi
    @PostMapping("confirmPaid")
    @ApiOperation("确认已缴纳金额")
    public void confirmPaid(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                            @RequestBody @Valid ConfirmPaidCommand command) {
        agentOrderAdminService.confirmPaid(loginInfo, command);
    }

    /**
     * 发货
     *
     * @param command 登录信息 {@link LoginInfo}
     * @param command 命令 {@link DeliverCommand}
     */
    @AdminApi
    @PostMapping(value = "deliver")
    @ApiOperation("发货")
    public void deliver(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                        @RequestBody @Valid DeliverCommand command) {
        agentOrderAdminService.deliver(loginInfo, command);
    }

    /**
     * 取消订单
     *
     * @param loginInfo 登录信息 {@link LoginInfo}
     * @param query 订单id  {@link IdQuery}
     */
    @AdminApi
    @PostMapping("cancel")
    @ApiOperation("取消订单")
    public void cancel(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                     @RequestBody @Valid IdQuery query) {
        agentOrderAdminService.cancel(loginInfo, query);
    }

    /**
     * 查询订单详情
     *
     * @param query 条件 {@link AgentTradePageQuery}
     * @return 订单列表  {@link IPage<AgentOrderAdminDto>}
     */
    @AdminApi
    @PostMapping("detail")
    @ApiOperation("查询订单详情")
    public AgentOrderAdminDto detail(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                     @RequestBody @Valid IdQuery query) {
        return agentOrderAdminService.detail(loginInfo, query);
    }

    /**
     * 处理退货订单
     *
     * @param command 登录信息 {@link LoginInfo}
     * @param command 命令 {@link DealReturnCommand}
     */
    @AdminApi
    @PostMapping("dealReturn")
    @ApiOperation("处理退货订单")
    public void dealReturn(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                            @RequestBody @Valid DealReturnCommand command) {
        agentOrderAdminService.dealReturn(loginInfo, command);
    }

    /**
     * 拒绝订单
     *
     * @param loginInfo 登录信息 {@link LoginInfo}
     * @param query 订单id  {@link IdQuery}
     */
    @AdminApi
    @PostMapping("refuse")
    @ApiOperation("拒绝订单")
    public void refuse(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                       @RequestBody @Valid IdQuery query) {
        agentOrderAdminService.refuse(loginInfo, query);
    }

    /**
     * 同步订单数据
     */
    @AdminApi
    @PostMapping(value = "sync")
    @ApiOperation("同步订单数据")
    public void sync() {
        // 同步订单信息到ES
        syncManageService.syncOrder();
    }

    /**
     * 创建测试数据
     */
    @NoToken
    @AdminApi
    @PostMapping("createMockData")
    @ApiOperation("创建测试数据")
    public void createMockData() {
//        agentOrderAdminService.createMockData();
    }
}
