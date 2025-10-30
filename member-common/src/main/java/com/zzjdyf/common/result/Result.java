package com.zzjdyf.common.result;

import com.zzjdyf.common.api.web.http.common.UsualResult;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.text.MessageFormat;

@Data
public class Result<T> implements Cloneable, Serializable {
    private String code;   // 状态码（可配置）
    private String msg;     // 消息（支持模板占位符）
    private T data;        // 数据
//    private Map<String, Object> extra; // 扩展字段（动态追加）

    // ------------------- 静态配置（支持运行时修改）-------------------
    public static String ERR_CODE = "300";        // 业务异常状态码
    public static String SUCC_CODE = "200";       // 默认业务状态码
    private static String SUCCESS_CODE = "1";      // 默认成功码
    private static String ERROR_CODE = "0";        // 默认失败码
    private static String SUCCESS_MSG = "操作成功";
    private static String ERROR_MSG_TEMPLATE = "错误：{0}"; // 支持占位符

    /**
     * 动态修改配置（可选）
     */
    public static void config(String successCode, String errorCode, String successMsg, String errorMsgTemplate) {
        SUCCESS_CODE = successCode;
        ERROR_CODE = errorCode;
        SUCCESS_MSG = successMsg;
        ERROR_MSG_TEMPLATE = errorMsgTemplate;
    }

    // ------------------- 兼容旧版方法 -------------------
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = SUCCESS_CODE;
        result.msg = SUCCESS_MSG;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = ERROR_CODE;
        result.msg = MessageFormat.format(ERROR_MSG_TEMPLATE, msg); // 支持模板
        return result;
    }

    // ------------------- 新增扩展方法 -------------------
    /**
     * 自定义状态码和消息
     */
    public static <T> Result<T> ok(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T>  Result<T> error(String code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    /**
     * 添加扩展字段（链式调用）
     */
//    public Result<T> addExtra(String key, Object value) {
//        if (this.extra == null) {
//            this.extra = new HashMap<>();
//        }
//        this.extra.put(key, value);
//        return this;
//    }

    /**
     * 模块编号
     */
    private String modelCode;
    /**
     * 结果编号
     */
    private Integer optCode;
    /**
     * 唯一编号:模块编号--状态编号
     */
    private String uniqueCode;
    /**
     * 操作结果
     */
    private Boolean success;
    /**
     * 结果信息
     */
    private String message;

    public Result() {
    }

    public Result(Result opt) {
        if (opt == null) {
            opt = UsualResult.SUCCESS;
        }
        this.code = opt.getCode();
        this.modelCode = opt.getModelCode();
        this.optCode = opt.getOptCode();
        this.success = opt.getSuccess();
        this.message = opt.getMessage();
        generateUniqueCode();
    }

    public Result(String code, String message, T data) {
        if(code.equals(SUCC_CODE)) {
            this.code = SUCC_CODE;
            this.msg = SUCCESS_MSG;
            this.data = data;
        } else {
            this.code = ERR_CODE;
            this.msg = MessageFormat.format(ERROR_MSG_TEMPLATE, message);
            this.data = data;
        }
    }

    public Result(String code, Integer optCode, Boolean success, String message) {
        this.code = code;
        this.optCode = optCode;
        this.success = success;
        this.message = message;
        generateUniqueCode();
    }

    public Result(Integer optCode, Boolean success, String message) {
        this(String.valueOf(optCode), optCode, success, message);
    }

    public void generateUniqueCode() {
        if(!ObjectUtils.isEmpty(modelCode)) {
            uniqueCode = modelCode + ":" + optCode;
        }else {
            uniqueCode = String.valueOf(optCode);
        }
    }

    public String getModelCode() {
        return modelCode;
    }

    public Integer getOptCode() {
        return optCode;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result [modelCode=" + modelCode + ", optCode=" + optCode + ", uniqueCode=" + uniqueCode
                + ", success=" + success + ", message=" + message + "]";
    }

    public Result cloneResult() {
        try {
            return (Result) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return UsualResult.FAILURE;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setOptCode(Integer optCode) {
        this.optCode = optCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
