package com.cheshun.price.service;

import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.price.domain.entity.HardwarePrice;
import com.cheshun.price.domain.vo.HardwarePriceVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 后管-硬件价格记录Service
 * @author wangzhuo
 * @date 20260205
 */
public interface AdminHardwarePriceService {

    /**
     * 后管-添加硬件价格记录
     * @param hardwarePriceVO
     * @return
     */
    Result add(HardwarePriceVO hardwarePriceVO);

    /**
     * 管理端：热搜词批量添加
     * @param hardwarePriceVOList
     * @return
     */
    Result batchAdd(List<HardwarePriceVO> hardwarePriceVOList);

    /**
     * 后管-查询硬件价格记录详情
     * @param id
     * @return
     */
    Result getHardwarePriceDetail(Long id);

    /**
     * 根据id返回硬件价格记录DTO
     * @param id
     * @return
     */
    HardwarePrice getHardwarePrice(Long id);

    /**
     * 后管-修改单个硬件价格记录
     * @param hardwarePriceVO
     * @return
     */
    Result update(HardwarePriceVO hardwarePriceVO);

    /**
     * 后管：按条件查询（分页）
     * @param pageCondition
     * @param platform
     * @param productType
     * @param brand
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @param sortField 排序字段（price/salesVolume/crawlTime）
     * @param sortOrder 排序方向（asc/desc）
     * @return
     */
    Result adminQueryByCondition(PageCondition pageCondition,
                                 String platform,
                                 String productType,
                                 String brand,
                                 BigDecimal minPrice, BigDecimal maxPrice, String sortField, String sortOrder);

    /**
     * 后管-删除单个硬件价格记录
     * @param hardwarePriceVO
     */
    Result delete(HardwarePriceVO hardwarePriceVO);

    /**
     * 管理端：商品批量删除
     * @param hardwarePriceList
     */
    void batchDelete(List<HardwarePrice> hardwarePriceList);

}
