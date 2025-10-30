package com.zzjdyf.auth.service.user.login.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzjdyf.auth.dao.UserLoginInfoMapper;
import com.zzjdyf.auth.service.user.login.UserService;
import com.zzjdyf.auth.vo.dto.*;
import com.zzjdyf.common.constant.JwtClaimsConstant;
import com.zzjdyf.common.constant.MessageConstant;
import com.zzjdyf.common.context.BaseContext;
import com.zzjdyf.common.crminterface.MemberRelated;
import com.zzjdyf.common.exception.LoginFailedException;
import com.zzjdyf.common.properties.CrmProperties;
import com.zzjdyf.common.properties.JwtProperties;
import com.zzjdyf.common.properties.WeChatProperties;
import com.zzjdyf.common.result.Result;
import com.zzjdyf.auth.tools.util.CrmTokenUtil;
import com.zzjdyf.auth.tools.util.JwtUtil;
import com.zzjdyf.common.tools.utils.HttpClientUtil;
import com.zzjdyf.common.tools.utils.RefreshTokenUtil;
import com.zzjdyf.common.tools.utils.WeChatDecryptor;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;


    public static final String WECHAT_USER_INFO = "wechat_user_info:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserLoginInfoMapper userLoginInfoMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CrmProperties crmProperties;

    @Autowired
    private CrmTokenUtil crmTokenUtil;


    @Autowired
    @Qualifier("gainGroupId")
    private ThreadPoolTaskExecutor gainGroupId;

    /**
     * 微信登录
     *
     * @param param
     * @return
     */
    public Result wxLogin(WeChatLoginParam param) {
        JSONObject openidJson = getOpenid(param.getCode());
        String openid = openidJson.getString("openid");
        //判断openid是否为空，如果为空表示登录失败，抛出业务异常
        if (openidJson.getString("openid") == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, openid);
        // 短期token
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        // 3. 获取过期时间戳（秒级）
        Claims parsedClaims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
        Long expireTimestamp = parsedClaims.getExpiration().getTime() / 1000; // 转为秒级时间戳
        String unionid = openidJson.getString("unionid");
        String sessionKey = openidJson.getString("session_key");
        String refreshToken = RefreshTokenUtil.encryptOpenId(openid);
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setExpireTimestamp(expireTimestamp);
        userLoginVO.setOpenid(openid);
        userLoginVO.setUnionId(unionid);
        userLoginVO.setRefreshToken(refreshToken);
        userLoginVO.setAccessToken(token);
        if (redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken) != null) { // 清除缓存进入的
            log.info("用户openid=={}为清除缓存进入", openid);
            String memberLoginInfoStr = redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken).toString();
            MemberLoginInfo memberLoginInfo = JSONObject.parseObject(memberLoginInfoStr, MemberLoginInfo.class);
            memberLoginInfo.setSessionKey(sessionKey);
            if (StringUtils.isEmpty(memberLoginInfo.getMemberCardNo()) || StringUtils.isEmpty(memberLoginInfo.getPhoneNumber())) {
                log.info("用户openid=={}在缓存KEY=={}中取不到卡号信息，需要获取手机号", openid, refreshToken);
                String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
                redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
                return Result.ok("10000", "需获取手机号获取会员信息", userLoginVO);
            }
            String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
            BeanUtils.copyProperties(memberLoginInfo, userLoginVO);
            addMemberBindInfo(memberLoginInfo); // 老卡新增绑定信息
            redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
            userLoginInfoMapper.editMemberInfo(memberLoginInfo);
            return Result.success(userLoginVO);
        } else { // 首次 / 登出 / refreshToken 过期
            MemberLoginInfo memberLoginInfo = new MemberLoginInfo();
            memberLoginInfo.setCreatetime(LocalDateTime.now().format(dateTimeFormatter));
            memberLoginInfo.setModifytime(LocalDateTime.now().format(dateTimeFormatter));
            memberLoginInfo.setOpenid(openid);
            memberLoginInfo.setUnionid(unionid);
            memberLoginInfo.setSessionKey(sessionKey);
            memberLoginInfo.setRefreshToken(refreshToken);
            MemberLoginInfo memberLoginInfos = userLoginInfoMapper.existsByOpenId(openid);
            if (memberLoginInfos != null) { // 数据表用户信息已存在
                if (StringUtils.isEmpty(memberLoginInfos.getMemberCardNo()) || StringUtils.isEmpty(memberLoginInfos.getPhoneNumber())) {
                    log.info("用户openid=={}数据库存在数据，但是无卡号数据，需要获取手机号", openid);
                    String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
                    redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
                    userLoginInfoMapper.editMemberInfo(memberLoginInfo);
                    return Result.ok("10000", "需获取手机号获取会员信息", userLoginVO);
                }
                log.info("用户openid=={}在数据库已存在用户信息，无需获取手机号", openid);
                JSONObject reqJson = new JSONObject();
                reqJson.put("entId", crmProperties.getEntid());
                reqJson.put("cardTypeNo", "CT01");
                reqJson.put("params", memberLoginInfos.getMemberCardNo());
                JSONObject jsonObject = crmTokenUtil.postRequest(MemberRelated.QUERY_MEMBER_CARD, reqJson);
                if (!jsonObject.getBoolean("success")) {
                    userLoginInfoMapper.editMemberInfo(memberLoginInfo);
                    String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
                    redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
                    log.info("用户openid=={}信息在数据库存在,但会员卡被禁用，需获取手机号重新登录/注册", openid);
                    return Result.ok("10000", "需获取手机号获取会员信息", userLoginVO);
                }
                String memberCardNo = memberLoginInfos.getMemberCardNo();
                String memberName = memberLoginInfos.getMemberName();
                String phoneNumber = memberLoginInfos.getPhoneNumber();
                String gender = memberLoginInfos.getGender();
                String birthday = memberLoginInfos.getBirthday();
                memberLoginInfo.setPhoneNumber(phoneNumber);
                memberLoginInfo.setMemberName(memberName);
                memberLoginInfo.setMemberCardNo(memberCardNo);
                memberLoginInfo.setGender(gender);
                memberLoginInfo.setBirthday(birthday);
                memberLoginInfo.setSessionKey(sessionKey);
                BeanUtils.copyProperties(memberLoginInfo, userLoginVO);
//                return Result.ok("10000", "需根据手机号获取会员信息", userLoginVO);
                addMemberBindInfo(memberLoginInfo); // 老卡新增绑定信息
                String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
                redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
                userLoginInfoMapper.editMemberInfo(memberLoginInfo);
                return Result.success(userLoginVO);
            }
            String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
            redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
            userLoginInfoMapper.addUserLoginInfo(memberLoginInfo);
            log.info("用户openid=={}信息在数据库不存在，需获取手机号", openid);
            return Result.ok("10000", "需获取手机号获取会员信息", userLoginVO);
        }
    }

    @Override
    public Result<UserTokenInfo> reflush(String refreshToken) {
        if (redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken) == null) {
            // refreshToken 过期/不存在
            log.info("refreshToken=={} 已经过期/不存在,需重新登录", refreshToken);
            return Result.error("重新登录");
        } else {
            String memberLoginInfoStr = redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken).toString();
            MemberLoginInfo memberLoginInfo = JSONObject.parseObject(memberLoginInfoStr, MemberLoginInfo.class);
            String openid = memberLoginInfo.getOpenid();
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.USER_ID, openid);
            UserTokenInfo userTokenInfo = new UserTokenInfo();
            String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
            userTokenInfo.setAccessToken(token);
            log.info("用户openid=={}通过{}获取新的access_token=={}", openid, refreshToken, token);
            Claims parsedClaims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long expireTimestamp = parsedClaims.getExpiration().getTime() / 1000; // 转为秒级时间戳
            userTokenInfo.setExpireTimestamp(expireTimestamp);
            return Result.success(userTokenInfo);
        }
    }

    @Override
    public Result logout() {
        String openid = BaseContext.getCurrentId();
        String refreshToken = RefreshTokenUtil.encryptOpenId(openid);
        log.info("用户openid=={}登出,清除refreshToken=={}", openid, refreshToken);
        String memberLoginInfoStr = redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken).toString();
        MemberLoginInfo memberLoginInfo = JSONObject.parseObject(memberLoginInfoStr, MemberLoginInfo.class);
        userLoginInfoMapper.delUserLoginInfo(memberLoginInfo.getOpenid());
        redisTemplate.delete(WECHAT_USER_INFO + refreshToken);
        return Result.success();
    }

    @Override
    public Result wechatPhoneLogin(WechatMinappPhoneVPO phoneData, HttpServletRequest request) {
        String encryptedData = phoneData.getEncryptedData();
        String iv = phoneData.getIv();
        String openid = BaseContext.getCurrentId();
        String refreshToken = RefreshTokenUtil.encryptOpenId(openid);
        if (redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken) != null) {

            String memberLoginInfoStr = redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken).toString();
            MemberLoginInfo memberLoginInfo = JSONObject.parseObject(memberLoginInfoStr, MemberLoginInfo.class);
            String unionid = memberLoginInfo.getUnionid();
            String sessionKey = memberLoginInfo.getSessionKey();
            try {
                String phoneNumberJson = WeChatDecryptor.decrypt(encryptedData, iv, sessionKey);
                String phoneNumber = JSONObject.parseObject(phoneNumberJson).getString("phoneNumber");
                JSONObject reqJson = new JSONObject();
                reqJson.put("entId", crmProperties.getEntid());
                reqJson.put("cardTypeNo", "CT01");
                reqJson.put("params", phoneNumber);
                JSONObject jsonObject = crmTokenUtil.postRequest(MemberRelated.QUERY_MEMBER_CARD, reqJson);
                log.info("用户openid=={},phoneNumber=={} CRM查询会员信息返回：{}", openid, phoneNumber, jsonObject);
                UserLoginVO userLoginVO = new UserLoginVO();
                if (jsonObject.getBoolean("success")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    userLoginVO.setMemberCardNo(data.getString("memberCardNo"));
                    userLoginVO.setMemberName(data.getString("memberName"));
                    userLoginVO.setOpenid(openid);
                    userLoginVO.setUnionId(unionid);
                    userLoginVO.setPhoneNumber(phoneNumber);
                    userLoginVO.setBirthday(data.getString("birthday"));
                    userLoginVO.setGender(data.getString("sex"));
                    userLoginVO.setRefreshToken(refreshToken);
                    userLoginVO.setAccessToken(request.getHeader("accessToken"));
                    // 3. 获取过期时间戳（秒级）
                    Claims parsedClaims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), request.getHeader("accessToken"));
                    Long expireTimestamp = parsedClaims.getExpiration().getTime() / 1000; // 转为秒级时间戳
                    userLoginVO.setExpireTimestamp(expireTimestamp);
                    memberLoginInfo.setMemberCardNo(data.getString("memberCardNo"));
                    memberLoginInfo.setMemberName(data.getString("memberName"));
                    memberLoginInfo.setModifytime(LocalDateTime.now().format(dateTimeFormatter));
                    memberLoginInfo.setPhoneNumber(phoneNumber);
                    memberLoginInfo.setBirthday(data.getString("birthday"));
                    memberLoginInfo.setGender(data.getString("sex"));
                    memberLoginInfo.setRefreshToken(refreshToken);
                    addMemberBindInfo(memberLoginInfo); // 老卡新增绑定信息
                    String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
                    redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
                    userLoginInfoMapper.editMemberInfo(memberLoginInfo);
                    return Result.success(userLoginVO);
                } else {
                    memberLoginInfo.setModifytime(LocalDateTime.now().format(dateTimeFormatter));
                    memberLoginInfo.setPhoneNumber(phoneNumber);
                    memberLoginInfo.setRefreshToken(refreshToken);
                    String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
                    redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
                    userLoginInfoMapper.editMemberInfo(memberLoginInfo);
                    log.info("用户openid=={} phoneNumber={} 未办卡，去注册", openid, phoneNumber);
                    return Result.error("10001", "未查到会员信息，去注册");
                }
            } catch (Exception e) {
                log.info("用户openid=={}解密手机号失败,解密参数phoneData=={},sessionkey=={}", openid, phoneData, sessionKey);
                return Result.error("解密失败，需重新登录");
            }
        } else {
            log.info("用户openid=={}缓存异常，需重新登录", openid);
            return Result.error("未查询到登录信息，需重新登录");
        }
    }


    /**
     * 调用微信接口服务，获取微信用户的openid
     *
     * @param code
     * @return
     */
    private JSONObject getOpenid(String code) {
        //调用微信接口服务，获得当前微信用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        JSONObject jsonObject = JSON.parseObject(json);
        log.info("登录入参code=={}, api返回结果: {}", code, jsonObject);
        return jsonObject;
    }

    @Override
    public Result register(RegisterInfoPO registerInfoPO, HttpServletRequest request) {
        if (StringUtils.isEmpty(registerInfoPO.getStoreCode())) {
            Result.error("10003", "门店id不能为空!");
        }
        if (StringUtils.isEmpty(registerInfoPO.getMemberName())) {
            registerInfoPO.setMemberName("微信用户");
        }
        if (StringUtils.isEmpty(registerInfoPO.getGender())) {
            registerInfoPO.setGender("0");
        }
        log.info("用户注册办卡信息：{}", registerInfoPO);
        String openid = BaseContext.getCurrentId();
        String refreshToken = RefreshTokenUtil.encryptOpenId(openid);
        String memberLoginInfoStr = redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken).toString();
        MemberLoginInfo memberLoginInfo = JSONObject.parseObject(memberLoginInfoStr, MemberLoginInfo.class);
        log.info("用户openid=={}，memberLoginInfo=={}", openid, memberLoginInfo);
        //CRM用户注册/绑定
        JSONObject reqJson = new JSONObject();
        reqJson.put("entId", crmProperties.getEntid());
        reqJson.put("nickName", registerInfoPO.getMemberName());
        reqJson.put("memberName", registerInfoPO.getMemberName());
        reqJson.put("unionid", memberLoginInfo.getUnionid());
        reqJson.put("openid", memberLoginInfo.getOpenid());
        reqJson.put("cardTypeNo", "CT01");
        reqJson.put("memberPhone", memberLoginInfo.getPhoneNumber());
        reqJson.put("channel", "5");
        reqJson.put("storeCode", registerInfoPO.getStoreCode());
        JSONObject jsonObject = crmTokenUtil.postRequest("/app/rm_vip/createVipForApplet", reqJson);
        JSONObject userInfo = jsonObject.getJSONObject("data");
        log.info("CRM注册返回结果: {}", jsonObject);
        if (!(boolean) jsonObject.get("success")) {
            return Result.error("10005", "CRM注册请求失败!");
        }
        //没有用户信息
        if (userInfo == null) {
            return Result.error("10005", "CRM注册请求失败!");
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setMemberCardNo(userInfo.getString("memberCardNo"));
        userLoginVO.setMemberName(registerInfoPO.getMemberName());
        userLoginVO.setOpenid(openid);
        userLoginVO.setUnionId(memberLoginInfo.getUnionid());
        userLoginVO.setPhoneNumber(memberLoginInfo.getPhoneNumber());
        userLoginVO.setBirthday(registerInfoPO.getBirthday());
        userLoginVO.setGender(registerInfoPO.getGender());
        userLoginVO.setRefreshToken(refreshToken);
        userLoginVO.setAccessToken(request.getHeader("accessToken"));
        // 3. 获取过期时间戳（秒级）
        Claims parsedClaims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), request.getHeader("accessToken"));
        Long expireTimestamp = parsedClaims.getExpiration().getTime() / 1000; // 转为秒级时间戳
        userLoginVO.setExpireTimestamp(expireTimestamp);
        memberLoginInfo.setMemberCardNo(userInfo.getString("memberCardNo"));
        memberLoginInfo.setMemberName(registerInfoPO.getMemberName());
        memberLoginInfo.setModifytime(LocalDateTime.now().format(dateTimeFormatter));
        memberLoginInfo.setPhoneNumber(memberLoginInfo.getPhoneNumber());
        memberLoginInfo.setBirthday(registerInfoPO.getBirthday());
        memberLoginInfo.setGender(registerInfoPO.getGender());
        String memberLoginInfoJsonValue = JSONObject.toJSONString(memberLoginInfo);
        redisTemplate.opsForValue().set(WECHAT_USER_INFO + refreshToken, memberLoginInfoJsonValue, 30, TimeUnit.DAYS);
        userLoginInfoMapper.editMemberInfo(memberLoginInfo);
        return Result.success(userLoginVO);
    }

    @Override
    public Result disable(String memberCardNo, String unionid) {
        // 释放服务专员
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("entId", "193127");
        JSONArray array = new JSONArray();
        array.add(memberCardNo);
        requestParams.put("memberCardNoList", array);
        JSONObject jsonObject3 = crmTokenUtil.postRequest("/app/rm_vip/serviceWaiterBatchCleanApiPlugin", requestParams);
        log.info("会员自主注销调用接口3释放 MemberCardNo:{} JSON:{}", memberCardNo, jsonObject3);
        // 注销
        JSONObject reqJson = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("number", memberCardNo);
        reqJson.put("data", data);
        JSONObject jsonObject = crmTokenUtil.postRequest("/v2/yn/rm_vip/rm_mb_vip_card/DisableVipCardUnCommit", reqJson);
        log.info("会员自主注销调用接口1反审核 MemberCardNo:{} JSON:{}", memberCardNo, jsonObject);
        JSONObject reqJson2 = new JSONObject();
        JSONObject data2 = new JSONObject();
        data2.put("number", memberCardNo);
        data2.put("enable", "0");
        data2.put("notes", "会员自主注销");
        reqJson2.put("data", data2);
        JSONObject jsonObject2 = crmTokenUtil.postRequest("/v2/yn/rm_vip/rm_mb_vip_card/DisableVipCard", reqJson2);
        log.info("会员自主注销调用接口2注销 MemberCardNo:{} JSON:{}", memberCardNo, jsonObject2);
        logout();
        // 解绑
        JSONObject reqJson4 = new JSONObject();
        reqJson4.put("entId", "193127");
        reqJson4.put("unionid", unionid);
        reqJson4.put("memberCardNo", memberCardNo);
        JSONObject jsonObject4 = crmTokenUtil.postRequest("/app/rm_vip/unBindAppletVip", reqJson4);
        log.info("会员自主注销调用接口4解绑 MemberCardNo:{} JSON:{}", memberCardNo, jsonObject4);
        return Result.success("注销成功！");
    }


    /**
     * 老会员新增绑定记录
     */
    private void addMemberBindInfo(MemberLoginInfo memberInfo) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("entId", crmProperties.getEntid());
        reqJson.put("nickName", memberInfo.getMemberName());
        reqJson.put("memberName", memberInfo.getMemberName());
        reqJson.put("unionid", memberInfo.getUnionid());
        reqJson.put("openid", memberInfo.getOpenid());
        reqJson.put("cardTypeNo", "CT01");
        reqJson.put("memberPhone", memberInfo.getPhoneNumber());
        reqJson.put("channel", "5");
        JSONObject jsonObject = crmTokenUtil.postRequest("/app/rm_vip/createVipForApplet", reqJson);
        log.info("会员新增绑定记录结果: {}", jsonObject);
    }
}
