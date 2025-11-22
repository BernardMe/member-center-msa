package com.cheshun.common.exception;

/**
 * 会员还未登录异常
 * @author Administrator
 *
 */
public class MemberUnloginException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 8162104756817519364L;

	public MemberUnloginException() {
		super("会员还未登录！");
	}
}
