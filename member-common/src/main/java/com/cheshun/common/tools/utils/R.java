/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.cheshun.common.tools.utils;

import com.cheshun.common.exception.BizCodeEnum;
import lombok.Data;
import org.apache.http.HttpStatus;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class R<T> {
    private int code = HttpStatus.SC_OK;
    private String message = "success";
    private int action = 0;
    private T data;
    private String codeStr;

    public R() {
    }

    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(String codeStr, String message) {
        this.codeStr = codeStr;
        this.message = message;
    }

    public R(T data) {
        this.data = data;
    }

    public R(int code, String message, int action, T data) {
        this.code = code;
        this.message = message;
        this.action = action;
        this.data = data;
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data);
    }

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> ok(String codeStr, String message) {
        return new R<>(codeStr, message);
    }

    public static <T> R<T> error(BizCodeEnum code) {
        return new R<>(code.getCode(), code.getMsg());
    }

    public static <T> R<T> error(int code, String message) {
        return new R<>(code, message);
    }

    public static <T> R<T> error() {
        return new R<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static <T> R<T> error(String message) {
        return new R<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
    }

    public static <T> R<T> error(String codeStr, String message) {
        return new R<>(codeStr, message);
    }

    public static <T> R<T> error(int code, String message, int action) {
        return new R<>(code, message, action, null);
    }

    public boolean isOk() {
        return this.code == HttpStatus.SC_OK;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }
}
