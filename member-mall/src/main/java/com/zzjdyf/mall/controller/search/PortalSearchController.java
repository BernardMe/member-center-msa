package com.zzjdyf.mall.controller.search;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zzjdyf.common.annotation.PageInfoParam;
import com.zzjdyf.common.component.page.PageCondition;
import com.zzjdyf.mall.service.SearchEsSkuService;
import com.zzjdyf.mall.service.SearchPreService;
import com.zzjdyf.mall.vo.dto.*;
import com.zzjdyf.common.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal/search")
@Api(tags = "首页搜索业务接口")
@Slf4j
public class PortalSearchController {

    @Autowired
    private SearchPreService searchPreService;
    @Autowired
    private SearchEsSkuService searchEsSkuService;

    @ApiOperation("会员端-首页获取热搜词列表")
    @PostMapping("/pre/top-searches")
    public Result<List<HotSearchPO>> portalGetHotwordList(@RequestBody HotwordListParam param) {
        log.info("会员卡号：{}", param.getMemberCardNo());

        return searchPreService.portalGetHotwordList(param);
    }

    @ApiOperation("会员端-搜索前搜索提示")
    @GetMapping("/pre/suggestions")
    public Result portalSearchGetSuggestions(
            @PageInfoParam PageCondition pageCondition,
            @RequestParam("keyword") String keyword,
            @RequestParam("memberCardNo") String memberCardNo) {

        return searchPreService.portalGetSuggestionList(pageCondition, keyword, memberCardNo);
    }

    @ApiOperation("会员端-根据输入项多维搜索商品列表")
    @PostMapping("/post/faceted-search")
    public Result portalPostFacetedSearch(@RequestBody FacetedSearchVO facetedSearchVO) {

        return searchEsSkuService.portalPostFacetedSearch(facetedSearchVO);
    }


}
