package com.cheshun.order.feign;

import com.cheshun.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存服务 Feign 客户端
 */
@FeignClient(name = "inventory-service", url = "http://localhost:28081/inventory-service")
public interface InventoryFeignClient {

    /**
     * Try阶段：冻结库存
     */
    @PostMapping("/inventory/try")
    Result<Boolean> tryFreeze(@RequestParam("transactionId") String transactionId,
                              @RequestParam("branchId") String branchId,
                              @RequestParam("productId") Long productId,
                              @RequestParam("quantity") Integer quantity);

    /**
     * Confirm阶段：确认扣减库存
     */
    @PostMapping("/inventory/confirm")
    Result<Boolean> confirmFreeze(@RequestParam("transactionId") String transactionId,
                                  @RequestParam("branchId") String branchId);

    /**
     * Cancel阶段：取消冻结
     */
    @PostMapping("/inventory/cancel")
    Result<Boolean> cancelFreeze(@RequestParam("transactionId") String transactionId,
                                 @RequestParam("branchId") String branchId);
}