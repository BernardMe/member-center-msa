//package com.zzjdyf.common.result;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiParam;
//import org.jetbrains.annotations.NotNull;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
//@ApiModel
//public class CustomApiResult<T> implements Serializable {
//    private static final long serialVersionUID = -5183069904956760192L;
//    private @NotNull
//    @ApiParam(
//    value = "状态：true - 成功, false - 失败",
//    required = true
//) boolean status = true;
//    private @ApiParam("错误代码") String errorCode = "";
//    private @ApiParam("错误信息") String message;
//    private @ApiParam("响应数据") T data;
//
//    public static <T> CustomApiResult<T> success(T data) {
//        CustomApiResult<T> result = new CustomApiResult<T>();
//        result.setStatus(true);
//        result.setData(data);
//        return result;
//    }
//
//    public static <T> CustomApiResult<T> fail(String errorCode, String errorMessage) {
//        CustomApiResult<T> result = new CustomApiResult<T>();
//        result.setStatus(false);
//        result.setErrorCode(errorCode);
//        result.setMessage(errorMessage);
//        return result;
//    }
//
//    public boolean isStatus() {
//        return this.status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public String getErrorCode() {
//        return this.errorCode;
//    }
//
//    public void setErrorCode(String errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return this.data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//}