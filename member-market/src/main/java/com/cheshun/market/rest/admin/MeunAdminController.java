package com.cheshun.market.rest.admin;

import com.cheshun.market.domain.entity.ClsMarketEtcMeun;
import com.cheshun.market.service.admin.MeunAdminService;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.AdminApi;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("clsapi/market/etc/admin/meun")
@Api(tags = "后管系统-菜单API")
@AllArgsConstructor
public class MeunAdminController {

    private final MeunAdminService meunAdminService;

    @AdminApi
    @PostMapping("findList")
    @ApiOperation("查询菜单列表")
    public List<ClsMarketEtcMeun> findList(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo){
        return  meunAdminService.findList(loginInfo.getRoleId());
    }

}
