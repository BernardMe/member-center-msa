package com.zzjdyf.common.api.web.http.common;

import com.zzjdyf.common.result.Result;

/**
 * 仅成功和失败.
 *
 * @author Administrator
 */
public interface UsualResult {
	Result SUCCESS = new Result(200, true, "操作成功!");
	Result FAILURE = new Result(300, false, "操作失败!");
	Result NOT_EXIST = new Result(420, false, "数据不存在!");
	Result TO_MANY = new Result(421, false, "数据多于一条!");
	Result HAS_EXIST = new Result(422, false, "数据已存在!");
	Result SIGN_ERROR = new Result(511, false, "签名异常！");
	Result NONE_ACCESS = new Result(512, false, "无访问权限！");
	Result EXCEPTION = new Result(513, false, "操作异常！");
}
