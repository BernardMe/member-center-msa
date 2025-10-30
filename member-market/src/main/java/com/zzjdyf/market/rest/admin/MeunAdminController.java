package com.zzjdyf.market.rest.admin;

import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.entity.ClsMarketEtcMeun;
import com.zzjdyf.market.service.admin.MeunAdminService;
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
