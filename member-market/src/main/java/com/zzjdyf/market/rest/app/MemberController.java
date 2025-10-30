package com.zzjdyf.market.rest.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.service.app.MemberAppService;
import com.zzjdyf.market.vo.dto.AgentAppDto;
import com.zzjdyf.market.vo.query.MemberPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangzhuo
 * Created on 20210728
 */
@Slf4j
@RestController
@RequestMapping("clsapi/market/etc/app/member")
@Api(tags = "移动端-我的成员API")
@AllArgsConstructor
public class MemberController {
    private final MemberAppService fellowService;

    /**
     * 分页查询下级成员列表
     *
     * @param loginInfo 用户登陆信息 {@link LoginInfo}
     * @param query     条件 {@link MemberPageQuery}
     * @return 下级成员列表  {@link IPage< AgentAppDto >}
     */
    @PostMapping("query")
    @ApiOperation("分页查询下级成员列表")
    public IPage<AgentAppDto> query(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo,
                                    @RequestBody @Valid MemberPageQuery query) {
        return fellowService.query(loginInfo.getId(), query);
    }
}
