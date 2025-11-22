package com.cheshun.market.rest.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.market.service.app.PromoteAppService;
import com.cheshun.market.vo.dto.PromoteAppDto;
import com.cheshun.market.vo.query.PromotePageQuery;
import com.cheshun.common.Const;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/8/4 3:08 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/app/promote")
@Api(tags = "移动端-分润记录API")
@AllArgsConstructor
public class AgentPromoteAppController {
    private final PromoteAppService bonusAppService;

    /**
     * 分页查询代理商分润记录
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param query     条件 {@link PromotePageQuery}
     * @return 代理商推广明细  {@link IPage<  PromoteAppDto  >}
     */
    @PostMapping
    @ApiOperation("分页查询代理商分润记录")
    public IPage<PromoteAppDto> query(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                      @RequestBody @Valid PromotePageQuery query) {
        return bonusAppService.query(loginInfo.getId(), query);
    }
}
