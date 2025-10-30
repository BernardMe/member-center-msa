package com.zzjdyf.auth.service.member.core.oms.api;

import com.zzjdyf.common.constant.HttpMethod;

import java.util.Objects;

public class OmsUrl {
	
	private static final String OMS_HEAD = "https://oms.zzjdyf.com/noms/v2";
	private HttpMethod method;
	private String relativeUrl;
	private String absoluteUrl;
	
	public OmsUrl(String method, String relativeUrl) {
		Objects.requireNonNull(method);
		Objects.requireNonNull(relativeUrl);
		this.method = HttpMethod.valueOf(method.toUpperCase());
		this.relativeUrl = relativeUrl;
		absoluteUrl = OMS_HEAD + relativeUrl;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public String getAbsoluteUrl() {
		return absoluteUrl;
	}
}
