package com.zzjdyf.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.market.domain.entity.ClsMarketEtcEsTrade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 阿隆
 * Created on 2021/7/29 6:29 下午.
 */
@Mapper
@Repository
public interface EsTradeMapper extends BaseMapper<ClsMarketEtcEsTrade> {
}