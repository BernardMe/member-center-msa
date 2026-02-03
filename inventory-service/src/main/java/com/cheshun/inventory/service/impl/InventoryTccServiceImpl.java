package com.cheshun.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cheshun.inventory.entity.Inventory;
import com.cheshun.inventory.entity.InventoryFrozen;
import com.cheshun.inventory.mapper.InventoryFrozenMapper;
import com.cheshun.inventory.mapper.InventoryMapper;
import com.cheshun.inventory.service.InventoryTccService;
import com.cheshun.tcc.common.enums.TccStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 库存服务实现 - TCC 模式
 */
@Slf4j
@Service
public class InventoryTccServiceImpl implements InventoryTccService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private InventoryFrozenMapper inventoryFrozenMapper;

    /**
     * Try阶段：冻结库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean tryFreeze(String transactionId, String branchId, Long productId, Integer quantity) {
        log.info("Try阶段 - 开始冻结库存, transactionId={}, branchId={}, productId={}, quantity={}",
                transactionId, branchId, productId, quantity);

        // 1. 检查是否已经执行过（幂等性检查）
        LambdaQueryWrapper<InventoryFrozen> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InventoryFrozen::getTransactionId, transactionId)
                .eq(InventoryFrozen::getBranchId, branchId);
        InventoryFrozen existFrozen = inventoryFrozenMapper.selectOne(queryWrapper);

        if (existFrozen != null) {
            log.info("Try阶段 - 已执行过，直接返回成功");
            return true;
        }

        // 2. 查询库存信息
        LambdaQueryWrapper<Inventory> inventoryQuery = new LambdaQueryWrapper<>();
        inventoryQuery.eq(Inventory::getProductId, productId);
        Inventory inventory = inventoryMapper.selectOne(inventoryQuery);

        if (inventory == null) {
            log.error("Try阶段 - 商品不存在, productId={}", productId);
            return false;
        }

        if (inventory.getAvailableStock() < quantity) {
            log.error("Try阶段 - 库存不足, availableStock={}, needQuantity={}",
                    inventory.getAvailableStock(), quantity);
            return false;
        }

        // 3. 冻结库存（使用乐观锁）
        int updateCount = inventoryMapper.freezeStock(productId, quantity, inventory.getVersion());

        if (updateCount == 0) {
            log.error("Try阶段 - 冻结库存失败，可能是并发冲突");
            return false;
        }

        // 4. 记录冻结信息
        InventoryFrozen frozen = new InventoryFrozen();
        frozen.setTransactionId(transactionId);
        frozen.setBranchId(branchId);
        frozen.setProductId(productId);
        frozen.setFrozenQuantity(quantity);
        frozen.setStatus(TccStatus.TRYING);
        frozen.setCreateTime(LocalDateTime.now());
        frozen.setUpdateTime(LocalDateTime.now());

        inventoryFrozenMapper.insert(frozen);

        log.info("Try阶段 - 冻结库存成功");
        return true;
    }

    /**
     * Confirm阶段：确认扣减库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmFreeze(String transactionId, String branchId) {
        log.info("Confirm阶段 - 开始确认扣减库存, transactionId={}, branchId={}",
                transactionId, branchId);

        // 1. 查询冻结记录
        LambdaQueryWrapper<InventoryFrozen> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InventoryFrozen::getTransactionId, transactionId)
                .eq(InventoryFrozen::getBranchId, branchId);
        InventoryFrozen frozen = inventoryFrozenMapper.selectOne(queryWrapper);

        if (frozen == null) {
            log.error("Confirm阶段 - 冻结记录不存在");
            return false;
        }

        // 2. 幂等性检查
        if (frozen.getStatus() == TccStatus.CONFIRMED) {
            log.info("Confirm阶段 - 已确认过，直接返回成功");
            return true;
        }

        // 3. 确认扣减库存（从冻结库存中扣减）
        int updateCount = inventoryMapper.confirmStock(frozen.getProductId(), frozen.getFrozenQuantity());

        if (updateCount == 0) {
            log.error("Confirm阶段 - 确认扣减库存失败");
            return false;
        }

        // 4. 更新冻结记录状态
        LambdaUpdateWrapper<InventoryFrozen> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(InventoryFrozen::getId, frozen.getId())
                .set(InventoryFrozen::getStatus, TccStatus.CONFIRMED)
                .set(InventoryFrozen::getUpdateTime, LocalDateTime.now());

        inventoryFrozenMapper.update(null, updateWrapper);

        log.info("Confirm阶段 - 确认扣减库存成功");
        return true;
    }

    /**
     * Cancel阶段：取消冻结，恢复库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelFreeze(String transactionId, String branchId) {
        log.info("Cancel阶段 - 开始取消冻结, transactionId={}, branchId={}",
                transactionId, branchId);

        // 1. 查询冻结记录
        LambdaQueryWrapper<InventoryFrozen> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InventoryFrozen::getTransactionId, transactionId)
                .eq(InventoryFrozen::getBranchId, branchId);
        InventoryFrozen frozen = inventoryFrozenMapper.selectOne(queryWrapper);

        if (frozen == null) {
            log.info("Cancel阶段 - 冻结记录不存在，可能Try阶段就失败了，直接返回成功");
            return true;
        }

        // 2. 幂等性检查
        if (frozen.getStatus() == TccStatus.CANCELED) {
            log.info("Cancel阶段 - 已取消过，直接返回成功");
            return true;
        }

        // 3. 如果已经确认过，不能再取消
        if (frozen.getStatus() == TccStatus.CONFIRMED) {
            log.error("Cancel阶段 - 库存已确认扣减，无法取消");
            return false;
        }

        // 4. 取消冻结，恢复库存
        int updateCount = inventoryMapper.cancelFreeze(frozen.getProductId(), frozen.getFrozenQuantity());

        if (updateCount == 0) {
            log.error("Cancel阶段 - 取消冻结失败");
            return false;
        }

        // 5. 更新冻结记录状态
        LambdaUpdateWrapper<InventoryFrozen> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(InventoryFrozen::getId, frozen.getId())
                .set(InventoryFrozen::getStatus, TccStatus.CANCELED)
                .set(InventoryFrozen::getUpdateTime, LocalDateTime.now());

        inventoryFrozenMapper.update(null, updateWrapper);

        log.info("Cancel阶段 - 取消冻结成功");
        return true;
    }
}