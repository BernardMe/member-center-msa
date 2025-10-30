package com.zzjdyf.gateway.util;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by xueqing wang on 2021/5/17 13:51
 */
@Slf4j
public class AESContextHolder {

    private AESContextHolder() {
    }

    private static final ThreadLocal<Mode> threadLocal = new ThreadLocal<>();


    public static Mode getAESModeAndPadding() {
        Mode  mode= threadLocal.get();
        if (mode != null) {
            return mode;
        } else {
            return null;
        }
    }

    public static void setAESModeAndPadding(Mode mode) {
        threadLocal.set(mode);
    }

}
