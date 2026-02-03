package com.cheshun.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cheshun.common.result.Result;
import com.cheshun.order.dto.CreateOrderRequest;
import com.cheshun.order.entity.Order;
import com.cheshun.order.feign.InventoryFeignClient;
import com.cheshun.order.mapper.OrderMapper;
import com.cheshun.order.service.OrderService;
import com.cheshun.tcc.common.enums.TccStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.cheshun.common.result.Result.SUCC_CODE;

/**
 * 订单服务实现 - TCC 分布式事务
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private InventoryFeignClient inventoryFeignClient;

    /**
     * 创建订单（使用TCC分布式事务）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(CreateOrderRequest request) {
        // 生成全局事务ID和分支事务ID
        String transactionId = "TXN_" + UUID.randomUUID().toString().replace("-", "");
        String orderBranchId = "ORDER_" + UUID.randomUUID().toString().replace("-", "");
        String inventoryBranchId = "INVENTORY_" + UUID.randomUUID().toString().replace("-", "");

        log.info("=== 开始TCC分布式事务 ===");
        log.info("全局事务ID: {}", transactionId);
        log.info("订单分支ID: {}", orderBranchId);
        log.info("库存分支ID: {}", inventoryBranchId);

        Order order = null;
        boolean inventoryTrySuccess = false;

        try {
            // ========== Try 阶段 ==========
            log.info("--- Try阶段开始 ---");

            // 1. 订单服务 Try：创建预订单
            order = tryCreateOrder(transactionId, orderBranchId, request);
            log.info("订单服务Try成功，订单号: {}", order.getOrderNo());

            // 2. 库存服务 Try：冻结库存
            Result<Boolean> inventoryTryResult = inventoryFeignClient.tryFreeze(
                    transactionId, inventoryBranchId, request.getProductId(), request.getQuantity());

            if (!inventoryTryResult.getCode().equals(SUCC_CODE) || !inventoryTryResult.getData()) {
                throw new RuntimeException("库存冻结失败: " + inventoryTryResult.getMsg());
            }

            inventoryTrySuccess = true;
            log.info("库存服务Try成功");
            log.info("--- Try阶段完成 ---");

            // ========== Confirm 阶段 ==========
            log.info("--- Confirm阶段开始 ---");

            // 3. 订单服务 Confirm：确认订单
            confirmOrder(transactionId, orderBranchId);
            log.info("订单服务Confirm成功");

            // 4. 库存服务 Confirm：确认扣减库存
            Result<Boolean> inventoryConfirmResult = inventoryFeignClient.confirmFreeze(
                    transactionId, inventoryBranchId);

            if (!inventoryConfirmResult.getCode().equals(SUCC_CODE) || !inventoryConfirmResult.getData()) {
                throw new RuntimeException("库存确认扣减失败: " + inventoryConfirmResult.getMsg());
            }

            log.info("库存服务Confirm成功");
            log.info("--- Confirm阶段完成 ---");
            log.info("=== TCC分布式事务成功完成 ===");

            return order.getOrderNo();

        } catch (Exception e) {
            // ========== Cancel 阶段 ==========
            log.error("=== TCC事务异常，开始回滚 ===", e);
            log.info("--- Cancel阶段开始 ---");

            try {
                // 回滚订单
                if (order != null) {
                    cancelOrder(transactionId, orderBranchId);
                    log.info("订单服务Cancel成功");
                }

                // 回滚库存
                if (inventoryTrySuccess) {
                    Result<Boolean> inventoryCancelResult = inventoryFeignClient.cancelFreeze(
                            transactionId, inventoryBranchId);

                    if (inventoryCancelResult.getCode().equals(SUCC_CODE) && inventoryCancelResult.getData()) {
                        log.info("库存服务Cancel成功");
                    } else {
                        log.error("库存服务Cancel失败: {}", inventoryCancelResult.getMessage());
                    }
                }

                log.info("--- Cancel阶段完成 ---");
                log.info("=== TCC分布式事务回滚完成 ===");

            } catch (Exception cancelException) {
                log.error("Cancel阶段异常", cancelException);
            }

            throw new RuntimeException("创建订单失败: " + e.getMessage(), e);
        }
    }

    /**
     * Try阶段：创建预订单
     */
    private Order tryCreateOrder(String transactionId, String branchId, CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderNo("ORD_" + System.currentTimeMillis());
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setStatus("PENDING"); // 待确认状态
        order.setTransactionId(transactionId);
        order.setTccStatus(TccStatus.TRYING);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        orderMapper.insert(order);
        return order;
    }

    /**
     * Confirm阶段：确认订单
     */
    private void confirmOrder(String transactionId, String branchId) {
        LambdaUpdateWrapper<Order> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Order::getTransactionId, transactionId)
                .set(Order::getStatus, "CONFIRMED")
                .set(Order::getTccStatus, TccStatus.CONFIRMED)
                .set(Order::getUpdateTime, LocalDateTime.now());

        orderMapper.update(null, updateWrapper);
    }

    /**
     * Cancel阶段：取消订单
     */
    private void cancelOrder(String transactionId, String branchId) {
        LambdaUpdateWrapper<Order> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Order::getTransactionId, transactionId)
                .set(Order::getStatus, "CANCELED")
                .set(Order::getTccStatus, TccStatus.CANCELED)
                .set(Order::getUpdateTime, LocalDateTime.now());

        orderMapper.update(null, updateWrapper);
    }
}