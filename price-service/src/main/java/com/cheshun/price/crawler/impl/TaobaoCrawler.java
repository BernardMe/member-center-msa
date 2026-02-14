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
 * 淘宝平台爬虫
 *
 * 【重要说明】
 * 淘宝反爬虫机制最严格：
 * 1. 强制登录才能看价格
 * 2. 滑块验证码
 * 3. IP限制
 * 4. 页面动态渲染（JS加载）
 *
 * 建议使用：
 * - Selenium + 人工Cookie
 * - 淘宝开放平台API（需要申请）
 * - 第三方数据服务
 */
@Slf4j
@Component
public class TaobaoCrawler implements PlatformCrawler {

    @Autowired
    private CrawlerConfig crawlerConfig;

    private static final Random random = new Random();

    @Override
    public String getPlatformName() {
        return "TAOBAO";
    }

    @Override
    public List<HardwarePrice> searchProducts(String keyword, int page, int pageSize) {
        log.info("淘宝爬虫 - 搜索商品: keyword={}, page={}, pageSize={}", keyword, page, pageSize);
        return simulateTaobaoSearch(keyword, pageSize);
    }

    @Override
    public HardwarePrice getProductDetail(String productUrl) {
        log.info("淘宝爬虫 - 获取商品详情: url={}", productUrl);
        return null;
    }

    @Override
    public boolean isSupported() {
        return crawlerConfig.getEnabled() &&
                crawlerConfig.getPlatforms().getOrDefault("taobao",
                        new CrawlerConfig.PlatformConfig()).getEnabled();
    }

    /**
     * 模拟淘宝搜索数据
     */
    private List<HardwarePrice> simulateTaobaoSearch(String keyword, int pageSize) {
        List<HardwarePrice> result = new ArrayList<>();

        String[] motherboards = {
                "华硕 TUF GAMING B760M-PLUS WIFI D4",
                "微星 PRO B760M-A WIFI DDR4",
                "技嘉 B760M DS3H DDR4",
                "华擎 B760M-HDV/M.2 D4",
                "七彩虹 战斧 B760M-PLUS V20"
        };

        String[] shops = {
                "华硕官方旗舰店",
                "微星电脑旗舰店",
                "技嘉旗舰店",
                "华擎旗舰店",
                "七彩虹旗舰店"
        };

        for (int i = 0; i < Math.min(pageSize, motherboards.length); i++) {
            HardwarePrice price = new HardwarePrice();
            price.setProductName(motherboards[i]);
            price.setProductModel(extractModel(motherboards[i]));
            price.setProductType("MOTHERBOARD");
            price.setBrand(extractBrand(motherboards[i]));
            price.setPlatform("TAOBAO");
            price.setProductUrl("https://item.taobao.com/item.htm?id=" + (600000000000L + random.nextInt(99999999)));

            // 淘宝价格通常最低
            BigDecimal basePrice = new BigDecimal(750 + random.nextInt(1100));
            price.setPrice(basePrice);
            price.setOriginalPrice(basePrice.add(new BigDecimal(random.nextInt(300))));

            price.setSalesVolume(10000 + random.nextInt(90000));
            price.setShopName(shops[i]);
            price.setImageUrl("https://img.alicdn.com/example_" + i + ".jpg");
            price.setDescription(motherboards[i] + " 全新原装 顺丰包邮");
            price.setCrawlTime(LocalDateTime.now());
            price.setCreateTime(LocalDateTime.now());
            price.setUpdateTime(LocalDateTime.now());

            result.add(price);

            log.info("模拟数据 - 淘宝: {} - 价格: {}", price.getProductName(), price.getPrice());
        }

        return result;
    }

    /**
     * 真实淘宝爬虫思路
     *
     * 方法1: 使用淘宝开放平台API（推荐）
     * - 需要申请淘宝开放平台账号
     * - 使用官方API获取商品信息
     * - 稳定可靠，不会被封
     *
     * 方法2: Selenium + 人工Cookie
     * - 人工登录淘宝获取Cookie
     * - 使用Selenium模拟浏览器
     * - 定期更新Cookie
     *
     * 方法3: 第三方数据服务
     * - 购买专业的电商数据服务
     * - 如：快递鸟、八爪鱼等
     */

    private String extractBrand(String productName) {
        if (productName.contains("华硕") || productName.contains("ASUS")) return "华硕";
        if (productName.contains("微星") || productName.contains("MSI")) return "微星";
        if (productName.contains("技嘉") || productName.contains("GIGABYTE")) return "技嘉";
        if (productName.contains("华擎") || productName.contains("ASRock")) return "华擎";
        if (productName.contains("七彩虹")) return "七彩虹";
        return "未知";
    }

    private String extractModel(String productName) {
        if (productName.contains("B760")) return "B760";
        if (productName.contains("Z790")) return "Z790";
        if (productName.contains("B650")) return "B650";
        return "UNKNOWN";
    }
}