package com.cheshun.mall.service.member.core.common.impl;

import com.cheshun.mall.service.member.core.common.CommonAccessService;
import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.constant.CommonServiceConstant;
import com.cheshun.common.constant.user.MemberHttpHeader;
import com.cheshun.common.tools.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class CommonAccessServiceImpl implements CommonAccessService, CommonServiceConstant {

	//private final String ACCESS_KEY = "accessToken";

	//private Logger log;

//	@Resource(name = RedisConfig.REDIS_NAME_MULTIPLE)
//	private RedisTemplate<String, UserSessionStorageEntity> redis;
//	private HashOperations<String, String, UserSessionStorageEntity> hash;
//
//	private Set<String> privateKeys;

	public CommonAccessServiceImpl() {
		/*log = LoggerFactory.getLogger(getClass());
		privateKeys = new HashSet<String>();
		for(CommonSessionStorageKey key : CommonSessionStorageKey.values()) {
			if(!key.isPublic()) {
				privateKeys.add(key.getKey());
			}
		}*/
	}
	
	@Override
	public EntityResult<String> getAccessToken() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = attrs.getRequest();
		HttpServletResponse resp = attrs.getResponse();
		//首先从请求中获取会话TOKEN.
		String token = req.getHeader(MemberHttpHeader.ACCESS_TOKEN.getHeader());
		log.info("获取sessionToken,reqToken = {}", token);
		if(ObjectUtils.isEmpty(token)) {
			//尝试从返回结果中获取会话TOKEN
			token = resp.getHeader(MemberHttpHeader.SET_SESSION_TOKEN.getHeader());
			log.info("获取sessionToken,resToken = {}", token);
		}
		//redis.expire(ACCESS_KEY + token, OVER_TIME, TimeUnit.HOURS);
		return ResultUtil.entResult(token);
	}

//	@Override
//	public EntityResult<String> createSessionStorage() {
//		//生成token.
//		String token = generateSessionToken();
//		UserSessionStorageEntity data = new UserSessionStorageEntity(token);
//		getHash().put(SESSION_KEY + token, UserSessionStorageConstant.KEY_CREATE, data);
//		//3个小时后过期
//		redis.expire(SESSION_KEY + token, OVER_TIME, TimeUnit.HOURS);
//		return ResultUtil.entResult(token);
//	}
//
//	@Override
//	public EntityResult<String> getPublicStorage(String token, String key) {
//		if(!StringUtil.checkStrings(token, key)) {
//			return ResultUtil.entResult(PE);
//		}
//		//私有检测
//		if(privateKeys.contains(key)) {
//			return ResultUtil.entResult(SU);
//		}
//		UserSessionStorageEntity data = getHash().get(SESSION_KEY + token, key);
//		if(data == null) {
//			return ResultUtil.entResult(SU);
//		}
//		//无限时间
//		if(data.getOverDateTime() == null) {
//			return ResultUtil.entResult(data.getValue());
//		}else {
//			LocalDateTime now = LocalDateTime.now();
//			//超时
//			if(now.isAfter(data.getOverDateTime())) {
//				this.deleteStorage(token, key);
//				return ResultUtil.entResult(SU);
//			}else {
//				return ResultUtil.entResult(data.getValue());
//			}
//		}
//	}
//
//	@Override
//	public EntityResult<String> getStorage(String token, String key) {
//		if(!StringUtil.checkStrings(token, key)) {
//			return ResultUtil.entResult(PE);
//		}
//		UserSessionStorageEntity data = getHash().get(SESSION_KEY + token, key);
//		if(data == null) {
//			return ResultUtil.entResult(SU);
//		}
//		//无限时间
//		if(data.getOverDateTime() == null) {
//			return ResultUtil.entResult(data.getValue());
//		}else {
//			LocalDateTime now = LocalDateTime.now();
//			//超时
//			if(now.isAfter(data.getOverDateTime())) {
//				this.deleteStorage(token, key);
//				return ResultUtil.entResult(SU);
//			}else {
//				return ResultUtil.entResult(data.getValue());
//			}
//		}
//	}
	
//	private HashOperations<String, String, UserSessionStorageEntity> getHash() {
//		if(hash == null) {
//			hash = redis.opsForHash();
//		}
//		return hash;
//	}
//
//	private String generateSessionToken() {
//		return TOKEN_HEAD + CodeUtil.createRandowCharString("", 32);
//	}

}
