package com.zzjdyf.mall.service.member.core.oms;

import com.zzjdyf.mall.service.member.core.oms.entity.OmsUserInfoVO;
import com.cheshun.common.api.web.http.common.EntityResult;

public interface OmsUserInfoService {
	/**
	 * 查询OMS根据手机号查询用户详情
	 * @param userPhone
	 * @return
	 */
	EntityResult<OmsUserInfoVO> queryOmsUserInfo(String userPhone);

}
