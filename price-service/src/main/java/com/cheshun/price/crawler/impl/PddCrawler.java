package com.cheshun.price.crawler.impl;

import com.cheshun.price.crawler.PlatformCrawler;
import com.cheshun.price.crawler.config.CrawlerConfig;
import com.cheshun.price.domain.entity.HardwarePrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 拼多多平台爬虫
 *
 * 【特点】
 * 拼多多价格通常最低，但需要注意：
 * 1. 可能是翻新品
 * 2. 需要拼单价格才最低
 * 3. 反爬虫也比较严格
 */
@Slf4j
@Component
public class PddCrawler implements PlatformCrawler {

    @Autowired
    private CrawlerConfig crawlerConfig;

    private static final Random random = new Random();

    @Override
    public String getPlatformName() {
        return "PDD";
    }

    @Override
    public List<HardwarePrice> searchProducts(String keyword, int page, int pageSize) {
        log.info("拼多多爬虫 - 搜索商品: keyword={}, page={}, pageSize={}", keyword, page, pageSize);
        return simulatePddSearch(keyword, pageSize);
    }

    @Override
    public HardwarePrice getProductDetail(String productUrl) {
        log.info("拼多多爬虫 - 获取商品详情: url={}", productUrl);
        return null;
    }

    @Override
    public boolean isSupported() {
        return crawlerConfig.getEnabled() &&
                crawlerConfig.getPlatforms().getOrDefault("pdd",
                        new CrawlerConfig.PlatformConfig()).getEnabled();
    }

    /**
     * 模拟拼多多搜索数据
     */
    private List<HardwarePrice> simulatePddSearch(String keyword, int pageSize) {
        List<HardwarePrice> result = new ArrayList<>();

        String[] motherboards = {
                "华硕 PRIME B760M-K D4 主板",
                "微星 B760M BOMBER DDR4",
                "技嘉 B760M D3H DDR4",
                "华擎 B760M-C",
                "铭瑄 MS-终结者 B760M"
        };

        String[] shops = {
                "华硕拼多多旗舰店",
                "微星拼多多旗舰店",
                "技嘉拼多多官方店",
                "华擎官方店",
                "铭瑄数码专营店"
        };

        for (int i = 0; i < Math.min(pageSize, motherboards.length); i++) {
            HardwarePrice price = new HardwarePrice();
            price.setProductName(motherboards[i]);
            price.setProductModel(extractModel(motherboards[i]));
            price.setProductType("MOTHERBOARD");
            price.setBrand(extractBrand(motherboards[i]));
            price.setPlatform("PDD");
            price.setProductUrl("https://mobile.yangkeduo.com/goods.html?goods_id=" + (1000000000L + random.nextInt(999999999)));

            // 拼多多价格最低（通常比淘宝低5%-15%）
            BigDecimal basePrice = new BigDecimal(680 + random.nextInt(1000));
            price.setPrice(basePrice);
            price.setOriginalPrice(basePrice.add(new BigDecimal(random.nextInt(400))));

            price.setSalesVolume(3000 + random.nextInt(47000));
            price.setShopName(shops[i]);
            price.setImageUrl("https://img.pddpic.com/example_" + i + ".jpg");
            price.setDescription(motherboards[i] + " 百亿补贴 全新正品");
            price.setCrawlTime(LocalDateTime.now());
            price.setCreateTime(LocalDateTime.now());
            price.setUpdateTime(LocalDateTime.now());

            result.add(price);

            log.info("模拟数据 - 拼多多: {} - 价格: {}", price.getProductName(), price.getPrice());
        }

        return result;
    }

    /**
     * 真实拼多多爬虫思路
     *
     * 拼多多特点：
     * 1. 移动端为主
     * 2. API接口相对简单
     * 3. 需要处理反爬虫参数（anti_content）
     *
     * 实现方案：
     * - 抓包分析API
     * - 模拟请求参数
     * - 使用代理IP
     */

    private String extractBrand(String productName) {
        if (productName.contains("华硕") || productName.contains("ASUS")) return "华硕";
        if (productName.contains("微星") || productName.contains("MSI")) return "微星";
        if (productName.contains("技嘉") || productName.contains("GIGABYTE")) return "技嘉";
        if (productName.contains("华擎") || productName.contains("ASRock")) return "华擎";
        if (productName.contains("铭瑄")) return "铭瑄";
        return "未知";
    }

    private String extractModel(String productName) {
        if (productName.contains("B760")) return "B760";
        if (productName.contains("Z790")) return "Z790";
        if (productName.contains("B650")) return "B650";
        return "UNKNOWN";
    }
}
