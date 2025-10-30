package com.zzjdyf.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.market.config.GoodsBrandConfig;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGoodsSku;
import com.zzjdyf.market.service.FileService;
import com.zzjdyf.market.service.admin.GoodsAdminService;
import com.zzjdyf.market.service.admin.SyncManageService;
import com.zzjdyf.market.vo.command.AddGoodsCommand;
import com.zzjdyf.market.vo.command.DeleteGoodsCommand;
import com.zzjdyf.market.vo.command.UpdateGoodsCommand;
import com.zzjdyf.market.vo.dto.GoodsAdminDto;
import com.zzjdyf.market.vo.dto.GoodsSkuAdminDto;
import com.zzjdyf.market.vo.query.GoodsPageQuery;
import com.zzjdyf.market.vo.query.IdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/goods")
@Api(tags = "后管系统-商品管理API")
@AllArgsConstructor
public class GoodsAdminController {
    private final FileService fileService;
    private final GoodsAdminService goodsAdminService;
    private final SyncManageService syncManageService;

    /**
     * 上传商品图片
     *
     * @param file {@link MultipartFile}
     */
    @AdminApi
    @PostMapping("upload")
    @ApiOperation("上传商品图片")
    public String upload(MultipartFile file) {
        return fileService.upload("goodsImage", file);
    }

    /**
     * 获取商品品牌列表
     *
     * @return 商品品牌列表 {@link List < GoodsBrandConfig.Brand>}
     */
    @NoToken
    @AdminApi
    @PostMapping("brandList")
    @ApiOperation("获取商品品牌列表")
    public List<GoodsBrandConfig.Brand> brandList() {
        return goodsAdminService.brandList();
    }

    /**
     * 新增商品
     *
     * @param command {@link AddGoodsCommand}
     */
    @AdminApi
    @PostMapping("add")
    @ApiOperation("新增商品")
    public void add(@RequestBody @Valid AddGoodsCommand command) {
        goodsAdminService.add(command);
    }

    /**
     * 更新商品
     *
     * @param command {@link UpdateGoodsCommand}
     */
    @AdminApi
    @PostMapping("update")
    @ApiOperation("更新商品")
    public void update(@RequestBody @Valid UpdateGoodsCommand command) {
        goodsAdminService.update(command);
    }

    /**
     * 根据ID查询商品
     *
     * @param query 条件 {@link IdQuery}
     * @return 商品  {@link GoodsAdminDto}
     */
    @AdminApi
    @PostMapping
    @ApiOperation("根据ID查询商品")
    public GoodsAdminDto get(@RequestBody @Valid IdQuery query) {
        return goodsAdminService.get(query);
    }

    /**
     * 分页查询商品
     *
     * @param query {@link GoodsPageQuery}
     * @return 商品列表  {@link IPage<GoodsAdminDto>}
     */
    @AdminApi
    @PostMapping("query")
    @ApiOperation("分页查询商品")
    public IPage<GoodsAdminDto> query(@RequestBody @Valid GoodsPageQuery query) {
        return goodsAdminService.query(query);
    }

    /**
     * 删除商品
     *
     * @param command {@link DeleteGoodsCommand}
     */
    @AdminApi
    @PostMapping("delete")
    @ApiOperation("删除商品")
    public void update(@RequestBody @Valid DeleteGoodsCommand command) {
        goodsAdminService.delete(command);
    }

    /**
     * 同步商品销量
     */
    @AdminApi
    @PostMapping(value = "syncSales")
    @ApiOperation("同步商品销量")
    public void syncSales() {
        // 同步商品库存
        syncManageService.syncSales();
    }

    /**
     * 获取商品规格详情
     *
     * @return 商品规格详情 {@link ClsMarketEtcGoodsSku}
     */
    @AdminApi
    @PostMapping("sku")
    @ApiOperation("获取商品规格详情")
    public GoodsSkuAdminDto getSku(@RequestBody @Valid IdQuery query) {
        return goodsAdminService.getSku(query);
    }

}
