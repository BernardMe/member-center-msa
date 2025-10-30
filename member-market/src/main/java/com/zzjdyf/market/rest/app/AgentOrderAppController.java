package com.zzjdyf.market.rest.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.service.app.AgentOrderAppService;
import com.zzjdyf.market.vo.command.AddOrderCommand;
import com.zzjdyf.market.vo.command.AddRepleshOrderCommand;
import com.zzjdyf.market.vo.command.AddRepleshUpOrderCommand;
import com.zzjdyf.market.vo.command.AddReturnOrderCommand;
import com.zzjdyf.market.vo.dto.CanReplenishAppDto;
import com.zzjdyf.market.vo.dto.OrderAppDto;
import com.zzjdyf.market.vo.query.IdQuery;
import com.zzjdyf.market.vo.query.OrderAppPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangzhuo
 * Created on 20210725
 */
@RestController
@RequestMapping("clsapi/market/etc/app/agent/order")
@Api(tags = "移动端-代理订单API")
@AllArgsConstructor
public class AgentOrderAppController {
    private final AgentOrderAppService agentOrderAppService;

    /**
     * 创建订货订单
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param command   命令 {@link AddOrderCommand}
     */
    @PostMapping("order")
    @ApiOperation("创建订货订单")
    public void order(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                      @RequestBody @Valid AddOrderCommand command) {
        agentOrderAppService.order(loginInfo.getId(), command);
    }

    /**
     * 待补货明细
     *
     * @return 待补货明细  {@link CanReplenishAppDto}
     */
    @PostMapping("canReplenish")
    @ApiOperation("待补货明细")
    public CanReplenishAppDto canReplenish(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        return agentOrderAppService.canReplenish(loginInfo.getId());
    }

    /**
     * 提标补货待补货明细
     *
     * @param query 条件 {@link IdQuery}
     * @return 待补货明细  {@link CanReplenishAppDto}
     */
    @PostMapping("canReplenishUp")
    @ApiOperation("待补货明细")
    public CanReplenishAppDto canReplenishUp(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                             @RequestBody  @Valid IdQuery query) {
        return agentOrderAppService.canReplenishUp(loginInfo.getId(), query.getId());
    }

    /**
     * 创建补货订单
     *
     * @param command 补货订单信息 {@link AddRepleshOrderCommand}
     */
    @PostMapping("replenish")
    @ApiOperation("创建补货订单")
    public void createReplenish(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                @RequestBody @Valid AddRepleshOrderCommand command) {
        agentOrderAppService.createReplenishOrder(loginInfo.getId(), command);
    }

    /**
     * 创建提标补货订单
     *
     * @param command 提标补货订单信息 {@link AddRepleshUpOrderCommand}
     */
    @PostMapping("replenishUp")
    @ApiOperation("创建补货订单")
    public void createReplenishUp(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                @RequestBody @Valid AddRepleshUpOrderCommand command) {
        agentOrderAppService.createReplenishUpOrder(loginInfo.getId(), command);
    }

    /**
     * 创建退货订单
     *
     * @param command 退货订单信息 {@link AddReturnOrderCommand}
     */
    @PostMapping("return")
    @ApiOperation("创建退货订单")
    public void createReturn(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                @RequestBody @Valid AddReturnOrderCommand command) {
        agentOrderAppService.createReturnOrder(loginInfo.getId(), command);
    }

    /**
     * 查询个人订单
     *
     * @param query 条件 {@link OrderAppPageQuery}
     * @return 个人订单  {@link IPage< OrderAppDto >}
     */
    @PostMapping("query")
    @ApiOperation("查询个人订单")
    public IPage<OrderAppDto> queryOrder(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                         @RequestBody @Valid OrderAppPageQuery query) {
        return agentOrderAppService.query(loginInfo.getId(), query);
    }

    /**
     * 查询个人订单详情
     *
     * @param query 条件 {@link OrderAppPageQuery}
     * @return 个人订单  {@link OrderAppDto}
     */
    @PostMapping("get")
    @ApiOperation("查询个人订单详情")
    public OrderAppDto queryOrderDetail(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                        @RequestBody @Valid IdQuery query) {
        return agentOrderAppService.get(loginInfo.getId(), query);
    }
}
