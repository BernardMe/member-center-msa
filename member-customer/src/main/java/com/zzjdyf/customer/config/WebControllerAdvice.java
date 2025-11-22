package com.zzjdyf.customer.config;

import com.cheshun.common.tools.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 阿隆
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class WebControllerAdvice implements ResponseBodyAdvice<Object> {
    @ExceptionHandler(value = {Exception.class})
    public R handleException(HttpServletRequest request, Exception e) {
        log.error(String.format("%s", request.getRequestURI()), e);
        return R.error(e.getMessage());
    }

    @Override
    public boolean supports(@Nullable MethodParameter methodParameter, @Nullable Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  @Nullable MethodParameter methodParameter,
                                  @Nullable MediaType mediaType,
                                  @Nullable Class aClass,
                                  @Nullable ServerHttpRequest serverHttpRequest,
                                  @Nullable ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResponseEntity) {
            return o;
        }
        if (o instanceof R) {
            return o;
        }
        return R.ok().setData(o);
    }
}
