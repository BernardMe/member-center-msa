package com.cheshun.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.inventory.entity.Inventory;
import com.cheshun.inventory.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 库存 Mapper
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

    /**
     * 冻结库存
     */
    @Update("UPDATE t_inventory SET available_stock = available_stock - #{quantity}, " +
            "frozen_stock = frozen_stock + #{quantity}, " +
            "update_time = NOW(), version = version + 1 " +
            "WHERE product_id = #{productId} AND available_stock >= #{quantity} AND version = #{version}")
    int freezeStock(@Param("productId") Long productId,
                    @Param("quantity") Integer quantity,
                    @Param("version") Integer version);

    /**
     * 确认库存扣减（从冻结库存中扣减）
     */
    @Update("UPDATE t_inventory SET frozen_stock = frozen_stock - #{quantity}, " +
            "total_stock = total_stock - #{quantity}, " +
            "update_time = NOW(), version = version + 1 " +
            "WHERE product_id = #{productId} AND frozen_stock >= #{quantity}")
    int confirmStock(@Param("productId") Long productId,
                     @Param("quantity") Integer quantity);

    /**
     * 取消冻结（恢复可用库存）
     */
    @Update("UPDATE t_inventory SET available_stock = available_stock + #{quantity}, " +
            "frozen_stock = frozen_stock - #{quantity}, " +
            "update_time = NOW(), version = version + 1 " +
            "WHERE product_id = #{productId} AND frozen_stock >= #{quantity}")
    int cancelFreeze(@Param("productId") Long productId,
                     @Param("quantity") Integer quantity);
}