package com.zzjdyf.market.rest.app;

import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.market.config.AccountConfig;
import com.zzjdyf.market.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/9/8 5:20 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/app/common")
@Api(tags = "移动端-共通API")
@AllArgsConstructor
public class CommonController {
    private final CommonService commonService;

    /**
     * 获取打款账户信息
     *
     * @return 打款账户信息 {@link AccountConfig}
     */
    @NoToken
    @GetMapping("getAccount")
    @ApiOperation("获取打款账户信息")
    public Map<String, String> getAccount() {
        return commonService.getAccount();
    }
}
