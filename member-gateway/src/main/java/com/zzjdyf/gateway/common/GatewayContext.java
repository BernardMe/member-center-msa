package com.zzjdyf.gateway.common;

/**
 * Created by xueqing wang on 2021/4/27 17:17
 */
import org.springframework.util.MultiValueMap;

/**
 * 网关上下文
 */
public class GatewayContext {

    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";

    /**
     * cache json body
     */
    private String cacheBody;
    /**
     * cache formdata
     */
    private MultiValueMap<String, Object> formData;
    /**
     * cache reqeust path
     */
    private String path;


    public String getCacheBody() {
        return cacheBody;
    }

    public void setCacheBody(String cacheBody) {
        this.cacheBody = cacheBody;
    }

    public MultiValueMap<String, Object> getFormData() {
        return formData;
    }

    public void setFormData(MultiValueMap<String, Object> formData) {
        this.formData = formData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

