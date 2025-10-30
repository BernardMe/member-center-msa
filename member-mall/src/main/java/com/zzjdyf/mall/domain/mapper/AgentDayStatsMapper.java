package com.zzjdyf.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgentDayStats;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 阿隆
 * Created on 2021/9/29 2:44 下午.
 */
@Mapper
@Repository
public interface AgentDayStatsMapper extends BaseMapper<ClsMarketEtcAgentDayStats> {
}
