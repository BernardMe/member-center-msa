package com.zzjdyf.common.constant;

import com.zzjdyf.common.api.CommonConstant;
import com.zzjdyf.common.api.web.http.common.UsualResult;
import com.zzjdyf.common.result.Result;


public interface CommonServiceConstant extends CommonResultConstant, UsualResult, CommonConstant {
	
	Result PE = PARAM_EXCEPTION;
	
	Result SU = SUCCESS;
	Result FA = FAILURE;

	String TRUE = TAG_TRUE;
	String FAL = TAG_FALSE;

	String DCS = DEFAULT_CHARSET;
}
