package com.cheshun.price.service;

import com.cheshun.price.domain.entity.HardwarePrice;

import java.util.List;
import java.util.Map;

/**
 * 硬件价格爬虫服务
 */
public interface HardwarePriceCrawlerService {

    /**
     * 爬取指定主板的价格（所有平台）
     * @param keyword 搜索关键词（如：华硕 B760）
     * @return 各平台价格列表
     */
    Map<String, List<HardwarePrice>> crawlMotherboardPrices(String keyword);

    /**
     * 爬取指定平台的主板价格
     * @param platform 平台名称：TAOBAO, JD, PDD
     * @param keyword 搜索关键词
     * @param pageSize 每页数量
     * @return 价格列表
     */
    List<HardwarePrice> crawlByPlatform(String platform, String keyword, int pageSize);

    /**
     * 比较各平台价格，找出最优惠的
     * @param keyword 搜索关键词
     * @return 价格比较结果
     */
    Map<String, Object> comparePrices(String keyword);

    /**
     * 保存爬取的价格数据到数据库
     * @param prices 价格列表
     */
    void savePrices(List<HardwarePrice> prices);

    /**
     * 定时任务：批量爬取热门主板价格
     */
    void scheduledCrawl();
}
