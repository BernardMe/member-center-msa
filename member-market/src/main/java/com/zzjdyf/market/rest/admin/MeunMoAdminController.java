package com.zzjdyf.market.rest.admin;

import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.entity.ClsMarketEtcRoleDataAuth;
import com.zzjdyf.market.service.admin.MeunMoAdminService;
import com.zzjdyf.market.vo.command.UpdateMeunMoCommand;
import com.zzjdyf.market.vo.command.UpdateRoleDataCommand;
import com.zzjdyf.market.vo.dto.MeunMoAdminDto;
import com.zzjdyf.market.vo.dto.RoleMoAdminDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clsapi/market/etc/admin/meunMo")
@Api(tags = "后管系统-菜单、功能权限API")
@AllArgsConstructor
public class MeunMoAdminController {

    private final MeunMoAdminService meunMoAdminService;

    @AdminApi
    @PostMapping("findList")
    @ApiOperation("查询菜单和功能列表")
    public List<MeunMoAdminDto> findList(){
        return  meunMoAdminService.findList();
    }



    @AdminApi
    @PostMapping("queryMeunMoByRole/{meunId}")
    @ApiOperation("根据菜单查询功能列表")
    public List<RoleMoAdminDto> queryMeunMoByRole(@PathVariable Long meunId, @RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo){
        return meunMoAdminService.queryMeunMoByRole(meunId,loginInfo.getRoleId());
    }

    @AdminApi
    @PostMapping("queryMoByRole/{roleId}")
    @ApiOperation("根据菜单查询功能列表")
    public List<MeunMoAdminDto> queryMoByRole(@PathVariable Long roleId){

        return meunMoAdminService.queryMoByRole(roleId);

    }

    @AdminApi
    @PostMapping("update")
    @ApiOperation("修改角色的功能权限")
    public void update(@RequestBody @Valid List<UpdateMeunMoCommand> updateMeunMoCommands){

        meunMoAdminService.update(updateMeunMoCommands);
    }

    @AdminApi
    @PostMapping("dataAuthUpdate")
    @ApiOperation("修改角色的数据权限")
    public void dataAuthUpdate(@RequestBody @Valid UpdateRoleDataCommand updateRoleDataCommand){

        meunMoAdminService.dataAuthUpdate(updateRoleDataCommand);
    }

    @AdminApi
    @PostMapping("dataAuthQuery")
    @ApiOperation("查询角色的数据权限")
    public ClsMarketEtcRoleDataAuth dataAuthQuery(@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo){
      return  meunMoAdminService.queryByRoleId(loginInfo.getRoleId());
    }

    /**
     * 根据前端传的角色id查询数据权限
     * @param id
     * @return
     */
    @AdminApi
    @PostMapping("dataAuthQuery/{id}")
    @ApiOperation("查询角色的数据权限")
    public ClsMarketEtcRoleDataAuth dataAuthQuery(@PathVariable Long id){
        return  meunMoAdminService.queryByRoleId(id);
    }


}
