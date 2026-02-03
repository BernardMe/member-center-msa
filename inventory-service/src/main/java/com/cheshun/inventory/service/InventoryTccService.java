package com.cheshun.inventory.service;

/**
 * 库存服务接口 - TCC 模式
 */
public interface InventoryTccService {

    /**
     * Try阶段：冻结库存
     * @param transactionId 全局事务ID
     * @param branchId 分支事务ID
     * @param productId 商品ID
     * @param quantity 数量
     * @return 是否成功
     */
    boolean tryFreeze(String transactionId, String branchId, Long productId, Integer quantity);

    /**
     * Confirm阶段：确认扣减库存
     * @param transactionId 全局事务ID
     * @param branchId 分支事务ID
     * @return 是否成功
     */
    boolean confirmFreeze(String transactionId, String branchId);

    /**
     * Cancel阶段：取消冻结，恢复库存
     * @param transactionId 全局事务ID
     * @param branchId 分支事务ID
     * @return 是否成功
     */
    boolean cancelFreeze(String transactionId, String branchId);
}