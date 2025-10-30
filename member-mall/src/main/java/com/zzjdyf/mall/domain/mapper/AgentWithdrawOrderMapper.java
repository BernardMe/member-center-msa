package com.zzjdyf.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgentWithdrawOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 阿隆
 * Created on 2021/8/2 3:43 下午.
 */
@Mapper
@Repository
public interface AgentWithdrawOrderMapper extends BaseMapper<ClsMarketEtcAgentWithdrawOrder> {
}
