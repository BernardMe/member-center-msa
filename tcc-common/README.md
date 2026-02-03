# TCC 分布式事务示例项目

## 项目简介

本项目展示了基于 **TCC（Try-Confirm-Cancel）模式** 的分布式事务实现，模拟电商场景中的订单服务和库存服务。

### 业务场景

用户下单购买商品时，需要保证订单创建和库存扣减的原子性：
- **订单服务**：创建订单记录
- **库存服务**：扣减商品库存（使用冻结库存机制）

### TCC 模式说明

TCC 是一种补偿型分布式事务解决方案，分为三个阶段：

1. **Try 阶段（预留资源）**
   - 订单服务：创建预订单（状态为 PENDING）
   - 库存服务：冻结库存（从可用库存转移到冻结库存）

2. **Confirm 阶段（确认提交）**
   - 订单服务：确认订单（状态改为 CONFIRMED）
   - 库存服务：确认扣减（从冻结库存扣减，减少总库存）

3. **Cancel 阶段（回滚补偿）**
   - 订单服务：取消订单（状态改为 CANCELED）
   - 库存服务：释放冻结库存（恢复到可用库存）

## 项目架构

```
tcc-demo
├── tcc-common          # 公共模块（TCC上下文、枚举、实体等）
├── order-service       # 订单服务（端口：8080）
├── inventory-service   # 库存服务（端口：8081）
└── sql                 # 数据库初始化脚本
```

## 技术栈

- **Spring Boot 2.7.14**
- **Spring Cloud OpenFeign** - 服务间调用
- **MyBatis-Plus 3.5.3.1** - 持久层框架
- **MySQL 8.0** - 数据库
- **Druid** - 数据库连接池
- **Lombok** - 简化代码

## 核心特性

### 1. 冻结库存机制

库存表设计：
```sql
CREATE TABLE t_inventory (
    available_stock INT    -- 可用库存
    frozen_stock INT       -- 冻结库存
    total_stock INT        -- 总库存（= 可用 + 冻结）
    version INT            -- 乐观锁版本号
);
```

### 2. 幂等性保证

- 使用 `transaction_id + branch_id` 唯一标识每个分支事务
- 执行前检查是否已执行过，防止重复操作

### 3. 乐观锁防止并发问题

```java
@Update("UPDATE t_inventory SET 
         available_stock = available_stock - #{quantity}, 
         frozen_stock = frozen_stock + #{quantity}, 
         version = version + 1 
         WHERE product_id = #{productId} 
         AND available_stock >= #{quantity} 
         AND version = #{version}")
int freezeStock(@Param("productId") Long productId, 
                @Param("quantity") Integer quantity,
                @Param("version") Integer version);
```

### 4. 事务记录与状态管理

库存冻结记录表：
```sql
CREATE TABLE t_inventory_frozen (
    transaction_id VARCHAR(64)  -- 全局事务ID
    branch_id VARCHAR(64)       -- 分支事务ID
    product_id BIGINT           -- 商品ID
    frozen_quantity INT         -- 冻结数量
    status VARCHAR(32)          -- TCC状态（TRYING/CONFIRMED/CANCELED）
);
```

## 快速开始

### 1. 环境准备

- JDK 11+
- Maven 3.6+
- MySQL 8.0+

### 2. 初始化数据库

执行初始化脚本：
```bash
mysql -u root -p < sql/init.sql
```

这将创建：
- `tcc_order` 数据库（订单表）
- `tcc_inventory` 数据库（库存表、库存冻结记录表）
- 测试商品数据

### 3. 修改配置

根据实际情况修改各服务的 `application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tcc_order
    username: root
    password: your_password
```

### 4. 启动服务

分别启动两个服务：

```bash
# 启动库存服务（端口：8081）
cd inventory-service
mvn spring-boot:run

# 启动订单服务（端口：8080）
cd order-service
mvn spring-boot:run
```

### 5. 测试

使用提供的 HTTP 请求文件进行测试：

**成功场景（库存充足）：**
```bash
POST http://localhost:8080/order/create
Content-Type: application/json

{
  "userId": 1001,
  "productId": 1001,
  "productName": "iPhone 15 Pro",
  "quantity": 2,
  "amount": 19999.00
}
```

**失败场景（库存不足）：**
```bash
POST http://localhost:8080/order/create
Content-Type: application/json

{
  "userId": 1002,
  "productId": 1001,
  "productName": "iPhone 15 Pro",
  "quantity": 200,
  "amount": 1999900.00
}
```

## 执行流程详解

### 正常流程（成功）

```
1. 用户请求创建订单
   ↓
2. Try 阶段
   ├─ 订单服务：创建预订单（status=PENDING, tcc_status=TRYING）
   └─ 库存服务：冻结库存（available_stock-2, frozen_stock+2）
   ↓
3. Confirm 阶段
   ├─ 订单服务：确认订单（status=CONFIRMED, tcc_status=CONFIRMED）
   └─ 库存服务：确认扣减（frozen_stock-2, total_stock-2）
   ↓
4. 返回订单号
```

### 异常流程（回滚）

```
1. 用户请求创建订单
   ↓
2. Try 阶段
   ├─ 订单服务：创建预订单 ✓
   └─ 库存服务：冻结库存失败 ✗（库存不足）
   ↓
3. Cancel 阶段（回滚）
   ├─ 订单服务：取消订单（status=CANCELED, tcc_status=CANCELED）
   └─ 库存服务：释放冻结库存（若有冻结）
   ↓
4. 返回错误信息
```

## 日志输出示例

成功场景日志：
```
=== 开始TCC分布式事务 ===
全局事务ID: TXN_abc123...
订单分支ID: ORDER_def456...
库存分支ID: INVENTORY_ghi789...

--- Try阶段开始 ---
订单服务Try成功，订单号: ORD_1706889123456
库存服务Try成功
--- Try阶段完成 ---

--- Confirm阶段开始 ---
订单服务Confirm成功
库存服务Confirm成功
--- Confirm阶段完成 ---

=== TCC分布式事务成功完成 ===
```

失败场景日志：
```
=== 开始TCC分布式事务 ===
...
--- Try阶段开始 ---
订单服务Try成功，订单号: ORD_1706889123456
库存服务Try失败: 库存不足

=== TCC事务异常，开始回滚 ===
--- Cancel阶段开始 ---
订单服务Cancel成功
库存服务Cancel成功（若Try成功过）
--- Cancel阶段完成 ---
=== TCC分布式事务回滚完成 ===
```

## 数据库状态验证

**查看库存状态：**
```sql
USE tcc_inventory;
SELECT * FROM t_inventory WHERE product_id = 1001;
SELECT * FROM t_inventory_frozen ORDER BY create_time DESC LIMIT 10;
```

**查看订单状态：**
```sql
USE tcc_order;
SELECT * FROM t_order ORDER BY create_time DESC LIMIT 10;
```

## 关键设计要点

### 1. 幂等性设计
- 使用唯一的事务ID组合（transaction_id + branch_id）
- 执行前检查操作是否已完成
- 支持重试机制

### 2. 资源预留
- Try阶段不直接扣减总库存，而是冻结
- 保证资源可回滚

### 3. 补偿机制
- Cancel阶段必须能够撤销Try阶段的操作
- 支持部分成功的回滚

### 4. 并发控制
- 使用乐观锁（version字段）防止并发冲突
- SQL条件判断确保数据一致性

### 5. 异常处理
- 完善的异常捕获和日志记录
- Try失败直接返回，不进入Confirm
- 任何阶段失败都触发Cancel

## 进阶优化建议

实际生产环境中，建议增强以下功能：

1. **事务协调器**
   - 独立的 TCC 事务管理服务
   - 记录全局事务状态
   - 定时检查超时事务

2. **超时处理**
   - 设置事务超时时间
   - 自动触发 Cancel

3. **重试机制**
   - Confirm/Cancel 失败后自动重试
   - 设置最大重试次数

4. **异步化**
   - Confirm/Cancel 阶段可考虑异步执行
   - 使用消息队列保证可靠性

5. **监控告警**
   - 记录事务执行时间
   - 统计成功/失败率
   - 异常事务告警

6. **分布式事务框架**
   - 集成 Seata 等成熟框架
   - 简化开发和运维

## 常见问题

### Q1: 如果 Confirm 阶段失败怎么办？
A: Confirm 阶段失败应该进行重试，因为 Try 阶段已经预留了资源。如果重试多次仍失败，需要人工介入处理。

### Q2: Cancel 阶段也可能失败吗？
A: 是的，Cancel 也可能失败。应该设计为可重试的幂等操作，确保最终能够回滚。

### Q3: 空回滚是什么？
A: Try 阶段因网络原因未执行，但 Cancel 被调用。需要在 Cancel 中检查 Try 是否执行过。

### Q4: 悬挂问题如何解决？
A: Try 因网络延迟后到达，但此时 Cancel 已执行完成。需要在 Try 中检查是否已被 Cancel。

## 许可证

MIT License

## 联系方式

如有问题或建议，欢迎提 Issue。