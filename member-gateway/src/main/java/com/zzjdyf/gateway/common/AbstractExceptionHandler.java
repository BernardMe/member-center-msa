package com.zzjdyf.gateway.common;



import com.zzjdyf.common.exception.RRException;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xueqing wang on 2021/4/27 11:01
 */
public abstract class AbstractExceptionHandler {

    private static final String DEFAULT_ERROR_CODE = "500";

    protected String formatMessage(Throwable ex) {
        String errorMessage = null;
        if (ex instanceof NotFoundException) {
            String reason = ((NotFoundException) ex).getReason();
            errorMessage = reason;
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            errorMessage = responseStatusException.getMessage();
        } else if(ex instanceof RRException){
            errorMessage = ((RRException) ex).getMsg();
        }else{
            errorMessage = ex.getMessage();
        }
        return errorMessage;
    }

    protected Map<String, Object> buildErrorMap(String errorMessage) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", DEFAULT_ERROR_CODE);
        resMap.put("message", errorMessage);
        resMap.put("payload", null);
        return resMap;
    }

}
