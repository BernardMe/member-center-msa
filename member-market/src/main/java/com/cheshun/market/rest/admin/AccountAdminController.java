package com.cheshun.market.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.market.service.admin.AccountAdminService;
import com.cheshun.market.vo.command.AddAccountCommand;
import com.cheshun.market.vo.command.UpdateAccountCommand;
import com.cheshun.market.vo.dto.AccountAdminDto;
import com.cheshun.market.vo.query.AccountPageQuery;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.AdminApi;
import com.cheshun.market.config.common.LoginInfo;
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
