package com.cheshun.auth.service.member.core.oms.impl;

import com.cheshun.auth.service.member.core.oms.OmsUserInfoService;
import com.cheshun.auth.service.member.core.oms.api.OmsUserInfoApi;
import com.cheshun.auth.service.member.core.oms.entity.OmsUserInfoVO;
import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.constant.CommonServiceConstant;
import com.cheshun.common.tools.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmsUserInfoServiceImpl implements OmsUserInfoService, CommonServiceConstant {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public EntityResult<OmsUserInfoVO> queryOmsUserInfo(String userPhone) {

		if(userPhone == null || userPhone.length() == 0) {
			return ResultUtil.entResult(PE);
		}
		//组装URL
		StringBuilder bui = new StringBuilder();
		bui.append(OmsUserInfoApi.QUERY_USER_INFO_BY_PHONE.getAbsoluteUrl());
		bui.append("?phone=");
		bui.append(userPhone);

		//查询OMS
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(bui.toString(), String.class);
		String jsonStr = responseEntity.getBody();
		//解析结果
		EntityResult<OmsUserInfoVO> omsUserInfoVOEntityResult = ResultUtil.parseEntityResult(jsonStr, OmsUserInfoVO.class);
		if(!omsUserInfoVOEntityResult.getSuccess()) {
			ResultUtil.listResult(omsUserInfoVOEntityResult);
		}
		return omsUserInfoVOEntityResult;
	}
}
