package com.zzjdyf.market.domain.dao.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public interface IJson {
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 把对象转换为json
     *
     * @param t 对象
     * @return json
     */
    default String objToJson(Object t) {
        if (t instanceof String) {
            return t.toString();
        }
        if (t instanceof Number) {
            return t.toString();
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(t);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 把json转换为对象
     *
     * @param json   json
     * @param tClass 对象类型
     * @return 对象
     */
    default <S> S jsonToObj(String json, Class<S> tClass) {
        if (tClass == String.class) {
            return tClass.cast(json);
        }
        if (Number.class.isAssignableFrom(tClass)) {
            return tClass.cast(new BigDecimal(json).longValue());
        }
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 把json转换为对象
     *
     * @param json      json
     * @param reference 对象类型
     * @return 对象
     */
    default <S> S jsonToObj(String json, TypeReference<S> reference) {
        try {
            return OBJECT_MAPPER.readValue(json, reference);
        } catch (Exception ignored) {
        }
        return null;
    }
}
