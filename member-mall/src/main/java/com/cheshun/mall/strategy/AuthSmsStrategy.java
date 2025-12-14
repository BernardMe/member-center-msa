package com.cheshun.mall.strategy;

import com.cheshun.mall.service.SkuService;
import com.cheshun.common.exception.RRException;
import com.google.common.collect.Lists;
import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xueqing wang on 2021/4/22 11:46
 */
@Component
@RefreshScope
public class AuthSmsStrategy {
    @Value("${cheshun.sms.weight.chengliye}")
    private Integer chengliye;

    @Value("${cheshun.sms.weight.mengwang}")
    private Integer mengwang;

    @Value("${cheshun.sms.weight.jumeng}")
    private Integer jumeng;

    List<Pair<String, Integer>> list;
    private static WeightRandom<String, Integer> random;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private List<SkuService> authSmsServiceList;

    @PostConstruct
    public void init() {
        list = Lists.newArrayList();
        if (chengliye > 0 && chengliye <= 10) {
            list.add(new Pair("CHENG_LI_YE", chengliye));
        }
        if (mengwang > 0 && mengwang <= 10) {
            list.add(new Pair("MENG_WANG", mengwang));
        }
        if (jumeng > 0 && jumeng <= 10) {
            list.add(new Pair("JU_MENG", jumeng));
        }
        if (chengliye + mengwang + jumeng > 10) {
            throw new RRException("权重值异常");
        }
        AuthSmsStrategy.random = new WeightRandom(list);
    }

    /**
     * 根据权重随机确定短信服务
     *
     * @return AuthSmsService 短信服务 {@link SkuService}
     * @author wangzhuo
     */
    public SkuService random() {
        String randomType = random.random();
        return getAuthSmsService(randomType);
    }

    public SkuService getAuthSmsService(String type) {

        for (SkuService authSmsService : authSmsServiceList) {
            if (type.equals(authSmsService.getType())) {
                return authSmsService;
            }
        }
        return null;
    }
}
