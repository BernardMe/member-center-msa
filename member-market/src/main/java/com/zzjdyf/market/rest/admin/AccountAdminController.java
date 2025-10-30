package com.zzjdyf.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.service.admin.AccountAdminService;
import com.zzjdyf.market.vo.command.AddAccountCommand;
import com.zzjdyf.market.vo.command.UpdateAccountCommand;
import com.zzjdyf.market.vo.dto.AccountAdminDto;
import com.zzjdyf.market.vo.query.AccountPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("clsapi/market/etc/admin/account")
@Api(tags = "后管系统-用户管理API")
@AllArgsConstructor
public class AccountAdminController {
    private final AccountAdminService accountAdminService;

    @AdminApi
    @PostMapping("findList")
    @ApiOperation("查询员工列表")
    public IPage<AccountAdminDto> findList(@RequestBody @Valid AccountPageQuery accountPageQuery){
        return  accountAdminService.findList(accountPageQuery);
    }

    @AdminApi
    @PostMapping("add")
    @ApiOperation("新增员工")
    public void add(@RequestBody @Valid AddAccountCommand addAccountCommand, @RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo){
        accountAdminService.add(addAccountCommand,loginInfo);
    }

    @AdminApi
    @PostMapping("update")
    @ApiOperation("修改员工")
    public void update(@RequestBody @Valid UpdateAccountCommand updateAccountCommand){
        accountAdminService.update(updateAccountCommand);
    }

    @AdminApi
    @PostMapping("delete/{id}")
    @ApiOperation("删除员工")
    public void delete(@PathVariable Long id){
        accountAdminService.delete(id);
    }


}
