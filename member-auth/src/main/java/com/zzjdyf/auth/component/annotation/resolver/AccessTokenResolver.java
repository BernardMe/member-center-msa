package com.zzjdyf.auth.component.annotation.resolver;

import com.zzjdyf.auth.component.annotation.AccessToken;
import com.zzjdyf.auth.service.member.core.common.CommonAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AccessTokenResolver implements HandlerMethodArgumentResolver {
	
	@Autowired
	private CommonAccessService service;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(String.class)
				&& parameter.hasParameterAnnotation(AccessToken.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return service.getAccessToken().getEntityData();
	}

}
