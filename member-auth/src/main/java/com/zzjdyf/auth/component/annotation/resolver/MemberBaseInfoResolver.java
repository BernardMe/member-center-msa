package com.zzjdyf.auth.component.annotation.resolver;

import com.zzjdyf.auth.service.member.core.base.MemberBaseInfoService;
import com.zzjdyf.auth.vo.dto.MemberLoginInfo;
import com.zzjdyf.common.annotation.MemberBaseParam;
import com.zzjdyf.common.api.web.http.common.EntityResult;
import com.zzjdyf.common.constant.user.MemberHttpHeader;
import com.zzjdyf.common.exception.MemberUnloginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class MemberBaseInfoResolver implements HandlerMethodArgumentResolver {
	
	@Autowired
	private MemberBaseInfoService service;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(MemberLoginInfo.class)
				&& parameter.hasParameterAnnotation(MemberBaseParam.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//根据TOKEN获取用户信息。
		EntityResult<MemberLoginInfo> userEntity = service.getMemberBaseInfoByToken(webRequest.getHeader(MemberHttpHeader.ACCESS_TOKEN.getHeader()));
		//获取失败或无内容则表示未登录。
		if(!userEntity.getSuccess() || userEntity.getEntityData() == null) {
			//判断是否依赖
			MemberBaseParam userAnno = parameter.getParameterAnnotation(MemberBaseParam.class);
			if(userAnno.required()) {
				throw new MemberUnloginException();
			}else {
				return null;
			}
		}
		return userEntity.getEntityData();
	}


}
