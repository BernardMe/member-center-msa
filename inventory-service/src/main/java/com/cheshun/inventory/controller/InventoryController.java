package com.cheshun.inventory.controller;

import com.cheshun.inventory.service.InventoryTccService;

import com.result.Result;
import com.tools.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.result.Result.ERR_CODE;

/**
 * 库存服务控制器
 */
@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryTccService inventoryTccService;

    /**
     * Try阶段：冻结库存
     */
    @PostMapping("/try")
    public Result<Boolean> tryFreeze(@RequestParam String transactionId,
                                     @RequestParam String branchId,
                                     @RequestParam Long productId,
                                     @RequestParam Integer quantity) {
        try {
            boolean success = inventoryTccService.tryFreeze(transactionId, branchId, productId, quantity);
            return success ? ResultUtil.result(true) : ResultUtil.result(ERR_CODE, "库存冻结失败");
        } catch (Exception e) {
            log.error("Try阶段异常", e);
            return Result.error("库存冻结异常：" + e.getMessage());
        }
    }

    /**
     * Confirm阶段：确认扣减库存
     */
    @PostMapping("/confirm")
    public Result<Boolean> confirmFreeze(@RequestParam String transactionId,
                                         @RequestParam String branchId) {
        try {
            boolean success = inventoryTccService.confirmFreeze(transactionId, branchId);
            return success ? ResultUtil.result(true) : ResultUtil.result(ERR_CODE, "库存确认扣减失败");
        } catch (Exception e) {
            log.error("Confirm阶段异常", e);
            return Result.error("库存确认扣减异常：" + e.getMessage());
        }
    }

    /**
     * Cancel阶段：取消冻结
     */
    @PostMapping("/cancel")
    public Result<Boolean> cancelFreeze(@RequestParam String transactionId,
                                        @RequestParam String branchId) {
        try {
            boolean success = inventoryTccService.cancelFreeze(transactionId, branchId);
            return success ? ResultUtil.result(true) : ResultUtil.result(ERR_CODE, "库存取消冻结失败");
        } catch (Exception e) {
            log.error("Cancel阶段异常", e);
            return Result.error("库存取消冻结异常：" + e.getMessage());
        }
    }
}