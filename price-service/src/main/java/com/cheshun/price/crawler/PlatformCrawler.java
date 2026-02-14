package com.cheshun.price.crawler;

import com.cheshun.price.domain.entity.HardwarePrice;

import java.util.List;

/**
 * 电商平台爬虫接口
 */
public interface PlatformCrawler {

    /**
     * 获取平台名称
     */
    String getPlatformName();

    /**
     * 搜索商品
     * @param keyword 搜索关键词（如：华硕 ROG STRIX B760-A GAMING WIFI D5）
     * @param page 页码
     * @param pageSize 每页数量
     * @return 商品列表
     */
    List<HardwarePrice> searchProducts(String keyword, int page, int pageSize);

    /**
     * 获取商品详情
     * @param productUrl 商品链接
     * @return 商品信息
     */
    HardwarePrice getProductDetail(String productUrl);

    /**
     * 是否支持该平台
     */
    boolean isSupported();
}