package com.zzjdyf.common.constant;

public enum HttpMethod {
	GET,POST,PUT,DELETE;
	private String method;
	private HttpMethod() {
		method = this.toString();
	}
	public String getMethod() {
		return method;
	}
}
