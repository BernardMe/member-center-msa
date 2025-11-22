package com.cheshun.market.config;

import com.baomidou.mybatisplus.core.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, IEnum<String>> {
    private final Map<Class<?>, Converter<String, ?>> CONVERTER_LIST = new HashMap<>();

    @Override
    public <T extends IEnum<String>> Converter<String, T> getConverter(@NonNull Class<T> aClass) {
        if (!CONVERTER_LIST.containsKey(aClass)) {
            Converter<String, T> converter = new EnumConverter<>(aClass);
            CONVERTER_LIST.put(aClass, converter);
            return converter;
        }
        return (Converter<String, T>) CONVERTER_LIST.get(aClass);
    }

    public static class EnumConverter<V extends Serializable, T extends IEnum<V>> implements Converter<V, T> {
        private final Map<V, T> enumMap = new HashMap<>();

        public EnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.getValue(), e);
            }
        }

        @Override
        public T convert(V v) {
            if (v instanceof String && !StringUtils.hasText(v.toString())) {
                return null;
            }
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
}
