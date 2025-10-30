package com.zzjdyf.mall.domain.dao.interfaces;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public interface IDao<T> {
    /**
     * 保存数据
     *
     * @param entity 数据
     * @return 数据(带id)
     */
    T save(T entity);
}
