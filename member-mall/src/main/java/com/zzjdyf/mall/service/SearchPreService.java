package com.zzjdyf.mall.service;

import com.zzjdyf.common.component.page.PageCondition;
import com.zzjdyf.mall.vo.dto.*;
import com.zzjdyf.common.result.Result;

import javax.servlet.http.HttpServletRequest;
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
