package com.zzjdyf.mall.service;

import com.zzjdyf.mall.domain.entity.ESHotSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotSearchRepository extends ElasticsearchRepository<ESHotSearch, String> {
    // 继承 ElasticsearchRepository，提供基本的 CRUD 操作
}
