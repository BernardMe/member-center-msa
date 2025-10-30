package com.zzjdyf.mall.service.impl;

import com.zzjdyf.mall.service.SkuService;
import com.zzjdyf.mall.vo.SmsRecordVo;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.common.result.Result;
import com.zzjdyf.common.tools.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zzjdyf.common.result.Result.ERR_CODE;

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
