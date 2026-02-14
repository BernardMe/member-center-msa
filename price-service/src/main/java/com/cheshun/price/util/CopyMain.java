package com.cheshun.price.util;

import com.cheshun.price.domain.entity.HardwarePrice;
import com.cheshun.price.domain.po.HardwarePricePO;
import com.tools.utils.CopyUtils;

public class CopyMain {


    public static void main(String[] args) {
        HardwarePricePO vo = new HardwarePricePO();
        //vo.setName("张三");
        //vo.setAge(25);

        HardwarePrice entity = new HardwarePrice();

        System.out.println("--- 执行赋值并生成 Setter 代码 ---");
        CopyUtils.copyPropertiesAndGenerateSetters(vo, entity);

        System.out.println("\n--- 结果 ---");
        System.out.println(entity);
    }
}
