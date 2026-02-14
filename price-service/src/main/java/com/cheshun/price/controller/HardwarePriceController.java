package com.cheshun.price.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.common.annotation.PageInfoParam;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.price.domain.entity.HardwarePrice;
import com.cheshun.price.domain.entity.PriceHistory;
import com.cheshun.price.mapper.HardwarePriceMapper;
import com.cheshun.price.mapper.PriceHistoryMapper;
import com.cheshun.price.service.AdminHardwarePriceService;
import com.cheshun.price.service.HardwarePriceCrawlerService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 硬件价格控制器
 */
@Slf4j
@RestController
@RequestMapping("/hardware-price")
public class HardwarePriceController {

    @Autowired
    private HardwarePriceCrawlerService crawlerService;
    @Autowired
    private AdminHardwarePriceService adminHardwarePriceService;
    @Autowired
    private HardwarePriceMapper hardwarePriceMapper;

    @Autowired
    private PriceHistoryMapper priceHistoryMapper;

    /**
     * 爬取指定主板的价格（所有平台）
     */
    @GetMapping("/crawl")
    public Result<Map<String, List<HardwarePrice>>> crawlPrices(@RequestParam String keyword) {
        try {
            log.info("收到爬取请求: keyword={}", keyword);
            Map<String, List<HardwarePrice>> prices = crawlerService.crawlMotherboardPrices(keyword);
            return Result.success(prices);
        } catch (Exception e) {
            log.error("爬取价格失败", e);
            return Result.error("爬取失败: " + e.getMessage());
        }
    }

    /**
     * 爬取指定平台的价格
     */
    @GetMapping("/crawl/{platform}")
    public Result<List<HardwarePrice>> crawlByPlatform(
            @PathVariable String platform,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "5") int pageSize) {
        try {
            log.info("收到平台爬取请求: platform={}, keyword={}", platform, keyword);
            List<HardwarePrice> prices = crawlerService.crawlByPlatform(platform, keyword, pageSize);
            return Result.success(prices);
        } catch (Exception e) {
            log.error("爬取平台价格失败", e);
            return Result.error("爬取失败: " + e.getMessage());
        }
    }

    /**
     * 价格比较分析
     */
    @GetMapping("/compare")
    public Result<Map<String, Object>> comparePrices(@RequestParam String keyword) {
        try {
            log.info("收到价格比较请求: keyword={}", keyword);
            Map<String, Object> result = crawlerService.comparePrices(keyword);
            return Result.success(result);
        } catch (Exception e) {
            log.error("价格比较失败", e);
            return Result.error("比较失败: " + e.getMessage());
        }
    }

    @ApiOperation("查询商品价格列表（分页）")
    @GetMapping("/list")
    public Result<Page<HardwarePrice>> listPrices(
            @PageInfoParam PageCondition pageCondition,
            @RequestParam(value = "platform", required = false) String platform,
            @RequestParam(value = "productType", required = false) String productType,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortOrder", defaultValue = "desc") String sortOrder
            ) {
        try {
            Result result = adminHardwarePriceService.adminQueryByCondition(pageCondition, platform, productType, brand, minPrice, maxPrice, sortField, sortOrder);
            return result;
        } catch (Exception e) {
            log.error("查询价格列表失败", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询价格历史记录
     */
    @GetMapping("/history/{priceId}")
    public Result<List<PriceHistory>> getPriceHistory(@PathVariable Long priceId) {
        try {
            LambdaQueryWrapper<PriceHistory> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PriceHistory::getPriceId, priceId)
                    .orderByDesc(PriceHistory::getRecordTime);

            List<PriceHistory> history = priceHistoryMapper.selectList(queryWrapper);
            return Result.success(history);
        } catch (Exception e) {
            log.error("查询价格历史失败", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询最低价商品
     */
    @GetMapping("/lowest")
    public Result<HardwarePrice> getLowestPrice(@RequestParam String productModel) {
        try {
            HardwarePrice lowestPrice = hardwarePriceMapper.findLowestPrice(productModel);
            return Result.success(lowestPrice);
        } catch (Exception e) {
            log.error("查询最低价失败", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 手动触发定时爬虫
     */
    @PostMapping("/scheduled-crawl")
    public Result<String> triggerScheduledCrawl() {
        try {
            log.info("手动触发定时爬虫任务");
            // 异步执行，避免超时
            new Thread(() -> {
                try {
                    crawlerService.scheduledCrawl();
                } catch (Exception e) {
                    log.error("定时爬虫执行失败", e);
                }
            }).start();

            return Result.success("定时爬虫任务已启动，正在后台执行...");
        } catch (Exception e) {
            log.error("触发定时爬虫失败", e);
            return Result.error("触发失败: " + e.getMessage());
        }
    }

    /**
     * 删除过期数据（超过30天的记录）
     */
    @DeleteMapping("/clean")
    public Result<Integer> cleanOldData(@RequestParam(defaultValue = "30") int days) {
        try {
            log.info("清理{}天前的数据", days);

            // 这里可以实现清理逻辑
            // DELETE FROM t_hardware_price WHERE crawl_time < DATE_SUB(NOW(), INTERVAL ? DAY)

            return Result.success(0);
        } catch (Exception e) {
            log.error("清理数据失败", e);
            return Result.error("清理失败: " + e.getMessage());
        }
    }
}