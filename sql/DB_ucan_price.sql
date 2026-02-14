-- ================================================
-- 创建比价数据库
-- ================================================
CREATE DATABASE IF NOT EXISTS ucan_price DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 附权走SSH终端
 grant all on `ucan_price`.* to `ucan_price`@'%' IDENTIFIED BY 'R*cYe2q0n$I7DK8G';

USE ucan_price;


-- 硬件商品报价表
DROP TABLE IF EXISTS t_hardware_price;
CREATE TABLE t_hardware_price (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_name VARCHAR(255) NOT NULL COMMENT '商品名称',
    product_model VARCHAR(100) COMMENT '商品型号',
    product_type VARCHAR(50) NOT NULL COMMENT '商品类型: MOTHERBOARD-主板, CPU-处理器, GPU-显卡, RAM-内存, SSD-固态硬盘',
    brand VARCHAR(50) COMMENT '品牌',
    platform VARCHAR(50) NOT NULL COMMENT '电商平台: TAOBAO-淘宝, JD-京东, PDD-拼多多',
    product_url VARCHAR(500) COMMENT '商品链接',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    original_price DECIMAL(10,2) COMMENT '原价',
    sales_volume INT COMMENT '销量',
    shop_name VARCHAR(255) COMMENT '店铺名称',
    image_url VARCHAR(500) COMMENT '商品图片URL',
    description TEXT COMMENT '商品描述',
    crawl_time DATETIME COMMENT '爬取时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_product_model (product_model),
    INDEX idx_platform (platform),
    INDEX idx_product_type (product_type),
    INDEX idx_brand (brand),
    INDEX idx_crawl_time (crawl_time),
    INDEX idx_price (price)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='硬件商品报价表';

-- 价格历史记录表
DROP TABLE IF EXISTS t_price_history;
CREATE TABLE t_price_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    price_id BIGINT NOT NULL COMMENT '关联的商品报价ID',
    product_name VARCHAR(255) NOT NULL COMMENT '商品名称',
    platform VARCHAR(50) NOT NULL COMMENT '电商平台',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    price_change_type VARCHAR(20) COMMENT '价格变化类型: UP-上涨, DOWN-下降, STABLE-稳定',
    price_change DECIMAL(10,2) COMMENT '价格变化金额',
    record_time DATETIME NOT NULL COMMENT '记录时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_price_id (price_id),
    INDEX idx_record_time (record_time),
    INDEX idx_platform (platform)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='价格历史记录表';

-- ================================================
-- 插入一些测试数据（可选）
-- ================================================

-- 插入模拟的主板价格数据
INSERT INTO t_hardware_price
(product_name, product_model, product_type, brand, platform, product_url, price, original_price, sales_volume, shop_name, description, crawl_time)
VALUES
('华硕 TUF GAMING B760M-PLUS WIFI D4', 'B760', 'MOTHERBOARD', '华硕', 'TAOBAO',
 'https://item.taobao.com/item.htm?id=123456789', 899.00, 1099.00, 12580,
 '华硕官方旗舰店', '全新原装 顺丰包邮', NOW()),

('华硕 ROG STRIX B760-A GAMING WIFI D5', 'B760', 'MOTHERBOARD', '华硕', 'JD',
 'https://item.jd.com/100123456789.html', 1299.00, 1499.00, 8965,
 '华硕京东自营旗舰店', '支持DDR5 WiFi6E', NOW()),

('微星 MAG B760M MORTAR WIFI DDR4', 'B760', 'MOTHERBOARD', '微星', 'PDD',
 'https://mobile.yangkeduo.com/goods.html?goods_id=234567890', 799.00, 999.00, 6543,
 '微星拼多多旗舰店', '百亿补贴 全新正品', NOW());

-- 查看插入的数据
SELECT * FROM t_hardware_price;

-- ================================================
-- 常用查询示例
-- ================================================

-- 1. 查询指定型号的最低价
SELECT * FROM t_hardware_price
WHERE product_model = 'B760'
ORDER BY price ASC
LIMIT 1;

-- 2. 按平台统计平均价格
SELECT platform,
       AVG(price) as avg_price,
       MIN(price) as min_price,
       MAX(price) as max_price,
       COUNT(*) as count
FROM t_hardware_price
WHERE product_model = 'B760'
GROUP BY platform;

-- 3. 查询最近爬取的数据
SELECT * FROM t_hardware_price
WHERE crawl_time >= DATE_SUB(NOW(), INTERVAL 1 DAY)
ORDER BY crawl_time DESC;

-- 4. 查询价格变化历史
SELECT h.*, p.product_name, p.platform
FROM t_price_history h
LEFT JOIN t_hardware_price p ON h.price_id = p.id
WHERE h.price_change_type = 'DOWN'
ORDER BY h.record_time DESC
LIMIT 10;

-- 5. 清理30天前的旧数据
-- DELETE FROM t_hardware_price
-- WHERE crawl_time < DATE_SUB(NOW(), INTERVAL 30 DAY);
