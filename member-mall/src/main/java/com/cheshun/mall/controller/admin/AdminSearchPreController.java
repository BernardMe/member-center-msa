package com.cheshun.mall.controller.admin;

import com.cheshun.common.annotation.PageInfoParam;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.mall.service.admin.AdminHotSearchService;
import com.cheshun.mall.vo.dto.ESHotSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后管接口-商城首页热搜
 * @author wangzhuo
 * @date 20251025
 */
@Api(tags = "管理接口-商城首页热搜管理")
@RestController
@RequestMapping("/admin/search/pre")
public class AdminSearchPreController {

    @Autowired
    private AdminHotSearchService adminHotSearchService;

    @ApiOperation("批量添加")
    @PostMapping("/hot-search/batch")
    public Result batchAdd(@RequestBody List<ESHotSearchVO> hotSearchVOList) {
        Result result = adminHotSearchService.batchAdd(hotSearchVOList);
        return result;
    }

    @ApiOperation("新增单个热搜词")
    @PostMapping("/hot-search/add")
    public Result add(@RequestBody ESHotSearchVO esHotSearchVO) {
        Result result = adminHotSearchService.add(esHotSearchVO);
        return result;
    }

    @ApiOperation("根据id修改单个热搜词")
    @PostMapping("/hot-search/update")
    public Result update(@RequestBody ESHotSearchVO esHotSearchVO) {
        Result result = adminHotSearchService.update(esHotSearchVO);
        return result;
    }

    @ApiOperation("根据id删除单个热搜词")
    @PostMapping("/hot-search/delete")
    public Result delete(@RequestBody ESHotSearchVO esHotSearchVO) {
        Result result = adminHotSearchService.delete(esHotSearchVO);
        return result;
    }

   /* @ApiOperation("查看单个商品")
    @GetMapping("/one")
    public EntityResult<ESGoodsSearchDTO> getById(@RequestParam String id) {
        Optional<ESGoodsSearchDTO> ESGoodsSearchDTO = adminHotSearchService.getById(id);
        ESGoodsSearchDTO esGoodsSearchDTO = ESGoodsSearchDTO.orElseGet(com.zzjdyf.pt.servers.db.domain.es.ESGoodsSearchDTO::new);
        return ResultUtil.entResult(esGoodsSearchDTO);
    }

    @ApiOperation("批量删除商品")
    @DeleteMapping("/batch")
    public OptResult batchDelete(@RequestBody List<ESGoodsSearchDTO> ESGoodsSearchDTOs) {
        adminHotSearchService.batchDelete(ESGoodsSearchDTOs);
        return UsualResult.OPERATION_SUCCESS;
    }

    @ApiOperation("清空商品")
    @DeleteMapping("/all")
    public OptResult allDelete() {
        adminHotSearchService.allDelete();
        return UsualResult.OPERATION_SUCCESS;
    }*/


    @ApiOperation("后管-按条件查询热搜词（分页）")
    @GetMapping("/hot-search/list")
    public Result query(
            @PageInfoParam PageCondition pageCondition,
            // 优先级范围（可选）
            @RequestParam(required = false) Integer minSort,
            @RequestParam(required = false) Integer maxSort,
            // 商品名（模糊匹配，可选）
            @RequestParam(required = false) String goodsName,
            // 适用症状（多个用逗号分隔，可选）
            @RequestParam(required = false) List<String> symptoms
    ) {
        Result result = adminHotSearchService.adminQueryByCondition(pageCondition, minSort, maxSort, goodsName, symptoms);
        return result;
    }
}