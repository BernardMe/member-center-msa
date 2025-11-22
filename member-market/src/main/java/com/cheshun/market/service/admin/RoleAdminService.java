package com.cheshun.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.entity.ClsMarketEtcAccount;
import com.cheshun.market.domain.entity.ClsMarketEtcRole;
import com.cheshun.market.domain.entity.enums.AccountStatus;
import com.cheshun.market.domain.entity.enums.RoleStatus;
import com.cheshun.market.service.DtoService;
import com.cheshun.common.exception.BizCodeEnum;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.config.common.LoginInfo;
import com.cheshun.market.domain.dao.AccountDao;
import com.cheshun.market.domain.dao.RoleDao;
import com.cheshun.market.vo.command.AddRoleCommand;
import com.cheshun.market.vo.command.UpdateRoleCommand;
import com.cheshun.market.vo.dto.RoleAdminDto;
import com.cheshun.market.vo.query.RolePageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class RoleAdminService {

    private final RoleDao roleDao;

    private final AccountDao accountDao;

    /**
     * 分页查询角色
     *
     * @param rolePageQuery 条件 {@link RolePageQuery}
     * @return 角色列表  {@link IPage < RoleAdminDto >}
     */
    public IPage<RoleAdminDto> query(RolePageQuery rolePageQuery) {

        IPage<ClsMarketEtcRole> page = roleDao.getMapper().findPageList(new Page<>(rolePageQuery.getPageNum(), rolePageQuery.getPageSize()),
                rolePageQuery.getRoleName(),rolePageQuery.getStatus());
        List<RoleAdminDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<RoleAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    private RoleAdminDto toDto(ClsMarketEtcRole entity) {
        RoleAdminDto dto = DtoService.INSTANCE.toDto(entity);
        LambdaQueryWrapper<ClsMarketEtcAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAccount::getRoleId, entity.getId());
        queryWrapper.eq(ClsMarketEtcAccount::getStatus, AccountStatus.Enable.getValue());
        List<ClsMarketEtcAccount> list = accountDao.selectList(queryWrapper);
        if(list.size()>0){
            dto.setRelation(1);
        }
        return dto;
    }

    public void add(AddRoleCommand addRoleCommand, LoginInfo loginInfo) {
        LambdaQueryWrapper<ClsMarketEtcRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcRole::getRoleName,addRoleCommand.getRoleName());
        queryWrapper.eq(ClsMarketEtcRole::getStatus, RoleStatus.Enable.getValue());
        List<ClsMarketEtcRole> list = roleDao.selectList(queryWrapper);
        if(list.size()>0){
            throw new RRException("已存在该角色，不允许新增");
        }
        ClsMarketEtcRole clsMarketEtcRole =new ClsMarketEtcRole();
        clsMarketEtcRole.setStatus(RoleStatus.Enable);
        clsMarketEtcRole.setRoleName(addRoleCommand.getRoleName());
        clsMarketEtcRole.setLimitNumber(Integer.valueOf(addRoleCommand.getLimitNumber()));
        clsMarketEtcRole.setRoleDesc(addRoleCommand.getRoleDesc());
        clsMarketEtcRole.setCreateId(loginInfo.getId());
        ClsMarketEtcAccount clsMarketEtcAccount = accountDao.findOne(loginInfo.getId());
        clsMarketEtcRole.setCreateName(clsMarketEtcAccount.getAccountName());

        if(roleDao.save(clsMarketEtcRole)==null){
            throw new RRException(BizCodeEnum.ADD_FAILED);
        }
    }

    public void update(UpdateRoleCommand updateRoleCommand) {

        if(RoleStatus.Deactivate.getValue().equals(updateRoleCommand.getStatus())){
            LambdaQueryWrapper<ClsMarketEtcAccount> queryWrapper = new LambdaQueryWrapper<>();

            queryWrapper.eq(ClsMarketEtcAccount::getRoleId,updateRoleCommand.getId());
            queryWrapper.eq(ClsMarketEtcAccount::getStatus,RoleStatus.Enable.getValue());
            List<ClsMarketEtcAccount> list =accountDao.selectList(queryWrapper);
            if(list.size()>0){
                throw new RRException("该角色有关联员工，停用失败");
            }
        }
        LambdaQueryWrapper<ClsMarketEtcRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcRole::getRoleName,updateRoleCommand.getRoleName());
        wrapper.eq(ClsMarketEtcRole::getStatus, RoleStatus.Enable.getValue());
        List<ClsMarketEtcRole> list = roleDao.selectList(wrapper);
        for(ClsMarketEtcRole clsMarketEtcRole:list){
            if(clsMarketEtcRole.getId()!=updateRoleCommand.getId()){
                throw new RRException("已存在该角色名称，更新失败");
            }
        }
        ClsMarketEtcRole clsMarketEtcRole =new ClsMarketEtcRole();
        clsMarketEtcRole.setStatus(updateRoleCommand.getStatus());
        clsMarketEtcRole.setRoleName(updateRoleCommand.getRoleName());
        clsMarketEtcRole.setLimitNumber(Integer.valueOf(updateRoleCommand.getLimitNumber()));
        clsMarketEtcRole.setRoleDesc(updateRoleCommand.getRoleDesc());
        clsMarketEtcRole.setId(updateRoleCommand.getId());
        roleDao.update(clsMarketEtcRole);

    }

    public void delete(Long id){
        LambdaQueryWrapper<ClsMarketEtcAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAccount::getRoleId,id);
        queryWrapper.eq(ClsMarketEtcAccount::getStatus,RoleStatus.Enable.getValue());
        List<ClsMarketEtcAccount> list =accountDao.selectList(queryWrapper);
        if(list.size()>0){
            throw new RRException("该角色有关联员工，无法删除");
        }
        ClsMarketEtcRole clsMarketEtcRole = new ClsMarketEtcRole();
        clsMarketEtcRole.setId(id);
        roleDao.delete(clsMarketEtcRole);
    }


    /**
     * 查询角色列表
     *
     */
    public List<RoleAdminDto> queryList() {
        LambdaQueryWrapper<ClsMarketEtcRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcRole::getStatus,RoleStatus.Enable);
        List<ClsMarketEtcRole> clsMarketEtcRoles = roleDao.selectList(queryWrapper);
        return clsMarketEtcRoles.stream().map(this::toDto).collect(Collectors.toList());
    }

    public RoleAdminDto get(Long id){
        ClsMarketEtcRole clsMarketEtcRole = roleDao.findOne(id);
        return clsMarketEtcRole==null?null:toDto(clsMarketEtcRole);
    }
}
