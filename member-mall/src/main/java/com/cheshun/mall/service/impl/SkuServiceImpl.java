package com.cheshun.mall.service.impl;

import com.cheshun.mall.service.SkuService;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.cheshun.common.result.Result.ERR_CODE;

/**
 * Sku服务实现类
 * @Create xueqing wang
 * @date 20251022
 */
@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService {


    @Override
    public String getType() {
        return "CHENG_LI_YE";
    }


    @Override
    public Result buildResult(Map map) {
        if ("1".equals(String.valueOf(map.get("resultCode")))) {
            Map<String, Object> ok = new HashMap<>();
            ok.put("message", (String.valueOf(map.get("resultMsg"))));
            ok.put("data", (String.valueOf(map.get("msgid"))));
            return ResultUtil.result(ok);
        }
        return ResultUtil.result(ERR_CODE, String.valueOf(map.get("resultMsg")));
    }



}
