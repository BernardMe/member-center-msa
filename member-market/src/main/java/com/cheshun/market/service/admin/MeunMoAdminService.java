package com.cheshun.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cheshun.market.domain.dao.*;
import com.cheshun.market.domain.entity.*;
import com.zzjdyf.market.domain.dao.*;
import com.zzjdyf.market.domain.entity.*;
import com.cheshun.market.service.common.JwtService;
import com.cheshun.market.vo.command.MeunMoCommand;
import com.cheshun.market.vo.command.UpdateMeunMoCommand;
import com.cheshun.market.vo.command.UpdateRoleDataCommand;
import com.cheshun.market.vo.dto.MeunMoAdminDto;
import com.cheshun.market.vo.dto.RoleMoAdminDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class MeunMoAdminService {

    private final MeunMoDao meunMoDao;

    private final MeunDao meunDao;

    private final RoleMoDao roleMoDao;

    private final RoleMeunDao roleMeunDao;

    private final RoleDataAuthDao roleDataAuthDao;

    private final AccountDao accountDao;

    private final JwtService jwtService;


    public List<MeunMoAdminDto> findList(){

        List<ClsMarketEtcMeun> list = meunDao.queryListByLeaf(1);
        List<MeunMoAdminDto> meunMoAdminDtos = new ArrayList<>();
        for(ClsMarketEtcMeun clsMarketEtcMeun:list){
            MeunMoAdminDto meunMoAdminDto =new MeunMoAdminDto();
            LambdaQueryWrapper<ClsMarketEtcMeunMo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ClsMarketEtcMeunMo::getMeunId,clsMarketEtcMeun.getId());
            List<ClsMarketEtcMeunMo> clsMarketEtcMeunMos =meunMoDao.selectList(wrapper);
            meunMoAdminDto.setMeunId(clsMarketEtcMeun.getId());
            meunMoAdminDto.setMeunName(clsMarketEtcMeun.getName());
            meunMoAdminDto.setClsMarketEtcMeunMos(clsMarketEtcMeunMos);
            meunMoAdminDtos.add(meunMoAdminDto);
        }
        return meunMoAdminDtos;
    }

    public List<MeunMoAdminDto> queryMoByRole(Long roleId){

        List <ClsMarketEtcRoleMeun> list= roleMeunDao.findListByRoleId(roleId,1);
        List<MeunMoAdminDto> meunMoAdminDtos = new ArrayList<>();
        for(ClsMarketEtcRoleMeun clsMarketEtcRoleMeun:list){
            MeunMoAdminDto meunMoAdminDto =new MeunMoAdminDto();
            LambdaQueryWrapper<ClsMarketEtcRoleMo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ClsMarketEtcRoleMo::getRoleId,roleId);
            wrapper.eq(ClsMarketEtcRoleMo::getMeunId,clsMarketEtcRoleMeun.getMeunId());
            meunMoAdminDto.setMeunId(clsMarketEtcRoleMeun.getMeunId());
            List<ClsMarketEtcRoleMo> clsMarketEtcMeunMos = roleMoDao.selectList(wrapper);
            List<ClsMarketEtcMeunMo> clsMarketEtcMeunMoList =new ArrayList<>();
            if(clsMarketEtcMeunMos.size()!=0){
                 clsMarketEtcMeunMoList =clsMarketEtcMeunMos.stream().map(this::toDto).collect(Collectors.toList());
            }
            meunMoAdminDto.setClsMarketEtcMeunMos(clsMarketEtcMeunMoList);

            meunMoAdminDtos.add(meunMoAdminDto);
        }

        return meunMoAdminDtos;
    }

    private ClsMarketEtcMeunMo toDto(ClsMarketEtcRoleMo entity) {
        ClsMarketEtcMeunMo dto = new ClsMarketEtcMeunMo();
        dto.setMeunId(entity.getMeunId());
        dto.setMoValue(String.valueOf(entity.getMoValue()));
        return dto;
    }

    @Transactional
    public void update(List<UpdateMeunMoCommand> updateMeunMoCommands){
        //遍历菜单
        for(UpdateMeunMoCommand updateMeunMoCommand:updateMeunMoCommands){
        log.info("删除角色下所有的mo值");
        ClsMarketEtcRoleMo clsMarketEtcRoleMo =new ClsMarketEtcRoleMo();
        clsMarketEtcRoleMo.setMeunId(updateMeunMoCommand.getMeunId());
        clsMarketEtcRoleMo.setRoleId(updateMeunMoCommand.getRoleId());
        roleMoDao.deleteByMeunId(clsMarketEtcRoleMo);
        for(MeunMoCommand meunMoCommand:updateMeunMoCommand.getMeunMoCommands()){
            clsMarketEtcRoleMo.setMoValue(meunMoCommand.getMoValue());
            clsMarketEtcRoleMo.setCreateTime(new Date());
            roleMoDao.insert(clsMarketEtcRoleMo);
        }
            if(updateMeunMoCommand.getType()==0){
                //给菜单父节点赋权

                meunRoot(updateMeunMoCommand.getMeunId(),updateMeunMoCommand.getRoleId());

                ClsMarketEtcRoleMeun clsMarketEtcRoleMeun =new ClsMarketEtcRoleMeun();
                clsMarketEtcRoleMeun.setMeunId(updateMeunMoCommand.getMeunId());
                clsMarketEtcRoleMeun.setRoleId(updateMeunMoCommand.getRoleId());
                if(roleMeunDao.findOneByRoleMeunId(clsMarketEtcRoleMeun)==null){
                    roleMeunDao.save(clsMarketEtcRoleMeun);
                }
            }else{
                //查询权限，如果没有一个权限则无这个菜单权限
                roleMeunDao.deleteByRoleMeunId(updateMeunMoCommand.getRoleId(),updateMeunMoCommand.getMeunId());
            }
        }
        try{
            new Thread(){
                @Override
                public void run() {
                    deleteTokenByRoleId(updateMeunMoCommands.get(0).getRoleId());
                }
            }.start();
        }catch (Exception e){
            log.info("删除token失败");
        }
    }


    public List<RoleMoAdminDto> queryMeunMoByRole(Long meunId, Long roleId){

        return roleMoDao.findListByRoleAndMenu(meunId,roleId);
    }

    //查询菜单的信息，判断是否是叶子节点，不是叶子节点同时增加父节点的权限
     void meunRoot(Long meunId,Long roleId){
        //菜单id是0的，直接结束
         if(0==meunId) {
            return;
         }
         ClsMarketEtcMeun clsMarketEtcMeun = meunDao.queryByMeunIdId(meunId);
        if(1==clsMarketEtcMeun.getLeaf()&&0!=clsMarketEtcMeun.getParentId()){
            ClsMarketEtcRoleMeun clsMarketEtcRoleMeun =new ClsMarketEtcRoleMeun();
            clsMarketEtcRoleMeun.setMeunId(clsMarketEtcMeun.getParentId());
            clsMarketEtcRoleMeun.setRoleId(roleId);
            if(roleMeunDao.findOneByRoleMeunId(clsMarketEtcRoleMeun)==null){
                roleMeunDao.save(clsMarketEtcRoleMeun);
            }
        }
         meunRoot(clsMarketEtcMeun.getParentId(),roleId);
    }

    //修改数据权限
    public void dataAuthUpdate(UpdateRoleDataCommand updateRoleDataCommand){
        ClsMarketEtcRoleDataAuth clsMarketEtcRoleDataAuth =new ClsMarketEtcRoleDataAuth();
        clsMarketEtcRoleDataAuth.setDataAuth(updateRoleDataCommand.getDataAuth());
        clsMarketEtcRoleDataAuth.setRoleId(updateRoleDataCommand.getRoleId());

        if(null == roleDataAuthDao.queryByRoleId(updateRoleDataCommand.getRoleId())){
            roleDataAuthDao.save(clsMarketEtcRoleDataAuth);
        }else {
            roleDataAuthDao.updateByRoleId(clsMarketEtcRoleDataAuth);
        }
        try{
            new Thread(){
                @Override
                public void run() {
                    deleteTokenByRoleId(updateRoleDataCommand.getRoleId());
                }
            }.start();
        }catch (Exception e){
            log.info("删除token失败");
        }
    }
    public ClsMarketEtcRoleDataAuth queryByRoleId(Long roleId){
        return roleDataAuthDao.queryByRoleId(roleId);
    }


    //修改角色权限以后，踢掉该角色下的所有在线用户
    public void deleteTokenByRoleId(Long roleId){
        LambdaQueryWrapper<ClsMarketEtcAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClsMarketEtcAccount::getRoleId,roleId);
        List<ClsMarketEtcAccount> accounts = accountDao.selectList(wrapper);
        accounts.forEach(t->{
            if(t.getToken()!=null){
                jwtService.delete(t.getToken());
            }
        });
    }
}
