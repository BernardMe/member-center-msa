package com.cheshun.mall.domain.dao.interfaces;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

/**
 * Dap层ES接口
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public interface IElasticSearch {

    ElasticsearchOperations getElasticsearchOperations();
    /**
     * 获取ElasticsearchTemplate
     *
     * @return ElasticsearchTemplate
     */
    ElasticsearchRestTemplate getElasticsearchTemplate();

    /**
     * 把对象保存到ES
     *
     * @param t 对象
     * @return 保存结果
     */
    default <T> boolean saveToES(T t) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(t).build();
            IndexCoordinates indexCoordinates = getElasticsearchOperations().getIndexCoordinatesFor(t.getClass());

            getElasticsearchTemplate().index(indexQuery, indexCoordinates);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从ES删除指定id
     *
     * @param tClass 对象类型
     * @param id     id
     */
    default <T> void deleteFromES(Class<T> tClass, String id) {
        try {
            IndexCoordinates indexCoordinates = getElasticsearchOperations().getIndexCoordinatesFor(tClass);

            if (getElasticsearchTemplate().indexOps(indexCoordinates).exists()) {
                getElasticsearchTemplate().delete(id, indexCoordinates);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
