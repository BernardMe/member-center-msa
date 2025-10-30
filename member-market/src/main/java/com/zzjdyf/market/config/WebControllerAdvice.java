package com.zzjdyf.market.config;

import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.common.tools.utils.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class WebControllerAdvice implements ResponseBodyAdvice<Object> {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception exception) {
        logException(request, exception, LogLevel.ERROR);
        return ResponseEntity.ok().body(R.error(BizCodeEnum.UNKNOW_EXCEPTION));
    }

    @ExceptionHandler(value = {RRException.class})
    public ResponseEntity<?> handleException(HttpServletRequest request, RRException exception) {
        logException(request, exception, LogLevel.ERROR);
        return ResponseEntity.ok().body(R.error(exception.getCode(), exception.getMsg()));
    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<?> handle(HttpServletRequest request, BindException exception) {
        logException(request, exception, LogLevel.DEBUG);
        return handleViolationFieldError(exception.getFieldErrors());
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(HttpServletRequest request, MethodArgumentNotValidException exception) {
        logException(request, exception, LogLevel.DEBUG);
        return handleViolationFieldError(exception.getBindingResult().getFieldErrors());
    }

    private ResponseEntity<?> handleViolationFieldError(List<FieldError> fieldErrorList) {
        for (FieldError fieldError : fieldErrorList) {
            return ResponseEntity.ok().body(R.error(fieldError.getDefaultMessage()));
        }
        return ResponseEntity.ok().body(R.error(BizCodeEnum.VAILD_EXCEPTION));
    }

    private void logException(HttpServletRequest httpServletRequest, Exception exception, LogLevel logLevel) {
        String msg;
        if (httpServletRequest instanceof HttpServletRequestWrapper) {
            HttpServletRequestWrapper request = (HttpServletRequestWrapper) httpServletRequest;
            msg = String.format("\nRequestId: %s\nClientIP: %s\nURI: %s\nMethod: %s\nHeaders: %s\nParams: %s\nBody: %s\nFormData: %s",
                    request.getRequestId(), request.getClientIp(), request.getRequestURI(), request.getMethod(), request.getHeaders(),
                    request.getParams(), request.getBody(), request.getFormData());
        } else {
            msg = String.format("\nURI: %s\nMethod: %s", httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        }
        switch (logLevel) {
            case TRACE:
                log.trace(msg, exception);
                break;
            case DEBUG:
                log.debug(msg, exception);
                break;
            case INFO:
                log.info(msg, exception);
                break;
            case WARN:
                log.warn(msg, exception);
                break;
            case ERROR:
            case FATAL:
                log.error(msg, exception);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean supports(@Nullable MethodParameter methodParameter, @Nullable Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @Nullable MethodParameter methodParameter,
                                  @Nullable MediaType mediaType,
                                  @Nullable Class aClass,
                                  @Nullable ServerHttpRequest serverHttpRequest,
                                  @Nullable ServerHttpResponse serverHttpResponse) {
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        if (request != null) {
            if (request.getServletRequest().getServletPath().contains("swagger") ||
                    (body != null && body.getClass().isAssignableFrom(Json.class))) {
                return body;
            }
            try {
                if (request.getServletRequest() instanceof HttpServletRequestWrapper) {
                    HttpServletRequestWrapper requestWrapper = (HttpServletRequestWrapper) request.getServletRequest();
                    String msg = String.format("\nRequestId: %s\nClientIP: %s\nURI: %s\nMethod: %s\nHeaders: %s\nParams: %s\nBody: %s\nFormData: %s\nresponse: %s",
                            requestWrapper.getRequestId(), requestWrapper.getClientIp(), requestWrapper.getRequestURI(), requestWrapper.getMethod(), requestWrapper.getHeaders(),
                            requestWrapper.getParams(), requestWrapper.getBody(), requestWrapper.getFormData(), OBJECT_MAPPER.writeValueAsString(body));
                    log.info(msg);
                } else {
                    String msg = String.format("\nURI: %s\nMethod: %s\nresponse: %s", request.getServletRequest().getRequestURI(), request.getMethod(), OBJECT_MAPPER.writeValueAsString(body));
                    log.info(msg);
                }
            } catch (Exception e) {
                log.error("beforeBodyWrite异常", e);
            }
        }
        Object result = body;
        if (!(body instanceof ResponseEntity) && !(body instanceof R)) {
            result = R.ok(body);
        }
        return result;
    }
}
