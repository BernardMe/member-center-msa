package com.zzjdyf.market.rest.admin;

import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.market.service.admin.GlobalSettingService;
import com.zzjdyf.market.vo.command.UpdateGlobalSettingCommand;
import com.zzjdyf.market.vo.dto.GlobalSettingDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 阿隆
 * Created on 2021/8/12 5:58 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/globalSetting")
@Api(tags = "后管系统-全局配置API")
@AllArgsConstructor
public class GlobalSettingAdminController {
    private final GlobalSettingService globalSettingService;

    /**
     * 获取全局配置信息
     *
     * @return 全局配置 {@link GlobalSettingDto}
     */
    @AdminApi
    @PostMapping
    @ApiOperation("获取全局配置信息")
    public GlobalSettingDto query() {
        return globalSettingService.query();
    }

    /**
     * 更新配置信息
     *
     * @param command 命令 {@link UpdateGlobalSettingCommand}
     */
    @AdminApi
    @PostMapping("update")
    @ApiOperation("更新配置信息")
    public void update(@RequestBody @Valid UpdateGlobalSettingCommand command) {
        globalSettingService.update(command);
    }
}
