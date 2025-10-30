package com.zzjdyf.market.service.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.dao.AccountDao;
import com.zzjdyf.market.domain.dao.RoleDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAccount;
import com.zzjdyf.market.domain.entity.ClsMarketEtcRole;
import com.zzjdyf.market.domain.entity.enums.AccountStatus;
import com.zzjdyf.market.vo.command.AddAccountCommand;
import com.zzjdyf.market.vo.command.UpdateAccountCommand;
import com.zzjdyf.market.vo.dto.AccountAdminDto;
import com.zzjdyf.market.vo.query.AccountPageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class AccountAdminService {


    private final AccountDao accountDao;

    private final RoleDao roleDao;

    /**
     * 分页查询员工信息
     *
     * @param accountPageQuery 条件 {@link AccountPageQuery}
     * @return 员工  {@link IPage < ClsMarketEtcAccount >}
     */
    public IPage<AccountAdminDto> findList(AccountPageQuery accountPageQuery) {
        IPage<AccountAdminDto> page = accountDao.getMapper().findListByNameOrPhone(new Page<>(accountPageQuery.getPageNum(), accountPageQuery.getPageSize()),
                accountPageQuery.getAccountName(),accountPageQuery.getStatus());
        return page;
    }


    public void add(AddAccountCommand addAccountCommand, LoginInfo loginInfo){

        if("0".equals(addAccountCommand.getStatus())) {
            ClsMarketEtcRole clsMarketEtcRole = roleDao.findOne(addAccountCommand.getRoleId());
            if (clsMarketEtcRole == null) {
                throw new RRException("该角色不存在");
            }
            LambdaQueryWrapper<ClsMarketEtcAccount> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ClsMarketEtcAccount::getRoleId, addAccountCommand.getRoleId());
            queryWrapper.eq(ClsMarketEtcAccount::getStatus, AccountStatus.Enable);
            List<ClsMarketEtcAccount> list = accountDao.selectList(queryWrapper);
            if (list.size() >= clsMarketEtcRole.getLimitNumber()) {
                throw new RRException("该角色已经到限制人数上限，无法新增员工，请选择另外的角色");
            }
            if (accountDao.findOneByPhone(addAccountCommand.getPhone()) != null) {
                throw new RRException("该登录账号已经存在，不允许新增");
            }
        }
        ClsMarketEtcAccount clsMarketEtcAccount = new ClsMarketEtcAccount();
        clsMarketEtcAccount.setAccountName(addAccountCommand.getAccountName());
        clsMarketEtcAccount.setPhone(addAccountCommand.getPhone());
        clsMarketEtcAccount.setRoleId(addAccountCommand.getRoleId());
        clsMarketEtcAccount.setStatus(AccountStatus.valueByCode(Integer.parseInt(addAccountCommand.getStatus())));
        clsMarketEtcAccount.setCreateName(loginInfo.getUsername());
        clsMarketEtcAccount.setCreateTime(new Date());
        clsMarketEtcAccount.setCreateUseId(loginInfo.getId());
        accountDao.save(clsMarketEtcAccount);
    }


    public void update(UpdateAccountCommand updateAccountCommand){
        ClsMarketEtcAccount clsMarketEtcAccount = accountDao.findOne(updateAccountCommand.getId());
        //启用状态的判断
        if("0".equals(updateAccountCommand.getStatus())){
            if(clsMarketEtcAccount.getRoleId() != updateAccountCommand.getRoleId()){
                ClsMarketEtcRole clsMarketEtcRole = roleDao.findOne(updateAccountCommand.getRoleId());
                if(clsMarketEtcRole==null){
                    throw new RRException("该角色不存在");
                }
                LambdaQueryWrapper<ClsMarketEtcAccount> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ClsMarketEtcAccount::getRoleId,updateAccountCommand.getRoleId());
                queryWrapper.eq(ClsMarketEtcAccount::getStatus,AccountStatus.Enable);
                List<ClsMarketEtcAccount> list = accountDao.selectList(queryWrapper);
                if(list.size()>=clsMarketEtcRole.getLimitNumber()){
                    throw new RRException("该角色已经到限制人数上限，无法新增员工，请选择另外的角色");
                }
            }
                if(!clsMarketEtcAccount.getPhone().equals(updateAccountCommand.getPhone())&&accountDao.findOneByPhone(updateAccountCommand.getPhone())!=null){
                    throw new RRException("该登录账号已经存在，不允许新增");
                }
        }
        clsMarketEtcAccount.setAccountName(updateAccountCommand.getAccountName());
        clsMarketEtcAccount.setPhone(updateAccountCommand.getPhone());
        clsMarketEtcAccount.setRoleId(updateAccountCommand.getRoleId());
        clsMarketEtcAccount.setStatus(AccountStatus.valueByCode(Integer.parseInt(updateAccountCommand.getStatus())));
        clsMarketEtcAccount.setId(updateAccountCommand.getId());
        accountDao.save(clsMarketEtcAccount);
    }


    public void delete (Long id){
        ClsMarketEtcAccount clsMarketEtcAccount =new ClsMarketEtcAccount();
        clsMarketEtcAccount.setId(id);
        accountDao.delete(clsMarketEtcAccount);
    }
}
