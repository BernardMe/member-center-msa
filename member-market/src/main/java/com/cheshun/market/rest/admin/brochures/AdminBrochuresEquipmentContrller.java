package com.cheshun.market.rest.admin.brochures;

import com.cheshun.common.annotation.PageInfoParam;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.market.domain.po.admin.brochures.BrochuresEquipmentPO;
import com.cheshun.market.service.admin.brochures.BrochuresEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "医疗器械图册")
@RestController
@RequestMapping("/admin/brochures/equipment")
public class AdminBrochuresEquipmentContrller {

    @Autowired
    private BrochuresEquipmentService brochuresEquipmentService;

    @ApiOperation("上传保存医疗器械记录")
    @PostMapping("/upload")
    public Result uploadBrochuresEquipmentInfo(
            @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return brochuresEquipmentService.uploadBrochuresEquipmentInfo(file);
    }

    @ApiOperation("批量同步OMS商品信息")
    @PostMapping("/oms/sync")
    public Result syncOmsGoodsInfoBatch(
            @RequestBody List<BrochuresEquipmentPO> equipmentInfoList, HttpServletRequest request) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return brochuresEquipmentService.syncOmsGoodsInfoBatch(equipmentInfoList);
    }


    @ApiOperation("查询医疗器械分组列表信息")
    @GetMapping("/list/category")
    public Result queryEquipmentByCategory(
            @PageInfoParam PageCondition pageCondition,
            @RequestParam(value = "categoryName", required = false) String categoryName,
            @RequestParam(value = "equipmentName", required = false) String equipmentName,
            @RequestParam(value = "goodsCode", required = false) String goodsCode, HttpServletRequest request) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return brochuresEquipmentService.adminQueryEquipmentByCategory(pageCondition, categoryName, equipmentName, goodsCode);
    }

    @ApiOperation("根据货号查询医疗器械详情")
    @GetMapping("/list/detail")
    public Result queryEquipmentByDetail(
            @RequestParam(value = "goodsCode") String goodsCode, HttpServletRequest request) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return brochuresEquipmentService.queryEquipmentDetail(goodsCode);
    }

    @ApiOperation("根据货号更新医疗器械信息")
    @PostMapping("/update")
    public Result updateEquipmentInfo(
            @RequestBody BrochuresEquipmentPO brochuresEquipmentPO, HttpServletRequest request) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return brochuresEquipmentService.updateEquipmentInfo(brochuresEquipmentPO);
    }

    @ApiOperation("查询器械目录分类列表")
    @GetMapping("/category/list")
    public Result queryEquipmentCategory(
            @PageInfoParam PageCondition pageCondition,
            @RequestParam(value = "categoryName", required = false) String categoryName,
            @RequestParam(value = "parentCode", required = false) String parentCode, HttpServletRequest request) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return brochuresEquipmentService.adminQueryEquipmentCategoryList(pageCondition, categoryName, parentCode);
    }
}
