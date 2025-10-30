package com.zzjdyf.market.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
    private Map<String, String> headerMap;
    private String requestId;
    private String params;
    private String body;
    private byte[] buffer = new byte[0];
    private String formData;
    private String clientIp;

    public HttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 请求IP
            this.clientIp = getClientIp(request);
            // 请求ID
            this.requestId = UUID.randomUUID().toString();
            // 请求头
            initHeader();
            /*
             * 根据Servlet规范，如果同时满足下列条件，则请求体(Entity)中的表单数据，将被填充到request的parameter集合中（request.getParameter系列方法可以读取相关数据）：
             * 1 这是一个HTTP/HTTPS请求
             * 2 请求方法是POST（querystring无论是否POST都将被设置到parameter中）
             * 3 请求的类型（Content-Type头）是application/x-www-form-urlencoded
             * 4 Servlet调用了getParameter系列方法
             */
            // 请求参数
            params = objectMapper.writeValueAsString(request.getParameterMap());
            InputStream is = request.getInputStream();
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                byte[] buff = new byte[1024];
                int read;
                while ((read = is.read(buff)) > 0) {
                    baos.write(buff, 0, read);
                }
                this.buffer = baos.toByteArray();
            }
            if (!HttpMethod.GET.matches(request.getMethod())) {
                // 请求体
                if (this.buffer.length > 0) {
                    this.body = new String(this.buffer, StandardCharsets.UTF_8);
                }
                String multipartName = "multipart";
                if (StringUtils.hasText(request.getContentType()) && request.getContentType().contains(multipartName)) {
                    Collection<Part> parts = request.getParts();
                    if (parts != null && parts.size() > 0) {
                        List<Map<String, Object>> formList = new ArrayList<>();
                        for (Part part : parts) {
                            Map<String, Object> partData = new HashMap<>();
                            partData.put("contentType", part.getContentType());
                            partData.put("name", part.getName());
                            partData.put("fileName", part.getSubmittedFileName());
                            partData.put("size", part.getSize());
                            if (part instanceof FilePart) {
                                FilePart filePart = (FilePart) part;
                                partData.put("fileName", filePart.filename());
                            }
                            formList.add(partData);
                        }
                        this.formData = objectMapper.writeValueAsString(formList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public String getParams() {
        return params;
    }

    public String getBody() {
        return body;
    }

    public String getFormData() {
        return formData;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getHeaders() {
        try {
            return new ObjectMapper().writeValueAsString(this.headerMap);
        } catch (Exception ignored) {
        }
        return null;
    }

    @JsonIgnore
    private void initHeader() {
        this.headerMap = new HashMap<>();
        Enumeration<String> stringEnumeration = getHeaderNames();
        while (stringEnumeration.hasMoreElements()) {
            String name = stringEnumeration.nextElement();
            this.headerMap.put(name, super.getHeader(name));
        }
    }

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (!StringUtils.hasText(value) && headerMap.containsKey(name)) {
            value = headerMap.get(name);
        }
        return value;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            values = Collections.singletonList(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }

    @Override
    public ServletInputStream getInputStream() {
        return new BufferedServletInputStream(this.buffer);
    }

    public static class BufferedServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream inputStream;
        private boolean finished = false;

        public BufferedServletInputStream(byte[] buffer) {
            this.inputStream = new ByteArrayInputStream(buffer);
        }

        @Override
        public int available() {
            return inputStream.available();
        }

        @Override
        public int read() {
            int data = inputStream.read();
            if (data == -1) {
                this.finished = true;
            }
            return data;
        }

        @Override
        public int read(@NonNull byte[] b, int off, int len) {
            return inputStream.read(b, off, len);
        }

        @Override
        public boolean isFinished() {
            return this.finished;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }

    /**
     * 获取IP地址
     *
     * @return request发起客户端的IP地址
     */
    @JsonIgnore
    private String getClientIp(HttpServletRequest request) {
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";
        if (StringUtils.hasText(xFor) && !unknown.equalsIgnoreCase(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xIp;
        if (StringUtils.hasText(xFor) && !unknown.equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (!StringUtils.hasText(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!StringUtils.hasText(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!StringUtils.hasText(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        return xFor;
    }
}
