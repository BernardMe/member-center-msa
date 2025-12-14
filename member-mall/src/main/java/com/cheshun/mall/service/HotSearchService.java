package com.cheshun.mall.service;

import com.cheshun.common.result.Result;

/**
 * 计算热搜词及热度Service
 * @author wangzhuo
 */
public interface HotSearchService {

    /**
     * 定时计算热搜词及热度
     * @return
     */
    Result getTopSearches();
}
