package com.cheshun.market.rest.app;

import com.cheshun.market.service.app.WechatLoginService;
import com.cheshun.market.vo.command.SmsLoginCommand;
import com.cheshun.market.vo.command.WechatLoginCommand;
import com.cheshun.market.vo.dto.AgentAppDto;
import com.cheshun.market.vo.dto.LittleProgramAuthDto;
import com.cheshun.market.vo.dto.WechatLoginDto;
import com.cheshun.common.annotation.NoToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author
 * Created on 20211027
 */
@RestController
@RequestMapping("clsapi/market/etc/app/wechatLogin")
@Api(tags = "移动端-微信授信登录")
public class WechatLoginController {


    @Value("${wechat.appid}")
    public String appid;

    @Value("${wechat.secret}")
    public String secret;

    @Autowired
    private WechatLoginService wechatLoginService;


    /**
     * 根据提供的code、appid和secret
     */
    @NoToken
    @RequestMapping(value = "/getOpenId/{code}", method = RequestMethod.GET)
    @ApiOperation("根据code获取获取用户的openId")
    public WechatLoginDto getOpenId(@PathVariable String code) throws Exception{
        LittleProgramAuthDto littleProgramAuthDto=  wechatLoginService.getLittleProgramAccessToken(appid,secret,code);
        WechatLoginDto wechatLoginDto =new WechatLoginDto();
        if(StringUtils.isNotEmpty(littleProgramAuthDto.getOpenid())){
            wechatLoginDto = wechatLoginService.getUserInfo(littleProgramAuthDto.getOpenid());
        }else{
            wechatLoginDto.setExist(false);
            wechatLoginDto.setOpenId(littleProgramAuthDto.getOpenid());
        }
        return wechatLoginDto;
    }

    /**
     * 手机号
     *
     * @param command 命令 {@link SmsLoginCommand}
     * @return 代理商信息 {@link AgentAppDto}
     */
    @NoToken
    @PostMapping("login")
    @ApiOperation("手机号登陆")
    public AgentAppDto login(@RequestBody @Valid WechatLoginCommand command) throws  Exception{
        LittleProgramAuthDto littleProgramAuthDto=  wechatLoginService.getLittleProgramAccessToken(appid,secret,command.getCode());
        String phone =wechatLoginService.decodeInfo(command.getEncryptedData(),command.getIv(),littleProgramAuthDto.getSession_key());
        return wechatLoginService.login(littleProgramAuthDto.getOpenid(),phone);
    }


}
