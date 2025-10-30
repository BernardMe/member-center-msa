package com.zzjdyf.market.service.admin;

import com.zzjdyf.common.annotation.MockData;
import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.common.tools.utils.R;
import com.zzjdyf.market.config.BuiltInManagerConfig;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.dao.AccountDao;
import com.zzjdyf.market.domain.dao.RoleDataAuthDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAccount;
import com.zzjdyf.market.domain.entity.ClsMarketEtcRoleDataAuth;
import com.zzjdyf.market.domain.entity.enums.DataAuth;
import com.zzjdyf.market.feign.AuthFeignService;
import com.zzjdyf.market.service.common.JwtService;
import com.zzjdyf.market.service.common.SmsService;
import com.zzjdyf.market.vo.command.SmsLoginCommand;
import com.zzjdyf.market.vo.dto.AdminUserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author 阿隆
 * Created on 2021/7/20 3:30 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginAdminService {
    private final BuiltInManagerConfig builtInManagerConfig;
    private final JwtService jwtService;
    private final SmsService smsService;
    private final AuthFeignService authFeignService;

    private final AccountDao accountDao;

    private final RoleDataAuthDao roleDataAuthDao;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     */
    @MockData(profiles = {"dev", "test"}, returnType = Void.class)
    public void send(String phone) {
    /*    // 获取管理员用户列表
        List<BuiltInManagerConfig.Manager> managerList = Optional.ofNullable(builtInManagerConfig.getList())
                .orElseThrow(() -> new RRException(BizCodeEnum.USER_NOT_EXIST));
        // 查找指定用户名的管理员
        managerList.stream()
                .filter(t -> t.getUsername().equals(phone))
                .findAny()
                .orElseThrow(() -> new RRException(BizCodeEnum.USER_NOT_EXIST));*/
        ClsMarketEtcAccount clsMarketEtcAccount =accountDao.findOneByPhone(phone);
        if(clsMarketEtcAccount==null){
            throw new RRException(BizCodeEnum.USER_NOT_EXIST);
        }
        R<Object> r = authFeignService.sendCode(phone);
        if (r.getCode() != HttpStatus.OK.value()) {
            throw new RRException(r.getMessage());
        }
    }

    /**
     * 手机号短信登陆
     *
     * @param command 命令 {@link SmsLoginCommand}
     * @return 管理员登陆信息 {@link AdminUserDto}
     */
    public AdminUserDto login(SmsLoginCommand command) {
        // 校验短信验证码
        smsService.verify(command.getPhone(), command.getSmsCode());
        // 获取管理员用户列表
      /*  List<BuiltInManagerConfig.Manager> managerList = Optional.ofNullable(builtInManagerConfig.getList())
                .orElseThrow(() -> new RRException(BizCodeEnum.USER_NOT_EXIST));
        // 查找指定用户名的管理员
        BuiltInManagerConfig.Manager manager = managerList.stream()
                .filter(t -> t.getUsername().equals(command.getPhone()))
                .findAny()
                .orElseThrow(() -> new RRException(BizCodeEnum.USER_NOT_EXIST));*/

        ClsMarketEtcAccount clsMarketEtcAccount =accountDao.findOneByPhone(command.getPhone());
        if(clsMarketEtcAccount==null){
            throw new RRException(BizCodeEnum.USER_NOT_EXIST);
        }
        ClsMarketEtcRoleDataAuth clsMarketEtcRoleDataAuth = roleDataAuthDao.queryByRoleId(clsMarketEtcAccount.getRoleId());

        try {
            // 生成token
            LoginInfo loginInfo = LoginInfo.builder().phone(clsMarketEtcAccount.getPhone()).username(clsMarketEtcAccount.getAccountName()).roleId(clsMarketEtcAccount.getRoleId()).id(clsMarketEtcAccount.getId())
                    .dataAuth(clsMarketEtcRoleDataAuth ==null? DataAuth.Company:clsMarketEtcRoleDataAuth.getDataAuth()).build();
            String token = jwtService.generate(loginInfo);
            clsMarketEtcAccount.setToken(token);
            accountDao.save(clsMarketEtcAccount);
            return AdminUserDto.builder().phone(clsMarketEtcAccount.getPhone()).token(token).id(clsMarketEtcAccount.getId()).build();
        } catch (Exception e) {
            throw new RRException(BizCodeEnum.LOGIN_FAILED, e);
        }
    }
}
