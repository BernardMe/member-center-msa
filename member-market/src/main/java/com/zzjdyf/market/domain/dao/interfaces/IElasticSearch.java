package com.zzjdyf.market.domain.dao.interfaces;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

/**
 * Dap层ES接口
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public interface IElasticSearch {

    /**
     * 获取ElasticsearchTemplate
     *
     * @return ElasticsearchTemplate
     */
    ElasticsearchTemplate getElasticsearchTemplate();

    /**
     * 把对象保存到ES
     *
     * @param t 对象
     * @return 保存结果
     */
    default <T> boolean saveToES(T t) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(t).build();
            getElasticsearchTemplate().index(indexQuery);
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
            if (getElasticsearchTemplate().indexExists(tClass)) {
                getElasticsearchTemplate().delete(tClass, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
