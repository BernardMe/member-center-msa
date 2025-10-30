package com.zzjdyf.market.rest.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzjdyf.common.Const;
import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.service.admin.RoleAdminService;
import com.zzjdyf.market.vo.command.AddRoleCommand;
import com.zzjdyf.market.vo.command.UpdateRoleCommand;
import com.zzjdyf.market.vo.dto.RoleAdminDto;
import com.zzjdyf.market.vo.query.RolePageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clsapi/market/etc/admin/role")
@Api(tags = "后管系统-角色API")
@AllArgsConstructor
public class RoleAdminController {

    private final RoleAdminService roleAdminService;
    /**
     * 分页查询角色
     *
     * @param rolePageQuery 条件 {@link RolePageQuery}
     * @return 角色列表  {@link IPage < RoleAdminDto >}
     */
    @AdminApi
    @PostMapping("query")
    @ApiOperation("分页查询角色列表")
    public IPage<RoleAdminDto> query(@RequestBody @Valid RolePageQuery rolePageQuery) {
        return roleAdminService.query(rolePageQuery);
    }

    /**
     * 新增角色
     *
     * @param addRoleCommand 条件 {@link AddRoleCommand}
     */
    @AdminApi
    @PostMapping("add")
    @ApiOperation("新增角色列表")
    public void add(@RequestBody @Valid AddRoleCommand addRoleCommand,@RequestAttribute(Const.LOGIN_INFO) LoginInfo loginInfo)  {
         roleAdminService.add(addRoleCommand,loginInfo);
    }


    /**
     * 修改角色
     *
     * @param updateRoleCommand 条件 {@link UpdateRoleCommand}
     */
    @AdminApi
    @PostMapping("update")
    @ApiOperation("修改角色")
    public void update(@RequestBody @Valid UpdateRoleCommand updateRoleCommand)  {
        roleAdminService.update(updateRoleCommand);

    }


    /**
     * 删除角色
     *
     * @param id 条件 {@link Long}
     */
    @AdminApi
    @PostMapping("delete/{id}")
    @ApiOperation("删除角色")
    public void delete(@PathVariable String id)  {
        if (!StringUtils.hasText(id) || id.length() > 20) {
            throw new RRException("参数异常");
        }
        roleAdminService.delete(Long.parseLong(id));

    }


    /**
     * 查询角色列表，供新增用户使用
     *
     * @return 角色列表  {@link List < RoleAdminDto >}
     */
    @AdminApi
    @PostMapping("queryList")
    @ApiOperation("分页查询角色列表")
    public List<RoleAdminDto> queryList() {
        return roleAdminService.queryList();
    }

    @AdminApi
    @PostMapping("get/{id}")
    @ApiOperation("查询角色的详情")
    public RoleAdminDto get(@PathVariable Long id){

        return roleAdminService.get(id);
    }

}
