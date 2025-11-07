package com.zzjdyf.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzjdyf.common.api.web.http.common.ListResult;
import com.zzjdyf.common.component.page.PageCondition;
import com.zzjdyf.common.tools.utils.ResultUtil;
import com.zzjdyf.mall.domain.entity.EsHotSearch;
import com.zzjdyf.mall.service.SearchPreService;
import com.zzjdyf.mall.vo.dto.*;
import com.zzjdyf.common.properties.WeChatProperties;
import com.zzjdyf.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.zzjdyf.common.constant.CommonServiceConstant.SU;
import static com.zzjdyf.common.result.Result.ERR_CODE;

@Service
@Slf4j
public class SearchPreServiceImpl implements SearchPreService {

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;


    public static final String WECHAT_USER_INFO = "wechat_user_info:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //feign调用

    /*@Autowired
    private UserLoginInfoMapper userLoginInfoMapper*/;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;


    @Autowired
    @Qualifier("gainGroupId")
    private ThreadPoolTaskExecutor gainGroupId;

    public Result<List<HotSearchPO>> portalGetHotwordList(HotwordListParam param) {
        try {

            //String hotSearchesJson = String.valueOf(redisTemplate.opsForValue().get("top_searches")); // 从 Redis 获取热搜词及热度

            Object topSearchListObj = redisTemplate.opsForValue().get("top_searches");
            List<HotSearchPO> topSearchPOList = JSONObject.parseObject(String.valueOf(topSearchListObj), new TypeReference<List<HotSearchPO>>(){});

            /*List<HotSearchPO> hotSearchPOList = Arrays.stream(hotSearchesJson.split(",")) // 按逗号分割
                    .map(entry -> {
                        String[] parts = entry.split(":"); // 按冒号分割
                        return HotSearchPO.builder().words(parts[0]).goodsName().score(Double.parseDouble(parts[1])); // 返回热搜词及热度
                    })
                    .collect(Collectors.toList());*/

            return ResultUtil.result(topSearchPOList);
        } catch (Exception e) {
            log.error("会员端-首页获取热搜词列表时发生异常，param={}", param.toString(), e);
            e.printStackTrace();
            return ResultUtil.result(ERR_CODE, "会员端-首页获取热搜词列表时发生异常");
        }
    }

    @Override
    public Result portalGetSuggestionList(PageCondition pageCondition, String keyword, String memberCardNo) {

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
            /*if (minSort != null || maxSort != null) {
                boolQuery.filter(QueryBuilders.rangeQuery("clickCount")
                        .gte(minSort != null ? minSort : Integer.MIN_VALUE)  // 若minSort为null，则不限制最小值
                        .lte(maxSort != null ? maxSort : Integer.MAX_VALUE)); // 若maxSort为null，则不限制最大值
            }*/

            // 条件2：商品名模糊匹配（支持中文分词）
            if (keyword != null && !keyword.isEmpty()) {
                // 1. 保留原有分词匹配（提高精准度）
                boolQuery.should(QueryBuilders.matchQuery("goodsName", keyword).boost(2.0f))
                        // 2. 新增通配符模糊匹配（支持部分字符匹配，如"99"匹配"999"）
                        .should(QueryBuilders.wildcardQuery("goodsName", "*" + keyword + "*").boost(1.0f));
            }

            // 条件3：适用症状（包含所有指定症状）
            /*if (keyword != null && !keyword.isEmpty()) {
                boolQuery.must(QueryBuilders.termsQuery("symptoms", keyword));
            }*/

            //当条件都为空时
            /*if (minSort == null && maxSort == null && (goodsName == null || goodsName.isEmpty()) && (symptoms == null || symptoms.isEmpty())) {
                boolQuery.must(QueryBuilders.matchAllQuery());
            }*/

            // 构建分页( // 注意：前端传过来的page通常是从1开始，ES需要转换为从0开始)
            Pageable pageable = PageRequest.of(pageCondition.getPageNum() - 1, pageCondition.getPageSize());

            // 构建完整查询（默认按sort升序排序，方便运营端查看优先级）
            NativeSearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(boolQuery)
                    .withSort(Sort.by(Sort.Direction.ASC, "clickCount"))
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

            List<HotSearchPO> suggestionHotSearchPOList = new ArrayList<>();
            pageContentList.stream().forEach(dto -> {
                HotSearchPO po = new HotSearchPO();
                copyDto2Po(dto, po);
                suggestionHotSearchPOList.add(po);
            });


            ListResult<HotSearchPO> portalSuggestionResult = null;
            if (pageCondition.getIsPage()) {
                PageInfo<HotSearchPO> pageInfo = new PageInfo<>(suggestionHotSearchPOList);
                pageInfo.setTotal(page.getTotalElements());
                pageInfo.setPages(page.getTotalPages());
                pageInfo.setPageNum(page.getNumber());
                pageInfo.setPageSize(page.getSize());
                portalSuggestionResult = ResultUtil.listResult(pageInfo);
            } else {
                portalSuggestionResult = ResultUtil.listResult(SU, suggestionHotSearchPOList);
            }

            //结果包装
            return portalSuggestionResult;
        } catch (Exception e) {
            log.error("会员端-首页根据输入项返回热搜词建议时发生异常， isPage={} pageNum={} pageSize={}",
                    pageCondition.getIsPage(), pageCondition.getPageNum(), pageCondition.getPageSize(), e);
            throw new RuntimeException("会员端-首页根据输入项返回热搜词建议时发生异常", e);
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
        po.setGoodsName(item.getGoodsName());
        po.setSymptoms(item.getSymptoms());
        po.setWords(item.getWords());
        String createTimeStr = null == item.getTimestamp() ? "" : item.getTimestamp();
        po.setCreateTimeStr(createTimeStr);
        po.setClickCount(item.getClickCount());
        po.setScore(item.getClickCount());
    }
}
