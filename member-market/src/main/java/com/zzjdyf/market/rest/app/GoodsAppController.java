package com.zzjdyf.market.rest.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.service.app.GoodsAppService;
import com.zzjdyf.market.vo.dto.GoodsAppDto;
import com.zzjdyf.market.vo.query.IdQuery;
import com.zzjdyf.market.vo.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangzhuo
 * Created on 20210803
 */
@RestController
@RequestMapping("clsapi/market/etc/app/goods")
@Api(tags = "移动端-商品API")
@AllArgsConstructor
public class GoodsAppController {
    private final GoodsAppService goodsAppService;

    /**
     * 分页查询商品列表
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param query     条件 {@link PageQuery}
     * @return 商品列表  {@link IPage< GoodsAppDto >}
     */
    @PostMapping("query")
    @ApiOperation("分页查询商品列表")
    public IPage<GoodsAppDto> query(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                    @RequestBody @Valid PageQuery query) {
        return goodsAppService.query(loginInfo.getId(), query);
    }

    /**
     * 根据ID查询商品
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param query     条件 {@link IdQuery}
     * @return 商品  {@link GoodsAppDto}
     */
    @PostMapping("detail")
    @ApiOperation("根据ID查询商品")
    public GoodsAppDto get(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                           @RequestBody @Valid IdQuery query) {
        return goodsAppService.get(loginInfo.getId(), query);
    }
}
