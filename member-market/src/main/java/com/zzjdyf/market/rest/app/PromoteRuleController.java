package com.zzjdyf.market.rest.app;

import com.zzjdyf.market.service.app.PromoteRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/8/20 5:45 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/app/promote/rule")
@Api(tags = "移动端-推广规则API")
@AllArgsConstructor
public class PromoteRuleController {
    private final PromoteRuleService promoteRuleService;

    /**
     * 获取推广规则列表
     *
     * @return 推广规则列表 {@link List}
     */
    @PostMapping
    @ApiOperation("获取推广规则列表")
    public List<String> query() {
        return promoteRuleService.query();
    }
}
