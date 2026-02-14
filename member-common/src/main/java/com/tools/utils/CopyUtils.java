package com.tools.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CopyUtils {

    /**
     * 将 VO 的属性值赋值给 Entity，并打印生成的 Setter 代码
     */
    public static <V, E> void copyPropertiesAndGenerateSetters(V vo, E entity) {
        Class<?> voClass = vo.getClass();
        Class<?> entityClass = entity.getClass();

        Field[] voFields = voClass.getDeclaredFields();

        for (Field voField : voFields) {
            voField.setAccessible(true);
            try {
                Object value = voField.get(vo);
                //if (value == null) continue; // 跳过空值

                // 寻找 entity 中同名的字段
                Field entityField = entityClass.getDeclaredField(voField.getName());
                entityField.setAccessible(true);

                // 类型匹配，进行赋值
                if (entityField.getType().isAssignableFrom(voField.getType())) {
                    entityField.set(entity, value);

                    // 生成并打印 setter 代码
                    String setterName = "set" + voField.getName().substring(0, 1).toUpperCase()
                            + voField.getName().substring(1);
                    System.out.println("po." + setterName + "(item.get"
                            + voField.getName().substring(0, 1).toUpperCase()
                            + voField.getName().substring(1) + "());");
                }
            } catch (NoSuchFieldException e) {
                // 如果entity没有该字段，跳过
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
