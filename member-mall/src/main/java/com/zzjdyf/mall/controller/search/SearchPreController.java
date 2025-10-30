package com.zzjdyf.mall.controller.search;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zzjdyf.common.annotation.PageInfoParam;
import com.zzjdyf.common.component.page.PageCondition;
import com.zzjdyf.mall.service.SearchPreService;
import com.zzjdyf.mall.vo.dto.*;
import com.zzjdyf.common.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/portal/search/pre")
@Api(tags = "搜索前置业务接口")
@Slf4j
public class SearchPreController {

    @Autowired
    private SearchPreService searchPreService;


    @ApiOperation("会员端-首页获取热搜词列表")
    @PostMapping("/top-searches")
    public Result<List<HotSearchPO>> portalGetHotwordList(@RequestBody HotwordListParam param) {
        log.info("会员卡号：{}", param.getMemberCardNo());
        return searchPreService.portalGetHotwordList(param);
    }


    @ApiOperation("首页根据输入项返回热搜词建议")
    @GetMapping("/suggestions")
    public Result portalGetSuggestionList(
            @PageInfoParam PageCondition pageCondition,
            @RequestParam("keyword") String keyword,
            @RequestParam("memberCardNo") String memberCardNo) {
        return searchPreService.portalGetSuggestionList(pageCondition, keyword, memberCardNo);
    }


}
