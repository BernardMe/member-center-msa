package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.GoodsInfos;

import java.util.List;

public interface GoodsInfosMapper extends BaseMapper<GoodsInfos> {


    List<GoodsInfos> selectImportGoodsForSku();
}




