package com.zzjdyf.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.entity.enums.DataAuth;
import com.zzjdyf.market.service.admin.OrderGoodsAdminService;
import com.zzjdyf.market.service.admin.SyncManageService;
import com.zzjdyf.market.vo.dto.OrderGoodsAdminDto;
import com.zzjdyf.market.vo.query.IdQuery;
import com.zzjdyf.market.vo.query.OrderGoodsPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/7/28 6:50 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/order/goods")
@Api(tags = "后管系统-设备管理API")
@AllArgsConstructor
public class OrderGoodsAdminController {
    private final OrderGoodsAdminService orderGoodsAdminService;
    private final SyncManageService syncManageService;

    /**
     * 分页查询设备列表
     *
     * @param query 条件 {@link OrderGoodsPageQuery}
     * @return 订单商品列表  {@link IPage< OrderGoodsAdminDto >}
     */
    @AdminApi
    @PostMapping("query")
    @ApiOperation("分页查询设备列表")
    public IPage<OrderGoodsAdminDto> query(@RequestBody @Valid OrderGoodsPageQuery query,@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        if(DataAuth.Personal == loginInfo.getDataAuth()){
            return orderGoodsAdminService.queryPersonal(query,loginInfo);
        }
        return orderGoodsAdminService.query(query);
    }

    /**
     * 确定损坏的卡/设备已退还
     *
     * @param query 条件 {@link IdQuery}
     */
    @AdminApi
    @PostMapping("confirmBrokenReturned")
    @ApiOperation("确定损坏的卡/设备已退还")
    public void confirmBrokenReturned(@RequestBody @Valid IdQuery query) {
        orderGoodsAdminService.confirmBrokenReturned(query);
    }

    /**
     * 同步设备激活状态
     */
    @AdminApi
    @PostMapping(value = "sync")
    @ApiOperation("同步设备激活状态")
    public void sync() {
        syncManageService.syncCardAndDeviceStatus();
    }




}
