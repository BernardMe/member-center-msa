package com.zzjdyf.mall.domain.dao;

import com.zzjdyf.mall.domain.entity.EsSkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsGoodsSkuRepository extends ElasticsearchRepository<EsSkuInfo, String> {
    // 继承 ElasticsearchRepository，提供基本的 CRUD 操作
}
