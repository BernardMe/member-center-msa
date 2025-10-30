package com.zzjdyf.customer.config;

import com.baomidou.mybatisplus.core.enums.IEnum;
import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 阿隆
 */
public class EnumConverter<V extends Serializable, T extends IEnum<V>> implements Converter<V, T> {
    private final Map<V, T> enumMap = new HashMap<>();

    public EnumConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getValue(), e);
        }
    }

    @Override
    public T convert(V v) {
        T t = enumMap.get(v);
        if (t == null) {
            if (v instanceof String) {
                try {
                    int value = Integer.parseInt(v.toString());
                    t = enumMap.get(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (t == null) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型: value = " + v);
        }
        return t;
    }
}
