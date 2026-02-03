package com.cheshun.inventory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheshun.tcc.common.enums.TccStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存冻结记录
 */
@Data
@TableName("t_inventory_frozen")
public class InventoryFrozen {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 全局事务ID
     */
    private String transactionId;

    /**
     * 分支事务ID
     */
    private String branchId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 冻结数量
     */
    private Integer frozenQuantity;

    /**
     * TCC状态
     */
    private TccStatus status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}