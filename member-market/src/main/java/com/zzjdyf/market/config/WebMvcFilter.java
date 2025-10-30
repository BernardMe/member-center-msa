package com.zzjdyf.market.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Slf4j
@WebFilter
@Component
public class WebMvcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequestWrapper request = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
        String msg = String.format("\nRequestId: %s\nClientIP: %s\nURI: %s\nMethod: %s\nHeaders: %s\nParams: %s\nBody: %s\nFormData: %s",
                request.getRequestId(), request.getClientIp(), request.getRequestURI(), request.getMethod(), request.getHeaders(),
                request.getParams(), request.getBody(), request.getFormData());
        log.info(msg);
        filterChain.doFilter(request, servletResponse);
    }
}
