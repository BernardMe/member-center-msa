package com.zzjdyf.auth.strategy;

import com.zzjdyf.auth.service.AuthVerifyService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xueqing wang on 2021/4/22 11:46
 */
@Component
public class AuthVerifyStrategy {

    @Resource
    private List<AuthVerifyService> authVerifyServiceList;


    public AuthVerifyService getAuthVerifyService(String type) {

        for (AuthVerifyService authVerifyService : authVerifyServiceList) {
            if (type.equals(authVerifyService.getType())) {
                return authVerifyService;
            }
        }
        return null;
    }
}
