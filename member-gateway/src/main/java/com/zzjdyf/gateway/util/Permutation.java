package com.zzjdyf.gateway.util;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.URLUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author awlwen
 * @since 2017/5/15.
 */
public class Permutation {
    public static String sort(Map payloadMap)  {
        Map<String,String> sortedMap = new TreeMap<String,String>();
        if(payloadMap != null && payloadMap.size() > 0){
            sortedMap.putAll(payloadMap);
        }
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> entrySet = sortedMap.entrySet();
        boolean hasParam = false;
        for(Map.Entry<String, String> entry : entrySet){
            String name = entry.getKey();
            String valStr = null;
            Object value = entry.getValue();
            if(value instanceof Map){
                valStr = sort((Map)value);
            }else if(value instanceof List){
                valStr = sortList((List)value);
            }else{
                valStr = value == null ? "" : value.toString();
            }
            if(hasParam){
                sb.append("&");
            }else{
                hasParam = true;
            }
            sb.append(name).append("=").append(valStr);
        }
        return sb.toString();
    }

    private static String sortList(List value) {
        StringBuilder listStr = new StringBuilder();
        List list = value;
        for(int i = 0; i < list.size(); i ++){
            if(list.get(i) instanceof Map){
                listStr.append(sort((Map)list.get(i)));
            }else if(list.get(i) instanceof List){
                listStr.append(sortList((List) list.get(i)));
            }else{
                listStr.append(list.get(i) == null ? "" : list.get(i).toString());
            }
        }
        return listStr.toString();
    }

    public static String sortWithoutList(Map payloadMap)  {
        Map<String,String> sortedMap = new TreeMap<String,String>();
        if(payloadMap != null && payloadMap.size() > 0){
            sortedMap.putAll(payloadMap);
        }
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> entrySet = sortedMap.entrySet();
        boolean hasParam = false;
        for(Map.Entry<String, String> entry : entrySet){
            String name = entry.getKey();
            String valStr = null;
            Object value = entry.getValue();
            if(value instanceof Map){
                valStr = sort((Map)value);
            }else{
                valStr = value == null ? "" : value.toString();
            }
            if(hasParam){
                sb.append("&");
            }else{
                hasParam = true;
            }
            sb.append(name).append("=").append(valStr);
        }
        return sb.toString();
    }
}
