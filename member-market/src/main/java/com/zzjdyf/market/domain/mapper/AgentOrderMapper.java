package com.zzjdyf.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentOrder;
import com.zzjdyf.market.domain.entity.enums.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20210719
 */
@Mapper
@Repository
public interface AgentOrderMapper extends BaseMapper<ClsMarketEtcAgentOrder> {
    /**
     * 扣减申请的卡数量和设备数量并更新订单状态
     *
     * @param id             id
     * @param cardQuantity   卡数量增量
     * @param deviceQuantity 设备数量增量
     * @param status         订单状态
     * @param operator       操作人员
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent_order set " +
            "status = #{status}, " +
            "operator = #{operator}, " +
            "update_time = NOW() " +
            "where id = #{id} " )
    int decreaseQuantityOfCardAndDevice(Long id, Integer cardQuantity, Integer deviceQuantity, OrderStatus status, String operator);
}