package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.ClsMarketEtcAgentReturnGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20211118
 */
@Mapper
@Repository
public interface AgentReturnOrderMapper extends BaseMapper<ClsMarketEtcAgentReturnGoods> {
}
