package com.cheshun.mall.domain.dao.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import org.redisson.api.RedissonClient;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public interface ICache<T> extends IJson {
    String CACHE_KEY_SEPARATOR = ":";

    /**
     * 获取RedissonClient
     *
     * @return RedissonClient
     */
    RedissonClient getRedissonClient();

    /**
     * 获取有效时长
     *
     * @return 有效时长
     */
    Duration getCacheDuration();

    /**
     * 获取T类型
     *
     * @return T类型
     */
    Class<T> getEntityClass();

    /**
     * 获取默认缓存Key
     *
     * @return 缓存Key
     */
    default String getDefaultCacheKeyPrefix() {
        return getCacheKeyPrefix("id");
    }

    /**
     * 获取缓存Key
     *
     * @param keyPrefix 缓存Key拼接的前缀
     * @param params    缓存Key拼接的参数
     * @return 缓存Key
     */
    default String getCacheKeyPrefix(String keyPrefix, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(getEntityClass().getSimpleName());
        if (StringUtils.hasText(keyPrefix)) {
            sb.append(CACHE_KEY_SEPARATOR);
            if (params != null && params.length > 0) {
                sb.append(String.format(keyPrefix, params));
            } else {
                sb.append(keyPrefix);
            }
        }
        if (params == null || params.length == 0) {
            sb.append(CACHE_KEY_SEPARATOR);
        }
        return sb.toString();
    }

    /**
     * 把对象保存到缓存
     *
     * @param entity 对象
     */
    void saveToCache(T entity);

    /**
     * 把对象从缓存删除
     *
     * @param entity 对象
     */
    default void deleteFromCache(T entity) {

    }

    /**
     * 把对象保存到缓存
     *
     * @param key 缓存Key
     * @param t   对象
     */
    default void saveToCache(String key, Object t) {
        Duration duration = getCacheDuration();
        if (duration == null) {
            // 默认保存7天
            duration = Duration.ofDays(7);
        }
        saveToCache(key, t, duration);
    }

    /**
     * 把对象保存到缓存
     *
     * @param key 缓存Key
     * @param t   对象
     * @param ttl 有效时长
     */
    default void saveToCache(String key, Object t, Duration ttl) {
        String json = objToJson(t);
        if (json == null) {
            return;
        }
        getRedissonClient().getBucket(key).set(json, ttl.getSeconds(), TimeUnit.SECONDS);
    }

    /**
     * 从缓存读取对象数据
     *
     * @param key    缓存Key
     * @param tClass 对象类型
     * @return 对象数据
     */
    default <S> S readFromCache(String key, Class<S> tClass) {
        Object obj = getRedissonClient().getBucket(key).get();
        if (obj == null) {
            return null;
        }
        return jsonToObj(obj.toString(), tClass);
    }

    /**
     * 从缓存读取对象数据
     *
     * @param key       缓存Key
     * @param reference 对象类型
     * @return 对象数据
     */
    default <S> S readFromCache(String key, TypeReference<S> reference) {
        Object obj = getRedissonClient().getBucket(key).get();
        if (obj == null) {
            return null;
        }
        return jsonToObj(obj.toString(), reference);
    }

    /**
     * 从缓存删除指定Key
     *
     * @param key 缓存Key
     */
    default void deleteFromCache(String key) {
        deleteFromCache(key, false);
    }

    /**
     * 从缓存删除指定Key
     *
     * @param key     缓存Key
     * @param pattern 是否是匹配删除
     */
    default void deleteFromCache(String key, boolean pattern) {
        if (pattern) {
            getRedissonClient().getKeys().deleteByPattern(key);
        } else {
            getRedissonClient().getKeys().delete(key);
        }
    }
}
