package com.zzjdyf.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgentUpperOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20211112
 */
@Mapper
@Repository
public interface AgentUpperOrderMapper extends BaseMapper<ClsMarketEtcAgentUpperOrder> {

    @Select("SELECT t.* FROM cls_market_etc.cls_market_etc_agent_upper_order t WHERE t.order_id = #{orderId} ")
    ClsMarketEtcAgentUpperOrder findOneByOrderId(@Param("orderId") Long orderId);

}
