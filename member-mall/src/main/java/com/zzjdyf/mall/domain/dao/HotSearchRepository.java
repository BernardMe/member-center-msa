package com.zzjdyf.mall.domain.dao;

import com.zzjdyf.mall.domain.entity.EsHotSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotSearchRepository extends ElasticsearchRepository<EsHotSearch, String> {
    // 继承 ElasticsearchRepository，提供基本的 CRUD 操作
}
