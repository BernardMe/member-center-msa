package com.zzjdyf.auth.service.member.core.base.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzjdyf.auth.service.member.core.base.MemberBaseInfoService;
import com.zzjdyf.auth.vo.dto.MemberLoginInfo;
import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.constant.CommonServiceConstant;
import com.cheshun.common.context.BaseContext;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.RefreshTokenUtil;
import com.cheshun.common.tools.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.zzjdyf.auth.service.user.login.impl.UserServiceImpl.WECHAT_USER_INFO;

@Service
public class MemberBaseInfoServiceImpl implements MemberBaseInfoService, CommonServiceConstant {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

//	private ValueOperations<String, String> baseValue;
	
//	@Autowired
//	private SystemResultService resultService;
	
//	private String BASE_TOKEN;

//	private boolean isDebug;
//	private String debugToken;
	
	public MemberBaseInfoServiceImpl() {
		//BASE_TOKEN = UserLoginConstant.KEY_USER_BASE;
		//isDebug = Boolean.valueOf(DefaultSystemConfig.getInstance().getProperties().getProperty("user.base.debug", "false"));
		//debugToken = DefaultSystemConfig.getInstance().getProperties().getProperty("user.base.token");
	}
	
	@Override
	public EntityResult<MemberLoginInfo> getMemberBaseInfoByToken(String userToken) {
		//根据openId获取MemberLoginInfo对象
		String openid = BaseContext.getCurrentId();
		String refreshToken = RefreshTokenUtil.encryptOpenId(openid);
		if (redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken) != null) {

			String memberLoginInfoStr = redisTemplate.opsForValue().get(WECHAT_USER_INFO + refreshToken).toString();
			MemberLoginInfo memberLoginInfo = JSONObject.parseObject(memberLoginInfoStr, MemberLoginInfo.class);

			return ResultUtil.entResult(memberLoginInfo);
		}
		return ResultUtil.entResult(new Result<>(300, false, "会员还未登录！！！"));
	}

//	@Override
//	public Result saveUserBaseInfo(MemberLoginInfo base) {
//		Result save = onlySaveUserBaseInfo(base);
//		if(save.getSuccess()) {
//			//获取当前http请求
//			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		    HttpServletResponse response = servletRequestAttributes.getResponse();
//		    response.addHeader(MemberHttpHeader.USER_BASE_INFO_UPDATE.getHeader(), Boolean.TRUE.toString());
//		}
//		return save;
//	}
//
//	@Override
//	public Result onlySaveUserBaseInfo(MemberLoginInfo base) {
//		if(base == null) {
//			return PE;
//		}
//		//当存在TOKEN时设置值。
//		Boolean hasSave = getBaseValue().setIfPresent(BASE_TOKEN + base.getRefreshToken(), JSONObject.toJSONString(base));
//		if(hasSave) {
//			return SU;
//		}else {
//			return PE;
//		}
//	}
//
//	@Override
//	public Result storeUserBaseInfo(MemberLoginInfo base) {
//		if(base == null) {
//			return PE;
//		}
//		getBaseValue().setIfAbsent(BASE_TOKEN + base.getRefreshToken(), JSONObject.toJSONString(base));
//		return SU;
//	}
//
//	private ValueOperations<String, String> getBaseValue() {
//		if(baseValue == null) {
//			baseValue = baseRedis.opsForValue();
//		}
//		return baseValue;
//	}
//
//	@Override
//	public Result deleteUserInfoByToken(String userToken) {
//		//删除Redis
//		baseRedis.delete(BASE_TOKEN + userToken);
//		return SU;
//	}

}
