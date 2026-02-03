-- ================================================
-- TCC分布式事务示例 - 数据库初始化脚本
-- ================================================

-- 创建订单数据库
CREATE DATABASE IF NOT EXISTS tcc_order DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE tcc_order;

-- 订单表
DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_no VARCHAR(64) NOT NULL COMMENT '订单号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    product_name VARCHAR(255) NOT NULL COMMENT '商品名称',
    quantity INT NOT NULL COMMENT '购买数量',
    amount DECIMAL(10,2) NOT NULL COMMENT '订单金额',
    status VARCHAR(32) NOT NULL COMMENT '订单状态: PENDING-待确认, CONFIRMED-已确认, CANCELED-已取消',
    transaction_id VARCHAR(64) NOT NULL COMMENT 'TCC全局事务ID',
    tcc_status VARCHAR(32) NOT NULL COMMENT 'TCC状态',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_order_no (order_no),
    INDEX idx_transaction_id (transaction_id),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';


-- ================================================
-- 创建库存数据库
-- ================================================
CREATE DATABASE IF NOT EXISTS tcc_inventory DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE tcc_inventory;

-- 库存表
DROP TABLE IF EXISTS t_inventory;
CREATE TABLE t_inventory (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    product_id BIGINT NOT NULL UNIQUE COMMENT '商品ID',
    product_name VARCHAR(255) NOT NULL COMMENT '商品名称',
    available_stock INT NOT NULL DEFAULT 0 COMMENT '可用库存',
    frozen_stock INT NOT NULL DEFAULT 0 COMMENT '冻结库存',
    total_stock INT NOT NULL DEFAULT 0 COMMENT '总库存',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    version INT NOT NULL DEFAULT 0 COMMENT '版本号（乐观锁）',
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 库存冻结记录表
DROP TABLE IF EXISTS t_inventory_frozen;
CREATE TABLE t_inventory_frozen (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    transaction_id VARCHAR(64) NOT NULL COMMENT 'TCC全局事务ID',
    branch_id VARCHAR(64) NOT NULL COMMENT 'TCC分支事务ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    frozen_quantity INT NOT NULL COMMENT '冻结数量',
    status VARCHAR(32) NOT NULL COMMENT 'TCC状态',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_transaction_branch (transaction_id, branch_id),
    INDEX idx_transaction_id (transaction_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存冻结记录表';

-- ================================================
-- 插入测试数据
-- ================================================

-- 插入商品库存数据
INSERT INTO t_inventory (product_id, product_name, available_stock, frozen_stock, total_stock, version)
VALUES
(1001, 'iPhone 15 Pro', 100, 0, 100, 0),
(1002, 'MacBook Pro', 50, 0, 50, 0),
(1003, 'AirPods Pro', 200, 0, 200, 0);

-- 查看初始化结果
SELECT * FROM t_inventory;