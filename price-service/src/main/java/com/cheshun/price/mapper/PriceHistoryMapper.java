package com.cheshun.price.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.price.domain.entity.PriceHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 价格历史 Mapper
 */
@Mapper
public interface PriceHistoryMapper extends BaseMapper<PriceHistory> {
}