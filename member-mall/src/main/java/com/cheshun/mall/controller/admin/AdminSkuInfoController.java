package com.cheshun.mall.controller.admin;

import com.cheshun.common.annotation.PageInfoParam;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.mall.service.admin.AdminEsSkuInfoService;
import com.cheshun.mall.vo.dto.EsSkuInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cheshun.common.result.Result.SUCC_CODE;

@Api(tags = "管理端-商品Sku")
@RestController
@RequestMapping("/admin/mall/sku")
public class AdminSkuInfoController {

    @Autowired
    private AdminEsSkuInfoService adminEsSkuInfoService;

    /*@ApiOperation("查询商品")
    @PostMapping("/query")
    public Result query(@RequestBody GoodsQueryRequest request) {
        return goodsInfosAdminService.query(request);
    }*/


    @PostMapping("/es/import")
    public Result importData(){
        adminEsSkuInfoService.importData();
        return ResultUtil.result(SUCC_CODE, "后管-刷新导入商品sku到ES接口数据导入成功");
    }

    @ApiOperation("后管-ES新增单个商品SKU")
    @PostMapping("/es/add")
    public Result add(@RequestBody EsSkuInfoVO esSkuInfoVO) {
        Result result = adminEsSkuInfoService.add(esSkuInfoVO);
        return result;
    }

    @ApiOperation("后管-ES根据skuId修改单个商品SKU")
    @PostMapping("/es/update")
    public Result update(@RequestBody EsSkuInfoVO esSkuInfoVO) {
        Result result = adminEsSkuInfoService.update(esSkuInfoVO);
        return result;
    }

    @ApiOperation("后管-ES根据skuId删除单个商品SKU")
    @PostMapping("/es/delete")
    public Result delete(@RequestBody EsSkuInfoVO esSkuInfoVO) {
        Result result = adminEsSkuInfoService.delete(esSkuInfoVO);
        return result;
    }

    @ApiOperation("后管-ES按条件查询商品SKU（分页）")
    @GetMapping("/es/list")
    public Result query(
            @PageInfoParam PageCondition pageCondition,
            // 优先级范围（可选）
            @RequestParam(required = false) Integer minSort,
            @RequestParam(required = false) Integer maxSort,
            // 商品名（模糊匹配，可选）
            @RequestParam(required = false) String goodsName,
            // 适用症状（多个用逗号分隔，可选）
            @RequestParam(required = false) String symptoms
    ) {
        Result result = adminEsSkuInfoService.adminQueryByCondition(pageCondition, minSort, maxSort, goodsName, symptoms);
        return result;
    }

}
