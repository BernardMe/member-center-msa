package com.cheshun.mall.vo.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.Set;

/**
 * 首页多维搜索结果PO
 */
@Data
public class FacetedSearchPO {

    /**
     * 商品货号,不分词
     */
    private PageInfo<EsSkuInfoPO> pageInfo;
    /**
     * 症状标签
     */
    private Set<String> symptomLabels;
    /**
     * 筛选条件(品牌)
     */
    private Set<String> originManufs;
    /**
     * reqId
     */
    private String reqId;
}
