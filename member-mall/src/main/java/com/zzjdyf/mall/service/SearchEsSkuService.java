package com.zzjdyf.mall.service;

import com.zzjdyf.common.result.Result;
import com.zzjdyf.mall.vo.dto.FacetedSearchVO;

/**
 * 会员端-首页搜索框Service
 * @author wangzhuo
 * @date 20251028
 */
public interface SearchEsSkuService {

    /**
     * 会员端-根据输入项多维搜索商品列表
     * @param facetedSearchVO
     * @return
     */
    Result portalPostFacetedSearch(FacetedSearchVO facetedSearchVO);
}
