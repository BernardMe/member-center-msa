package com.zzjdyf.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcGoodsSku;
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