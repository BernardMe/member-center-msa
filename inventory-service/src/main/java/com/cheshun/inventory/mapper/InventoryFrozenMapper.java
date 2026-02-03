package com.cheshun.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.inventory.entity.InventoryFrozen;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存冻结记录 Mapper
 */
@Mapper
public interface InventoryFrozenMapper extends BaseMapper<InventoryFrozen> {
}