package com.cheshun.price.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cheshun.price.crawler.PlatformCrawler;
import com.cheshun.price.domain.entity.HardwarePrice;
import com.cheshun.price.domain.entity.HardwarePriceExample;
import com.cheshun.price.domain.entity.PriceHistory;
import com.cheshun.price.mapper.HardwarePriceMapper;
import com.cheshun.price.mapper.PriceHistoryMapper;
import com.cheshun.price.service.HardwarePriceCrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 硬件价格爬虫服务实现
 */
@Slf4j
@Service
public class HardwarePriceCrawlerServiceImpl implements HardwarePriceCrawlerService {

    @Autowired
    private List<PlatformCrawler> crawlers;

    @Autowired
    private HardwarePriceMapper hardwarePriceMapper;

    @Autowired
    private PriceHistoryMapper priceHistoryMapper;

    @Override
    public Map<String, List<HardwarePrice>> crawlMotherboardPrices(String keyword) {
        log.info("=== 开始爬取主板价格 ===");
        log.info("搜索关键词: {}", keyword);

        Map<String, List<HardwarePrice>> result = new HashMap<>();

        for (PlatformCrawler crawler : crawlers) {
            if (crawler.isSupported()) {
                try {
                    log.info("正在爬取 {} 平台...", crawler.getPlatformName());
                    List<HardwarePrice> prices = crawler.searchProducts(keyword, 1, 5);
                    result.put(crawler.getPlatformName(), prices);

                    // 保存到数据库
                    savePrices(prices);

                    // 防止被封IP，休眠一下
                    Thread.sleep(2000);

                } catch (Exception e) {
                    log.error("爬取 {} 平台失败", crawler.getPlatformName(), e);
                }
            }
        }

        log.info("=== 爬取完成 ===");
        return result;
    }

    @Override
    public List<HardwarePrice> crawlByPlatform(String platform, String keyword, int pageSize) {
        log.info("爬取指定平台: platform={}, keyword={}", platform, keyword);

        PlatformCrawler crawler = crawlers.stream()
                .filter(c -> c.getPlatformName().equalsIgnoreCase(platform))
                .findFirst()
                .orElse(null);

        if (crawler == null || !crawler.isSupported()) {
            log.warn("平台 {} 不支持或未启用", platform);
            return Collections.emptyList();
        }

        List<HardwarePrice> prices = crawler.searchProducts(keyword, 1, pageSize);
        savePrices(prices);

        return prices;
    }

    @Override
    public Map<String, Object> comparePrices(String keyword) {
        log.info("=== 开始价格比较 ===");
        log.info("关键词: {}", keyword);

        // 1. 爬取各平台价格
        Map<String, List<HardwarePrice>> allPrices = crawlMotherboardPrices(keyword);

        // 2. 统计分析
        Map<String, Object> result = new HashMap<>();

        // 找出最低价
        HardwarePrice lowestPrice = allPrices.values().stream()
                .flatMap(List::stream)
                .min(Comparator.comparing(HardwarePrice::getPrice))
                .orElse(null);

        // 找出最高价
        HardwarePrice highestPrice = allPrices.values().stream()
                .flatMap(List::stream)
                .max(Comparator.comparing(HardwarePrice::getPrice))
                .orElse(null);

        // 计算平均价
        double avgPrice = allPrices.values().stream()
                .flatMap(List::stream)
                .mapToDouble(p -> p.getPrice().doubleValue())
                .average()
                .orElse(0.0);

        // 各平台平均价
        Map<String, Double> platformAvgPrices = new HashMap<>();
        for (Map.Entry<String, List<HardwarePrice>> entry : allPrices.entrySet()) {
            double platformAvg = entry.getValue().stream()
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .average()
                    .orElse(0.0);
            platformAvgPrices.put(entry.getKey(), platformAvg);
        }

        result.put("keyword", keyword);
        result.put("lowestPrice", lowestPrice);
        result.put("highestPrice", highestPrice);
        result.put("avgPrice", avgPrice);
        result.put("platformAvgPrices", platformAvgPrices);
        result.put("allPrices", allPrices);
        result.put("crawlTime", LocalDateTime.now());

        // 生成推荐
        String recommendation = generateRecommendation(lowestPrice, highestPrice, avgPrice);
        result.put("recommendation", recommendation);

        log.info("=== 价格比较完成 ===");
        log.info("最低价: {} - {} 元", lowestPrice.getPlatform(), lowestPrice.getPrice());
        log.info("最高价: {} - {} 元", highestPrice.getPlatform(), highestPrice.getPrice());
        log.info("平均价: {} 元", avgPrice);

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePrices(List<HardwarePrice> prices) {
        if (prices == null || prices.isEmpty()) {
            return;
        }

        for (HardwarePrice price : prices) {
            try {
                // 1. 查询是否已存在相同商品
                HardwarePriceExample example = new HardwarePriceExample();
                example.createCriteria().andPlatformEqualTo(price.getPlatform())
                        .andProductUrlEqualTo(price.getProductUrl());


                List<HardwarePrice> hardwarePriceList = hardwarePriceMapper.selectByExample(example);
                if (ObjectUtils.isEmpty(hardwarePriceList)) continue;

                HardwarePrice existing = hardwarePriceList.get(0);

                if (existing != null) {
                    // 2. 如果价格有变化，记录历史
                    if (!existing.getPrice().equals(price.getPrice())) {
                        recordPriceHistory(existing, price);
                    }

                    // 3. 更新价格
                    price.setId(existing.getId());
                    price.setUpdateTime(LocalDateTime.now());
                    hardwarePriceMapper.updateByPrimaryKey(price);

                    log.debug("更新商品价格: {} - {}", price.getProductName(), price.getPrice());
                } else {
                    // 4. 新增商品
                    hardwarePriceMapper.insert(price);
                    log.debug("新增商品: {} - {}", price.getProductName(), price.getPrice());
                }

            } catch (Exception e) {
                log.error("保存商品价格失败: {}", price.getProductName(), e);
            }
        }
    }

    /**
     * 定时爬取（每天凌晨2点执行）
     */
    @Override
    @Scheduled(cron = "0 0 2 * * ?")
    public void scheduledCrawl() {
        log.info("=== 定时爬虫任务开始 ===");

        // 热门主板列表
        String[] popularMotherboards = {
                "华硕 B760",
                "微星 B760",
                "技嘉 B760",
                "华硕 Z790",
                "微星 Z790"
        };

        for (String keyword : popularMotherboards) {
            try {
                log.info("爬取关键词: {}", keyword);
                crawlMotherboardPrices(keyword);

                // 防止被封，每个关键词之间间隔一段时间
                Thread.sleep(5000);

            } catch (Exception e) {
                log.error("定时爬取失败: {}", keyword, e);
            }
        }

        log.info("=== 定时爬虫任务完成 ===");
    }

    /**
     * 记录价格历史
     */
    private void recordPriceHistory(HardwarePrice oldPrice, HardwarePrice newPrice) {
        PriceHistory history = new PriceHistory();
        history.setPriceId(oldPrice.getId());
        history.setProductName(oldPrice.getProductName());
        history.setPlatform(oldPrice.getPlatform());
        history.setPrice(newPrice.getPrice());

        BigDecimal priceChange = newPrice.getPrice().subtract(oldPrice.getPrice());
        history.setPriceChange(priceChange);

        if (priceChange.compareTo(BigDecimal.ZERO) > 0) {
            history.setPriceChangeType("UP");
        } else if (priceChange.compareTo(BigDecimal.ZERO) < 0) {
            history.setPriceChangeType("DOWN");
        } else {
            history.setPriceChangeType("STABLE");
        }

        history.setRecordTime(LocalDateTime.now());
        history.setCreateTime(LocalDateTime.now());

        priceHistoryMapper.insert(history);

        log.info("价格变化记录: {} - {} -> {} ({}{})",
                oldPrice.getProductName(),
                oldPrice.getPrice(),
                newPrice.getPrice(),
                priceChange.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "",
                priceChange);
    }

    /**
     * 生成购买推荐
     */
    private String generateRecommendation(HardwarePrice lowestPrice, HardwarePrice highestPrice, double avgPrice) {
        if (lowestPrice == null || highestPrice == null) {
            return "暂无推荐";
        }

        BigDecimal priceDiff = highestPrice.getPrice().subtract(lowestPrice.getPrice());
        double diffPercent = priceDiff.doubleValue() / highestPrice.getPrice().doubleValue() * 100;

        StringBuilder sb = new StringBuilder();
        sb.append("【购买建议】\n");
        sb.append(String.format("推荐平台: %s\n", lowestPrice.getPlatform()));
        sb.append(String.format("推荐商品: %s\n", lowestPrice.getProductName()));
        sb.append(String.format("推荐价格: %.2f 元\n", lowestPrice.getPrice()));
        sb.append(String.format("比最高价便宜: %.2f 元 (%.1f%%)\n", priceDiff, diffPercent));

        if (lowestPrice.getPrice().doubleValue() < avgPrice * 0.9) {
            sb.append("💡 当前价格低于平均价10%以上，建议立即购买！");
        } else if (lowestPrice.getPrice().doubleValue() > avgPrice * 1.1) {
            sb.append("⚠️ 当前价格偏高，建议等待降价。");
        } else {
            sb.append("✅ 当前价格合理，可以考虑购买。");
        }

        return sb.toString();
    }
}