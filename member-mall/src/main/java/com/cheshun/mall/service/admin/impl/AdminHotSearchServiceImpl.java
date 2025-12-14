package com.cheshun.mall.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cheshun.common.api.web.http.common.ListResult;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.mall.domain.entity.EsHotSearch;
import com.cheshun.mall.domain.dao.HotSearchRepository;
import com.cheshun.mall.service.admin.AdminHotSearchService;
import com.cheshun.mall.vo.dto.ESHotSearchVO;
import com.cheshun.mall.vo.dto.HotSearchPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cheshun.common.constant.CommonServiceConstant.SU;
import static com.cheshun.common.result.Result.ERR_CODE;

/**
 * 计算热搜词及热度Service
 * @author wangzhuo
 * @date 20251022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminHotSearchServiceImpl implements AdminHotSearchService {

    /*@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/
    @Autowired
    private HotSearchRepository hotSearchRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    // 用于复杂查询
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Result add(ESHotSearchVO esHotSearchVO) {
        try {

            EsHotSearch esHotSearch = new EsHotSearch();
            copyVo2Dto(esHotSearchVO, esHotSearch);
            hotSearchRepository.save(esHotSearch);
            return SU;
        } catch (Exception e) {
            log.error("后管-热搜词添加时发生异常， esHotSearchVO={} ", esHotSearchVO.toString(), e);
            throw new RuntimeException("后管-热搜词添加时发生异常", e);
        }
    }

    @Override
    public Result batchAdd(List<ESHotSearchVO> hotSearchVOList) {
        return null;
    }

    @Override
    public Result update(ESHotSearchVO esHotSearchVO) {
        try {
            if (null == esHotSearchVO.getId() || "".equals(esHotSearchVO.getId())) {
                log.error("热搜词id不能为空 ");
                return ResultUtil.result(ERR_CODE,"热搜词id不能为空！");
            }
            EsHotSearch esHotSearch = hotSearchRepository.findById(esHotSearchVO.getId())
                    .orElse(null);
            if (null == esHotSearch) {
                log.error("后管-根据id更新单个热搜词， id={} 热搜词不存在", esHotSearchVO);
                return ResultUtil.result(ERR_CODE,"热搜词不存在！");
            }
            copyVo2Dto(esHotSearchVO, esHotSearch);
            // save 方法兼具新增和更新
            hotSearchRepository.save(esHotSearch);
            return SU;
        } catch (Exception e) {
            log.error("后管-根据id更新单个热搜词时发生异常， id={} ", esHotSearchVO, e);
            throw new RuntimeException("后管-根据id更新单个热搜词时发生异常", e);
        }
    }

    @Override
    public Result adminQueryByCondition(PageCondition pageCondition, Integer minSort, Integer maxSort, String goodsName, List<String> symptoms) {
        try {
            // 分页查询
            if (pageCondition == null || !pageCondition.getIsPage()) {
                pageCondition = new PageCondition();
            }
            //强制分页
            PageHelper.startPage(pageCondition.getPageNum(), pageCondition.getPageSize());

            // 构建布尔查询
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

            // 条件1：优先级范围（minSort ~ maxSort）
            if (minSort != null || maxSort != null) {
                boolQuery.filter(QueryBuilders.rangeQuery("clickCount")
                        .gte(minSort != null ? minSort : Integer.MIN_VALUE)  // 若minSort为null，则不限制最小值
                        .lte(maxSort != null ? maxSort : Integer.MAX_VALUE)); // 若maxSort为null，则不限制最大值
            }

            // 条件2：商品名模糊匹配（支持中文分词）
            if (goodsName != null && !goodsName.isEmpty()) {
                // 1. 保留原有分词匹配（提高精准度）
                boolQuery.should(QueryBuilders.matchQuery("goodsName", goodsName).boost(2.0f))
                        // 2. 新增通配符模糊匹配（支持部分字符匹配，如"99"匹配"999"）
                        .should(QueryBuilders.wildcardQuery("goodsName", "*" + goodsName + "*").boost(1.0f));
            }

            // 条件3：适用症状（包含所有指定症状）
            if (symptoms != null && !symptoms.isEmpty()) {
                boolQuery.must(QueryBuilders.termsQuery("symptoms", symptoms));
            }

            //当条件都为空时
            if (minSort == null && maxSort == null && (goodsName == null || goodsName.isEmpty()) && (symptoms == null || symptoms.isEmpty())) {
                boolQuery.must(QueryBuilders.matchAllQuery());
            }

            // 构建分页( // 注意：前端传过来的page通常是从1开始，ES需要转换为从0开始)
            Pageable pageable = PageRequest.of(pageCondition.getPageNum() - 1, pageCondition.getPageSize());

            // 构建完整查询（默认按sort升序排序，方便运营端查看优先级）
            NativeSearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(boolQuery)
                    .withSort(org.elasticsearch.search.sort.SortBuilders.fieldSort("clickCount").order(SortOrder.ASC))
                    .withPageable(pageable)
                    .build();

            // 执行查询
            SearchHits<EsHotSearch> searchHits = elasticsearchTemplate.search(query, EsHotSearch.class);
            SearchPage<EsHotSearch> page = SearchHitSupport.searchPageFor(searchHits, query.getPageable());

            List<EsHotSearch> pageContentList = new ArrayList<>();
            for (SearchHit<EsHotSearch> searchHit : searchHits) {
                EsHotSearch dto = searchHit.getContent();
                pageContentList.add(dto);
            }

            //Page<ESHotSearch> queryPage = elasticsearchOperations.queryForPage(query, ESHotSearch.class);
            //List<ESHotSearch> pageContentList = queryPage.getContent();
            //queryPage.get

            List<HotSearchPO> adminHotSearchPOList = new ArrayList<>();
            pageContentList.stream().forEach(dto -> {
                HotSearchPO po = new HotSearchPO();
                copyDto2Po(dto, po);
                adminHotSearchPOList.add(po);
            });


            ListResult<HotSearchPO> adminHotSearchResult = null;
            if (pageCondition.getIsPage()) {
                PageInfo<HotSearchPO> pageInfo = new PageInfo<>(adminHotSearchPOList);
                pageInfo.setTotal(page.getTotalElements());
                pageInfo.setPages(page.getTotalPages());
                pageInfo.setPageNum(page.getNumber());
                pageInfo.setPageSize(page.getSize());
                adminHotSearchResult = ResultUtil.listResult(pageInfo);
            } else {
                adminHotSearchResult = ResultUtil.listResult(SU, adminHotSearchPOList);
            }

            //结果包装
            return adminHotSearchResult;
        } catch (Exception e) {
            log.error("后管-按条件查询热搜词（分页）列表时发生异常， isPage={} pageNum={} pageSize={}",
                    pageCondition.getIsPage(), pageCondition.getPageNum(), pageCondition.getPageSize(), e);
            throw new RuntimeException("后管-按条件查询热搜词（分页）列表时发生异常", e);
        }
    }

    @Override
    public Result delete(ESHotSearchVO esHotSearchVO) {
        try {
            if (null == esHotSearchVO.getId() || "".equals(esHotSearchVO.getId())) {
                log.error("热搜词id不能为空 ");
                return ResultUtil.result(ERR_CODE,"热搜词id不能为空！");
            }
            EsHotSearch esHotSearch = hotSearchRepository.findById(esHotSearchVO.getId())
                    .orElse(null);
            if (null == esHotSearch) {
                log.error("后管-根据id删除单个热搜词， id={} 热搜词不存在", esHotSearchVO);
                return ResultUtil.result(ERR_CODE,"热搜词不存在！");
            }

            hotSearchRepository.deleteById(esHotSearchVO.getId());
            return SU;
        } catch (Exception e) {
            log.error("后管-根据id删除单个热搜词时发生异常， id={} ", esHotSearchVO, e);
            throw new RuntimeException("后管-根据id删除单个热搜词时发生异常", e);
        }
    }

    @Override
    public void batchDelete(List<EsHotSearch> ESGoodsSearchDTOs) {



    }

    /**
     * DTO复制到PO
     * @param item
     * @param po
     */
    private void copyDto2Po(EsHotSearch item, HotSearchPO po) {
        //po.setConfigId(configDTO.getConfigId());
        po.setId(item.getId());
        po.setGoodsName(item.getGoodsName());
        po.setSymptoms(item.getSymptoms());
        po.setWords(item.getWords());
        String createTimeStr = null == item.getTimestamp() ? "" : item.getTimestamp();
        po.setCreateTimeStr(createTimeStr);
        po.setClickCount(item.getClickCount());
        po.setScore(item.getClickCount());
    }


    /**
     * DTO复制到PO
     * @param dto
     * @param vo
     */
    private void copyVo2Dto(ESHotSearchVO vo, EsHotSearch dto) {
        //po.setConfigId(configDTO.getConfigId());
        dto.setId(vo.getId());
        dto.setGoodsName(vo.getGoodsName());
        dto.setSymptoms(vo.getSymptoms());
        dto.setWords(vo.getWords());
        dto.setClickCount(vo.getClickCount());
    }
}
