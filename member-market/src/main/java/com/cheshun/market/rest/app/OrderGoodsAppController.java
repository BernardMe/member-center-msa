package com.cheshun.market.rest.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.market.service.app.OrderGoodsAppService;
import com.cheshun.market.vo.command.ConfirmStatusCommand;
import com.cheshun.market.vo.dto.OrderGoodsAppDto;
import com.cheshun.market.vo.query.IdQuery;
import com.cheshun.market.vo.query.OrderGoodsAppPageQuery;
import com.cheshun.market.vo.query.OrderGoodsMngAppPageQuery;
import com.cheshun.common.Const;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangzhuo
 * Created on 20210802
 */
@RestController
@RequestMapping("clsapi/market/etc/app/order/goods")
@Api(tags = "移动端-订单商品API")
@AllArgsConstructor
public class OrderGoodsAppController {
    private final OrderGoodsAppService orderGoodsAppService;

    /**
     * 分页查询我的或下级成员的订单商品列表
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param query     条件 {@link OrderGoodsAppPageQuery}
     * @return 我的订单商品列表  {@link IPage<  OrderGoodsAppDto  >}
     */
    @PostMapping("query")
    @ApiOperation("分页查询我的或下级成员的订单商品列表")
    public IPage<OrderGoodsAppDto> query(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                         @RequestBody @Valid OrderGoodsAppPageQuery query) {
        return orderGoodsAppService.query(loginInfo.getId(), query);
    }

    /**
     * 分页查询我的订单商品管理列表
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param query     条件 {@link OrderGoodsMngAppPageQuery}
     * @return 我的订单商品管理列表  {@link IPage<OrderGoodsAppDto>}
     */
    @PostMapping("query4Manage")
    @ApiOperation("分页查询我的订单商品管理列表")
    public IPage<OrderGoodsAppDto> query4Manage(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                         @RequestBody @Valid OrderGoodsMngAppPageQuery query) {
        return orderGoodsAppService.query4Manage(loginInfo.getId(), query);
    }

    /**
     * 确定损坏的卡/设备
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param command   条件 {@link IdQuery}
     */
    @PostMapping("confirmBroken")
    @ApiOperation("确定损坏的卡/设备")
    public void confirmBroken(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                              @RequestBody @Valid ConfirmStatusCommand command) {
        orderGoodsAppService.confirmBroken(loginInfo.getId(), command);
    }
}
