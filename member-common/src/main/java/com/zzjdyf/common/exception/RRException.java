/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zzjdyf.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author Mark sunlightcs@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 给前端返回的错误消息
     */
    private String msg;
    /**
     * 错误码
     */
    private int code = 500;
    /**
     * 按钮行为
     */
    private int action = 0;

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, String detailError) {
        super(detailError);
        this.msg = msg;
    }

    public RRException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode() + "(" + codeEnum.getMsg() + ")");
        this.msg = codeEnum.getMsg();
        this.code = codeEnum.getCode();
    }

    public RRException(BizCodeEnum codeEnum, String detailError) {
        super(codeEnum.getCode() + "(" + codeEnum.getMsg() + ")" + ":" + detailError);
        this.msg = codeEnum.getMsg();
        this.code = codeEnum.getCode();
    }

    public RRException(BizCodeEnum codeEnum, Throwable e) {
        super(codeEnum.getCode() + "(" + codeEnum.getMsg() + ")", e);
        this.msg = codeEnum.getMsg();
        this.code = codeEnum.getCode();
    }

    public RRException withAction(int action) {
        this.action = action;
        return this;
    }
}
