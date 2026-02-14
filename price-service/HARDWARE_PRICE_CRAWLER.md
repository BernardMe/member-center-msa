# 硬件价格爬虫功能说明

## 功能概述

在原有的 TCC 分布式事务（订单服务 + 库存服务）基础上，为库存服务增加了**计算机硬件商品报价记录**和**电商平台爬虫**功能。

### 核心功能

1. **多平台价格爬取**：支持淘宝、京东、拼多多三大电商平台
2. **价格比较分析**：自动对比各平台价格，找出最优惠方案
3. **价格历史追踪**：记录价格变化趋势，分析涨跌
4. **定时自动爬取**：每天凌晨2点自动爬取热门商品价格
5. **智能购买建议**：基于价格分析生成购买推荐

## 系统架构

```
inventory-service (库存服务)
├── 原有功能
│   ├── 库存管理（TCC Try/Confirm/Cancel）
│   ├── 冻结库存机制
│   └── 库存扣减
│
└── 新增功能：硬件价格爬虫
    ├── 爬虫引擎
    │   ├── TaobaoCrawler（淘宝爬虫）
    │   ├── JdCrawler（京东爬虫）
    │   └── PddCrawler（拼多多爬虫）
    │
    ├── 数据存储
    │   ├── t_hardware_price（商品报价表）
    │   └── t_price_history（价格历史表）
    │
    ├── 业务服务
    │   ├── HardwarePriceCrawlerService（爬虫服务）
    │   └── 定时任务（每天自动爬取）
    │
    └── API 接口
        ├── /hardware-price/crawl（爬取价格）
        ├── /hardware-price/compare（价格比较）
        └── /hardware-price/list（查询列表）
```

## 数据库设计

### 1. 商品报价表 (t_hardware_price)

```sql
CREATE TABLE t_hardware_price (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,        -- 商品名称
    product_model VARCHAR(100),                -- 型号（如 B760）
    product_type VARCHAR(50) NOT NULL,         -- 类型（主板/CPU/显卡等）
    brand VARCHAR(50),                         -- 品牌（华硕/微星等）
    platform VARCHAR(50) NOT NULL,             -- 平台（淘宝/京东/拼多多）
    product_url VARCHAR(500),                  -- 商品链接
    price DECIMAL(10,2) NOT NULL,              -- 当前价格
    original_price DECIMAL(10,2),              -- 原价
    sales_volume INT,                          -- 销量
    shop_name VARCHAR(255),                    -- 店铺名称
    image_url VARCHAR(500),                    -- 商品图片
    description TEXT,                          -- 商品描述
    crawl_time DATETIME,                       -- 爬取时间
    create_time DATETIME,
    update_time DATETIME
);
```

### 2. 价格历史表 (t_price_history)

```sql
CREATE TABLE t_price_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    price_id BIGINT NOT NULL,                  -- 关联报价ID
    product_name VARCHAR(255) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,              -- 价格
    price_change_type VARCHAR(20),             -- UP/DOWN/STABLE
    price_change DECIMAL(10,2),                -- 价格变化金额
    record_time DATETIME NOT NULL,             -- 记录时间
    create_time DATETIME
);
```

## 爬虫实现说明

### ⚠️ 重要提示

由于淘宝、京东、拼多多等电商平台有**严格的反爬虫机制**：

1. **需要登录验证**：必须登录才能查看价格
2. **验证码挑战**：滑块验证码、图片验证码
3. **IP限制**：频繁访问会被封IP
4. **动态渲染**：页面由JavaScript动态加载
5. **数据加密**：API接口参数加密

### 当前实现方案

**方案A：模拟数据（演示用）** ✅ 当前采用

- 生成模拟的商品价格数据
- 模拟三个平台的价格差异
- 适合演示和功能测试
- 不依赖外部网络

**方案B：真实爬虫（需要额外配置）** 🔧 需要自行实现

代码中提供了真实爬虫的实现思路（注释部分），包括：

#### 真实爬虫实现方案

**方法1：使用官方API（推荐）** ⭐

```java
// 淘宝开放平台 API
// 需要申请开发者账号
// https://open.taobao.com/

// 京东联盟 API
// https://union.jd.com/

// 拼多多开放平台 API
// https://open.pinduoduo.com/
```

优点：
- 稳定可靠，不会被封
- 数据准确完整
- 官方支持

缺点：
- 需要申请开发者账号
- 可能有调用限制
- 部分数据需要收费

**方法2：Selenium + 无头浏览器**

```java
// 使用 Selenium WebDriver
WebDriver driver = new ChromeDriver();
driver.get("https://search.jd.com/Search?keyword=华硕B760");

// 等待页面加载
Thread.sleep(2000);

// 解析商品列表
List<WebElement> items = driver.findElements(
    By.cssSelector(".gl-item")
);
```

依赖配置（需要添加到 pom.xml）：

```xml
<!-- Selenium -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.15.0</version>
</dependency>

<!-- ChromeDriver -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.6.2</version>
</dependency>
```

优点：
- 可以爬取需要登录的页面
- 支持JavaScript渲染
- 模拟真实用户行为

缺点：
- 性能较低
- 需要安装浏览器驱动
- 容易被识别为机器人

**方法3：第三方数据服务**

- 快递鸟
- 八爪鱼采集器
- 神箭手云爬虫
- 阿里云数据服务

优点：
- 专业稳定
- 数据质量高
- 无需自己维护

缺点：
- 需要付费
- 有调用限制

### 反反爬虫策略

如果要实现真实爬虫，需要：

1. **使用代理IP池**
```yaml
crawler:
  proxy:
    enabled: true
    host: proxy.example.com
    port: 8888
```

2. **随机User-Agent**
```java
String[] userAgents = {
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64)...",
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)..."
};
String ua = userAgents[random.nextInt(userAgents.length)];
```

3. **请求限速**
```java
Thread.sleep(2000); // 每个请求间隔2秒
```

4. **模拟真实行为**
```java
// 先访问首页
driver.get("https://www.jd.com");
Thread.sleep(1000);

// 再进行搜索
driver.get(searchUrl);
```

## API 使用示例

### 1. 爬取指定主板价格

```bash
GET /hardware-price/crawl?keyword=华硕 B760
```

响应：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "JD": [
      {
        "productName": "华硕 ROG STRIX B760-A GAMING WIFI D5",
        "price": 1299.00,
        "platform": "JD",
        "shopName": "华硕京东自营旗舰店"
      }
    ],
    "TAOBAO": [...],
    "PDD": [...]
  }
}
```

### 2. 价格比较分析

```bash
GET /hardware-price/compare?keyword=华硕 B760
```

响应：
```json
{
  "code": 200,
  "data": {
    "keyword": "华硕 B760",
    "lowestPrice": {
      "platform": "PDD",
      "price": 799.00
    },
    "highestPrice": {
      "platform": "JD",
      "price": 1299.00
    },
    "avgPrice": 999.00,
    "recommendation": "【购买建议】\n推荐平台: PDD\n推荐价格: 799.00 元\n比最高价便宜: 500.00 元 (38.5%)\n💡 当前价格低于平均价10%以上，建议立即购买！"
  }
}
```

### 3. 查询价格列表

```bash
GET /hardware-price/list?page=1&pageSize=10&platform=JD
```

### 4. 定时爬虫

系统会在每天凌晨 2:00 自动爬取热门主板价格：
- 华硕 B760
- 微星 B760
- 技嘉 B760
- 华硕 Z790
- 微星 Z790

也可以手动触发：
```bash
POST /hardware-price/scheduled-crawl
```

## 快速开始

### 1. 初始化数据库

```bash
mysql -u root -p < sql/hardware_price_tables.sql
```

### 2. 启动服务

```bash
cd inventory-service
mvn spring-boot:run
```

### 3. 测试爬虫

使用提供的 `test-hardware-price.http` 文件进行测试：

```bash
# 爬取华硕 B760 主板价格
GET http://localhost:8081/hardware-price/crawl?keyword=华硕 B760

# 价格比较
GET http://localhost:8081/hardware-price/compare?keyword=华硕 B760
```

## 配置说明

在 `application.yml` 中配置爬虫参数：

```yaml
crawler:
  # 是否启用爬虫
  enabled: true
  
  # 请求超时时间（毫秒）
  timeout: 10000
  
  # 重试次数
  retry-count: 3
  
  # 请求间隔（毫秒）
  request-interval: 2000
  
  # 平台配置
  platforms:
    taobao:
      enabled: true
    jd:
      enabled: true
    pdd:
      enabled: true
```

## 使用场景

### 场景1：用户想买主板，对比价格

```
用户输入: "华硕 B760"
系统爬取三大平台价格
分析对比结果
给出购买建议: "拼多多最便宜，建议购买"
```

### 场景2：商家监控竞品价格

```
定时任务每天爬取竞品价格
记录价格变化历史
发现异常降价 → 触发告警
```

### 场景3：价格趋势分析

```
查询某商品30天的价格历史
分析价格波动趋势
预测最佳购买时机
```

## 注意事项

1. **模拟数据 vs 真实数据**
   - 当前使用模拟数据，适合演示
   - 如需真实数据，请实现方案B

2. **法律合规**
   - 爬虫需遵守 robots.txt
   - 不得用于商业用途（除非获得授权）
   - 注意数据隐私保护

3. **性能优化**
   - 使用缓存减少重复爬取
   - 异步爬取提高效率
   - 定期清理过期数据

4. **错误处理**
   - 网络异常重试
   - 反爬虫应对策略
   - 日志记录方便调试

## 扩展功能建议

1. **支持更多商品类型**
   - CPU（处理器）
   - GPU（显卡）
   - RAM（内存）
   - SSD（固态硬盘）

2. **价格预警**
   - 价格低于设定值时发送通知
   - 降价提醒
   - 价格异常波动告警

3. **数据可视化**
   - 价格走势图表
   - 平台价格对比柱状图
   - 销量排行榜

4. **智能推荐**
   - 基于用户预算推荐
   - 性价比排行
   - 热门商品推荐

## 总结

这个功能展示了如何在微服务中集成爬虫功能，实现了：

✅ 多平台数据采集
✅ 价格比较分析
✅ 定时自动爬取
✅ 价格历史追踪
✅ 智能购买建议

虽然当前使用模拟数据，但代码结构完整，可以方便地切换到真实爬虫实现。