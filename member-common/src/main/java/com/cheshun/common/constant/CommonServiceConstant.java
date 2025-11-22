package com.cheshun.common.constant;

import com.cheshun.common.api.web.http.common.UsualResult;
import com.cheshun.common.result.Result;
import com.cheshun.common.api.CommonConstant;


public interface CommonServiceConstant extends CommonResultConstant, UsualResult, CommonConstant {
	
	Result PE = PARAM_EXCEPTION;
	
	Result SU = SUCCESS;
	Result FA = FAILURE;

	String TRUE = TAG_TRUE;
	String FAL = TAG_FALSE;

	String DCS = DEFAULT_CHARSET;
}
