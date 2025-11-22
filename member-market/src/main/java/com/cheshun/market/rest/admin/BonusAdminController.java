package com.cheshun.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.market.domain.entity.enums.DataAuth;
import com.cheshun.market.service.admin.BonusAdminService;
import com.cheshun.market.service.admin.SyncManageService;
import com.cheshun.market.vo.dto.AgentAdminDto;
import com.cheshun.market.vo.dto.BonusDashboardAdminVo;
import com.cheshun.market.vo.query.AgentPageQuery;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.AdminApi;
import com.cheshun.market.config.common.LoginInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/bonus")
@Api(tags = "后管系统-分润管理API")
@AllArgsConstructor
public class BonusAdminController {
    private final BonusAdminService bonusAdminService;
    private final SyncManageService syncManageService;

    /**
     * 分润概况统计查询
     *
     * @return 分润概况统计 {@link BonusDashboardAdminVo}
     */
    @AdminApi
    @PostMapping("dashboard")
    @ApiOperation("分润概况统计查询")
    public BonusDashboardAdminVo dashboard(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        if(DataAuth.Personal == loginInfo.getDataAuth()){
            return bonusAdminService.dashboardPersonal(loginInfo);
        }
        return bonusAdminService.dashboard();
    }

    /**
     * 分页查询代理商分润信息
     *
     * @param query 条件 {@link AgentPageQuery}
     * @return 代理商分润列表  {@link IPage<  AgentAdminDto  >}
     */
    @AdminApi
    @PostMapping("query")
    @ApiOperation("分页查询代理商分润信息")
    public IPage<AgentAdminDto> query(@RequestBody @Valid AgentPageQuery query,@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo) {
        if(DataAuth.Personal == loginInfo.getDataAuth()){
            return bonusAdminService.queryPersonal(query,loginInfo);
        }
        return bonusAdminService.query(query);
    }

    /**
     * 同步分润金额
     */
    @AdminApi
    @PostMapping(value = "sync")
    @ApiOperation("同步分润金额")
    public void sync() {
        syncManageService.syncBonus();
    }
}
