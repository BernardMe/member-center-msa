package com.zzjdyf.mall.service;

import com.zzjdyf.common.result.Result;

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
