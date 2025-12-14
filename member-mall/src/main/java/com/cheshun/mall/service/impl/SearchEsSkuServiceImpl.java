package com.cheshun.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cheshun.common.enumeration.CommontBooleanStatusType;
import com.cheshun.mall.service.SearchEsSkuService;
import com.cheshun.mall.vo.dto.FacetedSearchVO;
import com.github.pagehelper.PageInfo;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.mall.domain.entity.EsSkuInfo;
import com.cheshun.mall.vo.dto.EsSkuInfoPO;
import com.cheshun.mall.vo.dto.FacetedSearchPO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
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

import static com.cheshun.common.result.Result.ERR_CODE;
import static com.cheshun.common.result.Result.SUCC_CODE;
import static com.cheshun.common.tools.utils.DateTimeUtil.USUAL_DATE_TIME_FORMAT;

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
                // 新增：根据 status 过滤，值=1
                boolQuery.filter(QueryBuilders.termQuery("status", 1));

                // 条件1：筛选门店
                if (!ObjectUtils.isEmpty(facetedSearchVO.getSubbh())) {
                    boolQuery.must(QueryBuilders.wildcardQuery("subbhStr", "*" + facetedSearchVO.getSubbh() + "*").boost(1.0f));
                }

                // 条件2：商品名模糊匹配（支持中文分词）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getKeyWords())) {
                    String keyword = facetedSearchVO.getKeyWords();

                    // 创建一个专门用于商品名查询的bool查询
                    BoolQueryBuilder goodsNameQuery = QueryBuilders.boolQuery();

                    // 使用match_phrase查询（精确短语匹配）
                    goodsNameQuery.should(QueryBuilders.matchPhraseQuery("goodsName", keyword).boost(2.0f));

                    // 使用match查询，operator为AND（所有词都必须出现）
                    goodsNameQuery.should(QueryBuilders.matchQuery("goodsName", keyword)
                            .operator(Operator.AND).boost(2.0f));

                    // 设置minimumShouldMatch为1（满足上面任意一个条件即可）
                    goodsNameQuery.minimumShouldMatch(1);

                    // 将商品名查询添加到主bool查询中（作为must条件）
                    boolQuery.must(goodsNameQuery);

                    // 保留原来的商品代码查询
                    boolQuery.should(QueryBuilders.matchQuery("goodsCode", keyword).boost(2.0f));
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
                    if (!ObjectUtils.isEmpty(dto.getSymptoms())) {
                        if(!ObjectUtils.isEmpty(symptomStrBuff.toString())) {
                            symptomStrBuff.append(",").append(dto.getSymptoms());
                        } else {
                            symptomStrBuff.append(dto.getSymptoms());
                        }
                    }
                    if (!ObjectUtils.isEmpty(dto.getOriginManuf())) {
                        if (!ObjectUtils.isEmpty(originManufStrBuff.toString())) {
                            originManufStrBuff.append(",").append(dto.getOriginManuf());
                        } else {
                            originManufStrBuff.append(dto.getOriginManuf());
                        }
                    }
                    facetedSkuInfoPOList.add(po);
                });
                Set<String> uniqueSymptomLableSet = 0 == symptomStrBuff.length() ? Collections.emptySet() : new HashSet<>(Arrays.asList(symptomStrBuff.toString().split(",")));
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

                // 过滤：发货类型（0:默认 1:O2O 2:B2C）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO()) &&
                        !ObjectUtils.isEmpty(facetedSearchVO.getFilterKeyVO().getDeliveryType())) {
                    int deliveryType = facetedSearchVO.getFilterKeyVO().getDeliveryType().intValue();
                    switch (deliveryType) {
                        case 1: // O2O
                            list = list.stream()
                                    .filter(po -> CommontBooleanStatusType.YES.getCode().equals(po.getIsO2o()))
                                    .collect(Collectors.toList());
                            break;
                        case 2: // B2C
                            list = list.stream()
                                    .filter(po -> CommontBooleanStatusType.YES.getCode().equals(po.getIsB2c()))
                                    .collect(Collectors.toList());
                            break;
                        default: // 默认按相关度排序
                            // ES默认按相关度_score降序，不需要额外设置
                            break;
                    }
                }

                // 条件：价格排序（0:默认 2:价格降序 3:价格升序）
                if (!ObjectUtils.isEmpty(facetedSearchVO.getSortType())) {
                    int sortType = facetedSearchVO.getSortType().intValue();
                    switch (sortType) {
                        case 2: // 价格降序
                            list = list.stream()
                                    .sorted(Comparator.comparing(EsSkuInfoPO::getRetailPrice, Comparator.reverseOrder()))
                                    .collect(Collectors.toList());
                            //queryBuilder.withSort(Sort.by(Sort.Direction.DESC, "retailPrice"));
                            break;
                        case 3: // 价格升序
                            list = list.stream()
                                    .sorted(Comparator.comparing(EsSkuInfoPO::getRetailPrice, Comparator.naturalOrder()))
                                    .collect(Collectors.toList());
                            //queryBuilder.withSort(Sort.by(Sort.Direction.ASC, "retailPrice"));
                            break;
                        default: // 默认按相关度排序
                            // ES默认按相关度_score降序，不需要额外设置
                            break;
                    }
                }

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
        po.setBorderImage(item.getBorderImage());
        po.setStatus(item.getStatus());
        String createTimeStr = null == item.getCreateTime() ? "" : USUAL_DATE_TIME_FORMAT.format(item.getCreateTime());
        po.setCreateTimeStr(createTimeStr);
        String updateTimeStr = null == item.getUpdateTime() ? "" : USUAL_DATE_TIME_FORMAT.format(item.getUpdateTime());
        po.setUpdateTimeStr(updateTimeStr);
        po.setIsDefault(item.getIsDefault());
        po.setIsO2o(item.getIsO2o());
        po.setIsB2c(item.getIsB2c());
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