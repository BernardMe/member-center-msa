package com.zzjdyf.customer.config;

import com.baomidou.mybatisplus.core.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/3/25 11:09.
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, IEnum<String>> {
    private final Map<Class<?>, Converter<String, ?>> CONVERTER_LIST = new HashMap<>();

    @Override
    public <T extends IEnum<String>> Converter<String, T> getConverter(Class<T> aClass) {
        if (!CONVERTER_LIST.containsKey(aClass)) {
            Converter<String, T> converter = new EnumConverter<>(aClass);
            CONVERTER_LIST.put(aClass, converter);
            return converter;
        }
        return (Converter<String, T>) CONVERTER_LIST.get(aClass);
    }
}
