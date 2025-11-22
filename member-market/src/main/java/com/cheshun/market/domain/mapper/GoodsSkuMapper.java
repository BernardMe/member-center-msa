package com.cheshun.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.market.domain.entity.ClsMarketEtcGoodsSku;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20210719
 */
@Mapper
@Repository
public interface GoodsSkuMapper extends BaseMapper<ClsMarketEtcGoodsSku> {

}