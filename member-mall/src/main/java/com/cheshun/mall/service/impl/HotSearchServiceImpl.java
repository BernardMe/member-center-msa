package com.cheshun.mall.service.impl;

import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.mall.domain.entity.EsHotSearch;
import com.cheshun.mall.service.HotSearchService;
import com.cheshun.mall.vo.dto.HotSearchPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算热搜词及热度Service
 * @author wangzhuo
 * @date 20251022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HotSearchServiceImpl implements HotSearchService {

    private Logger logger = LoggerFactory.getLogger(HotSearchServiceImpl.class);

    /*@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    // 用于复杂查询
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Result getTopSearches() {
        try {
            // 构建布尔查询
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

            Integer minSort = 0;
            Integer maxSort = 100;
            // 条件1：优先级范围（minSort ~ maxSort）
            if (minSort != null || maxSort != null) {
                boolQuery.filter(QueryBuilders.rangeQuery("clickCount")
                        .gte(minSort != null ? minSort : Integer.MIN_VALUE)  // 若minSort为null，则不限制最小值
                        .lte(maxSort != null ? maxSort : Integer.MAX_VALUE)); // 若maxSort为null，则不限制最大值
            }
            boolQuery.must(QueryBuilders.matchAllQuery());


            // 构建完整查询（默认按sort升序排序，方便运营端查看优先级）
            NativeSearchQuery queryBuilder = new NativeSearchQueryBuilder()
                    .withQuery(boolQuery)
                    .withSort(org.elasticsearch.search.sort.SortBuilders.fieldSort("clickCount").order(SortOrder.ASC))
                    .withPageable(PageRequest.of(0, 10))    // 构建分页( // 注意：，ES的page通常是从0开始)
                    .build();

            // 执行查询
            SearchHits<EsHotSearch> searchHits = elasticsearchOperations.search(queryBuilder, EsHotSearch.class);
            SearchPage<EsHotSearch> page = SearchHitSupport.searchPageFor(searchHits, queryBuilder.getPageable());

            logger.info("Total hits: " + searchHits.getTotalHits());


            List<EsHotSearch> pageContentList = new ArrayList<>();
            for (SearchHit<EsHotSearch> searchHit : searchHits) {
                EsHotSearch dto = searchHit.getContent();
                pageContentList.add(dto);
            }

            List<HotSearchPO> topSearchPOList = new ArrayList<>();
            pageContentList.stream().forEach(dto -> {
                HotSearchPO po = new HotSearchPO();
                copyDto2Po(dto, po);
                topSearchPOList.add(po);
            });

            /*if (searchHits.hasAggregations()) {
                // 获取原始的 Aggregations 对象
                Aggregations elasticsearchAggregations = (Aggregations) searchHits.getAggregations().aggregations();

                // 现在可以使用 get() 方法
                Terms hotSearches = elasticsearchAggregations.get("top_searches");

                topSearchPOList = hotSearches.getBuckets().stream()
                        .map(bucket -> {
                            String query = bucket.getKeyAsString();
                            long count = bucket.getDocCount();

                            // 获取子聚合
                            ParsedSum clickSum = bucket.getAggregations().get("click_sum");
                            double clickCount = clickSum != null ? clickSum.getValue() : 0.0;

                            double score = count + clickCount;
                            return HotSearchPO.builder().words(query)., score);
                        })
                        .collect(Collectors.toList());
            }*/

            return ResultUtil.result(topSearchPOList);

        } catch (Exception e) {
            log.error("定时计算热搜词及热度时发生异常 ",  e);
            throw new RuntimeException("定时计算热搜词及热度时发生异常", e);
        }
    }

    /**
     * DTO复制到PO
     * @param item
     * @param po
     */
    private void copyDto2Po(EsHotSearch item, HotSearchPO po) {
        //po.setConfigId(configDTO.getConfigId());
        po.setId(item.getId());
        po.setWords(item.getWords());
        po.setGoodsName(item.getGoodsName());
        po.setClickCount(item.getClickCount());
        po.setScore(item.getClickCount());
    }

}
