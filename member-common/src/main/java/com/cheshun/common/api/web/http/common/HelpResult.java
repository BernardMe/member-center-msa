package com.cheshun.common.api.web.http.common;

import com.cheshun.common.result.Result;

public class HelpResult {

	public static Result optResult(long count) {
		if (count == 0) {
			return UsualResult.FAILURE;
		}
		if (count == -1) {
			return UsualResult.EXCEPTION;
		}
		if (count >= 1) {
			return UsualResult.SUCCESS;
		}
		return UsualResult.FAILURE;
	}
}
