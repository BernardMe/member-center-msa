package com.zzjdyf.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.zzjdyf.common.result.Result;
import com.zzjdyf.common.tools.utils.ResultUtil;
import com.zzjdyf.mall.domain.entity.EsSkuInfo;
import com.zzjdyf.mall.service.SearchEsSkuService;
import com.zzjdyf.mall.vo.dto.EsSkuInfoPO;
import com.zzjdyf.mall.vo.dto.FacetedSearchPO;
import com.zzjdyf.mall.vo.dto.FacetedSearchVO;
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
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.zzjdyf.common.result.Result.ERR_CODE;
import static com.zzjdyf.common.result.Result.SUCC_CODE;
import static com.zzjdyf.common.tools.utils.DateTimeUtil.USUAL_DATE_TIME_FORMAT_VALUE;

/**
 * 会员端-首页搜索框Service实现类
 * @author wangzhuo
 * @date 20251028
 */
@Service
@Slf4j
public class SearchEsSkuServiceImpl implements SearchEsSkuService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //feign调用

    /*@Autowired
    private UserLoginInfoMapper userLoginInfoMapper*/;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    @Qualifier("gainGroupId")
    private ThreadPoolTaskExecutor gainGroupId;


    /*@Override
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
            *//*if (minSort != null || maxSort != null) {
                boolQuery.filter(QueryBuilders.rangeQuery("clickCount")
                        .gte(minSort != null ? minSort : Integer.MIN_VALUE)  // 若minSort为null，则不限制最小值
                        .lte(maxSort != null ? maxSort : Integer.MAX_VALUE)); // 若maxSort为null，则不限制最大值
            }*//*

            // 条件2：商品名模糊匹配（支持中文分词）
            if (keyword != null && !keyword.isEmpty()) {
                // 1. 保留原有分词匹配（提高精准度）
                boolQuery.should(QueryBuilders.matchQuery("goodsName", keyword).boost(2.0f))
                        // 2. 新增通配符模糊匹配（支持部分字符匹配，如"99"匹配"999"）
                        .should(QueryBuilders.wildcardQuery("goodsName", "*" + keyword + "*").boost(1.0f));
            }

            // 条件3：适用症状（包含所有指定症状）
            *//*if (keyword != null && !keyword.isEmpty()) {
                boolQuery.must(QueryBuilders.termsQuery("symptoms", keyword));
            }*//*

            //当条件都为空时
            *//*if (minSort == null && maxSort == null && (goodsName == null || goodsName.isEmpty()) && (symptoms == null || symptoms.isEmpty())) {
                boolQuery.must(QueryBuilders.matchAllQuery());
            }*//*

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
    }*/

    @Override
    public Result portalPostFacetedSearch(FacetedSearchVO facetedSearchVO) {
        try {
            // 校验
            if (ObjectUtils.isEmpty(facetedSearchVO.getKeyWords())) {
                log.error("搜索关键字不能为空 ");
                return ResultUtil.result(ERR_CODE,"搜索关键字不能为空！");
            }
            // 分页查询
            if (ObjectUtils.isEmpty(facetedSearchVO.getPageSize()) || ObjectUtils.isEmpty(facetedSearchVO.getPageNum())) {
                facetedSearchVO.setPageSize(10);
                facetedSearchVO.setPageNum(1);
            }

            if (!ObjectUtils.isEmpty(facetedSearchVO.getReqId())) {
                Object redisSearchObj = redisTemplate.opsForValue().get(String.format("init-result:%s", facetedSearchVO.getReqId()));
                if (ObjectUtils.isEmpty(redisSearchObj)) {
                    facetedSearchVO.setReqId(null);
                }
            }

            if (ObjectUtils.isEmpty(facetedSearchVO.getReqId())) {

                // 构建分页( // 注意：前端传过来的page通常是从1开始，ES需要转换为从0开始)
                Pageable pageable = PageRequest.of(facetedSearchVO.getPageNum() - 1, facetedSearchVO.getPageSize());

                // 构建布尔查询
                BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

                // 构建NativeSearchQueryBuilder
                NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                        .withQuery(boolQuery)
                        .withPageable(pageable);

                // 条件1：优先级范围（minSort ~ maxSort）
                /*if (minSort != null || maxSort != null) {
                    boolQuery.filter(QueryBuilders.rangeQuery("clickCount")
                            .gte(minSort != null ? minSort : Integer.MIN_VALUE)  // 若minSort为null，则不限制最小值
                            .lte(maxSort != null ? maxSort : Integer.MAX_VALUE)); // 若maxSort为null，则不限制最大值
                }*/

                // 条件2：商品名模糊匹配（支持中文分词）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getKeyWords())) {
                    // 1. 保留原有分词匹配（提高精准度）
                    boolQuery.should(QueryBuilders.matchQuery("goodsName", facetedSearchVO.getKeyWords()).boost(2.0f))
                            // 2. 新增通配符模糊匹配（支持部分字符匹配，如"99"匹配"999"）
                            .should(QueryBuilders.wildcardQuery("goodsName", "*" + facetedSearchVO.getKeyWords() + "*").boost(1.0f));
                }

                // 条件3：发货类型（0:默认 1:O2O 2:B2C）
                /*if (keyword != null && !keyword.isEmpty()) {
                    boolQuery.must(QueryBuilders.termsQuery("symptoms", keyword));
                }*/

                // 条件4：价格排序（0:默认 2:价格降序 3:价格升序）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getSortType())) {
                    int sortType = facetedSearchVO.getSortType().intValue();
                    switch (sortType) {
                        case 2: // 价格降序
                            queryBuilder.withSort(Sort.by(Sort.Direction.DESC, "retailPrice"));
                            break;
                        case 3: // 价格升序
                            queryBuilder.withSort(Sort.by(Sort.Direction.ASC, "retailPrice"));
                            break;
                        default: // 默认按相关度排序
                            // ES默认按相关度_score降序，不需要额外设置
                            break;
                    }
                }

                // 条件5：筛选条件（品牌/是否处方药）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO()) &&
                        !ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO().getOriginManufStr())) {
                    List<String> originManuList = Arrays.asList(facetedSearchVO.getFilterKeyVO().getOriginManufStr().split(","));
                    boolQuery.must(QueryBuilders.termsQuery("originManuf", originManuList));
                }

                // 条件6：适用症状（包含所有指定症状）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO()) &&
                        !ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO().getSymptomsLabel())) {
                    boolQuery.must(QueryBuilders.wildcardQuery("symptoms", "*" + facetedSearchVO.getFilterKeyVO().getSymptomsLabel() + "*").boost(1.0f));
                }

                //当条件都为空时
                /*if (minSort == null && maxSort == null && (goodsName == null || goodsName.isEmpty()) && (symptoms == null || symptoms.isEmpty())) {
                    boolQuery.must(QueryBuilders.matchAllQuery());
                }*/


                // 构建完整查询（默认按sort升序排序，方便运营端查看优先级）
                NativeSearchQuery query = queryBuilder.build();

                // 执行查询
                SearchHits<EsSkuInfo> searchHits = elasticsearchTemplate.search(query, EsSkuInfo.class);
                SearchPage<EsSkuInfo> page = SearchHitSupport.searchPageFor(searchHits, query.getPageable());

                List<EsSkuInfo> pageContentList = new ArrayList<>();
                for (SearchHit<EsSkuInfo> searchHit : searchHits) {
                    EsSkuInfo dto = searchHit.getContent();
                    pageContentList.add(dto);
                }

                StringBuffer symptomStrBuff = new StringBuffer();
                StringBuffer originManufStrBuff = new StringBuffer();

                List<EsSkuInfoPO> facetedSkuInfoPOList = new ArrayList<>();
                pageContentList.stream().forEach(dto -> {
                    EsSkuInfoPO po = new EsSkuInfoPO();
                    copyDto2Po(dto, po);
                    if (ObjectUtils.isEmpty(symptomStrBuff.toString())) {
                        symptomStrBuff.append(dto.getSymptoms());
                    } else {
                        symptomStrBuff.append(",").append(dto.getSymptoms());
                    }
                    if (ObjectUtils.isEmpty(originManufStrBuff.toString())) {
                        originManufStrBuff.append(dto.getOriginManuf());
                    } else {
                        originManufStrBuff.append(",").append(dto.getOriginManuf());
                    }
                    facetedSkuInfoPOList.add(po);
                });
                Set<String> uniqueSymptomLableSet = new HashSet<>(Arrays.asList(symptomStrBuff.toString().split(",")));
                Set<String> uniqueOriginManufSet = new HashSet<>(Arrays.asList(originManufStrBuff.toString().split(",")));

                FacetedSearchPO facetedSearchPO = new FacetedSearchPO();

                PageInfo<EsSkuInfoPO> pageInfo = new PageInfo<>(facetedSkuInfoPOList);
                pageInfo.setTotal(page.getTotalElements());
                pageInfo.setPages(page.getTotalPages());
                pageInfo.setPageNum(page.getNumber());
                pageInfo.setPageSize(page.getSize());
                facetedSearchPO.setPageInfo(pageInfo);
                facetedSearchPO.setSymptomLabels(uniqueSymptomLableSet);
                facetedSearchPO.setOriginManufs(uniqueOriginManufSet);

                //
                // 1.开头两位，标识业务代码或机器代码（可变参数）
                String machineId = "S";
                // 2.中间八位整数，标识日期
                String dayTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                // 3.生成uuid的hashCode值
                int hashCode = UUID.randomUUID().toString().hashCode();
                // 4.可能为负数
                if(hashCode < 0){
                    hashCode = -hashCode;
                }
                // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
                String value = machineId + dayTime + String.format("%010d", hashCode);
                log.info("搜索reqId已生成！ reqID ={} ", value);
                facetedSearchPO.setReqId(value);
                redisTemplate.opsForValue().set(String.format("init-result:%s", value), JSONObject.toJSONString(facetedSearchPO), 30, TimeUnit.MINUTES);

                //结果包装
                return ResultUtil.result(SUCC_CODE, "操作成功", facetedSearchPO);


            } else {
                Object redisFacetedSearchObj = redisTemplate.opsForValue().get(String.format("init-result:%s", facetedSearchVO.getReqId()));
                FacetedSearchPO facetedSearchPO = JSONObject.parseObject(String.valueOf(redisFacetedSearchObj), new TypeReference<FacetedSearchPO>(){});
                PageInfo<EsSkuInfoPO> pageInfo = facetedSearchPO.getPageInfo();
                List<EsSkuInfoPO> list = facetedSearchPO.getPageInfo().getList();

                List<EsSkuInfoPO> newList = null;

                //过滤 厂牌
                if (!ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO()) &&
                        !ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO().getOriginManufStr())) {
                    String[] originManu = facetedSearchVO.getFilterKeyVO().getOriginManufStr().split(",");
                    list = list.stream()
                            .filter(po -> facetedSearchVO.getFilterKeyVO().getOriginManufStr().contains(po.getOriginManuf())) // 过滤条件：
                        .collect(Collectors.toList());
                }

                //过滤症状
                if (!ObjectUtils.isEmpty(list) && !ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO()) &&
                        !ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO().getSymptomsLabel())) {
                    String[] SymptomsLabel = facetedSearchVO.getFilterKeyVO().getSymptomsLabel().split(",");
                    list = list.stream()
                            .filter(po -> facetedSearchVO.getFilterKeyVO().getSymptomsLabel().contains(po.getSymptoms())) // 过滤条件：
                            .collect(Collectors.toList());
                }

                //结果包装
                pageInfo.setList(list);
                facetedSearchPO.setPageInfo(pageInfo);
                //facetedSearchPO.set
                facetedSearchPO.setReqId(facetedSearchVO.getReqId());
                return ResultUtil.result(SUCC_CODE, "操作成功", facetedSearchPO);
            }
        } catch (Exception e) {
            log.error("会员端-根据输入项多维搜索商品列表时发生异常， keywords={} pageNum={} pageSize={}",
                    facetedSearchVO.getKeyWords(), facetedSearchVO.getPageNum(), facetedSearchVO.getPageSize(), e);
            throw new RuntimeException("会员端-根据输入项多维搜索商品列表时发生异常", e);
        }
    }

    /**
     * DTO复制到PO
     * @param item
     * @param po
     */
    private void copyDto2Po(EsSkuInfo item, EsSkuInfoPO po) {
        po.setSkuId(item.getSkuId());
        po.setGoodsId(item.getGoodsId());
        po.setGoodsCode(item.getGoodsCode());
        po.setGoodsName(item.getGoodsName());
        po.setSubTitle(item.getSubTitle());
        po.setRetailPrice(item.getRetailPrice());
        po.setMainImage(item.getMainImage());
        po.setStatus(item.getStatus());
        String createTimeStr = null == item.getCreateTime() ? "" : DateTimeFormatter.ofPattern(USUAL_DATE_TIME_FORMAT_VALUE).format(item.getCreateTime());
        po.setCreateTimeStr(createTimeStr);
        String updateTimeStr = null == item.getUpdateTime() ? "" : DateTimeFormatter.ofPattern(USUAL_DATE_TIME_FORMAT_VALUE).format(item.getUpdateTime());
        po.setUpdateTimeStr(updateTimeStr);
        po.setIsDefault(item.getIsDefault());
        po.setSpuId(item.getSpuId());
        po.setCategoryId(item.getCategoryId());
        po.setCategoryName(item.getCategoryName());
        po.setOriginManuf(item.getOriginManuf());
        po.setSpec(item.getSpec());
        po.setNum(item.getNum());
        /*po.setClickCount(item.getClickCount());
        po.setScore(item.getClickCount());*/
        po.setSymptoms(item.getSymptoms());
    }

}
