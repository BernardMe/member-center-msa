package com.cheshun.market.rest.admin;

import com.cheshun.market.service.admin.StatsService;
import com.cheshun.market.vo.dto.StatsDto;
import com.cheshun.common.annotation.AdminApi;
import com.cheshun.common.annotation.NoToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阿隆
 * Created on 2021/9/30 10:29 上午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/stats")
@Api(tags = "后管系统-统计API")
@AllArgsConstructor
public class StatsController {
    private final StatsService statsService;

    /**
     * 查询统计信息
     *
     * @param queryType 查询类型 D｜W｜M｜Y
     * @return 统计信息 {@link StatsDto}
     */
    @NoToken
    @GetMapping("query")
    public StatsDto query(String queryType) {
        return statsService.query(queryType);
    }

    /**
     * 清理统计信息
     */
    @NoToken
    @AdminApi
    @PostMapping(value = "clearAll")
    @ApiOperation("清理统计信息")
    public void clearAll() {
        statsService.clearAll();
    }
}
