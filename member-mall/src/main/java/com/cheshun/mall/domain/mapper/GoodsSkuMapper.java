package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.ClsMarketEtcGoodsSku;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20251019
 */
@Mapper
@Repository
public interface GoodsSkuMapper extends BaseMapper<ClsMarketEtcGoodsSku> {

}