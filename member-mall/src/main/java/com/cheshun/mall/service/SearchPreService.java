package com.cheshun.mall.service;

import com.cheshun.common.component.page.PageCondition;
import com.cheshun.mall.vo.dto.HotSearchPO;
import com.cheshun.mall.vo.dto.HotwordListParam;
import com.cheshun.common.result.Result;

import java.util.List;

public interface SearchPreService {

    /**
     * 会员端-首页获取热搜词列表
     * @param param
     * @return
     */
    Result<List<HotSearchPO>> portalGetHotwordList(HotwordListParam param);
    /**
     * 会员端-首页根据输入项返回热搜词建议
     * @param keyword
     * @param memberCardNo
     * @return
     */
    Result portalGetSuggestionList(PageCondition pageCondition, String keyword, String memberCardNo);


}
