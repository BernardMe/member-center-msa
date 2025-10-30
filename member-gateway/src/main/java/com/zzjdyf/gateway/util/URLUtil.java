package com.zzjdyf.gateway.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xueqing wang on 2021/4/28 17:36
 */
public class URLUtil extends cn.hutool.core.util.URLUtil {

    public static Map<String, List> getQueryParams(String url) {
        try {
            Map<String, List> params = new HashMap();

            String[] urlParts = url.split("\\?");

            if (urlParts.length > 1) {
                String query = urlParts[1];

                for (String param : query.split("&")) {
                    String[] pair = param.split("=");

                    String key = decode(pair[0]);

                    String value = "";

                    if (pair.length > 1) {
                        value = decode(pair[1]);

                    }

                    List values = params.get(key);

                    if (values == null) {
                        values = new ArrayList();

                        params.put(key, values);

                    }

                    values.add(value);

                }

            }

            return params;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }


    public static Map<String, List> getQueryParamsNo(String url) {
        try {
            Map<String, List> params = new HashMap();
            //字符串中即使没有 & ，使用split分割，string[]长度也是1，仍然会走下面的for循环，先在前面判断是否包含& yyj
            if (!url.contains("&")) {
                return params;
            }

            String query = url;

            for (String param : query.split("&")) {
                String[] pair = param.split("=");

                String key = pair[0];

                String value = "";

                if (pair.length > 1) {
                    value = pair[1];

                }

                List values = params.get(key);

                if (values == null) {
                    values = new ArrayList();

                    params.put(key, values);

                }

                values.add(value);


            }

            return params;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}

