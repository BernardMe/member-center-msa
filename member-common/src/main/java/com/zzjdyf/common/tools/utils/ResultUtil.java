package com.zzjdyf.common.tools.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import com.github.pagehelper.PageInfo;
import com.zzjdyf.common.api.web.http.common.EntityResult;
import com.zzjdyf.common.api.web.http.common.ListResult;
import com.zzjdyf.common.api.web.http.common.PageResult;
import com.zzjdyf.common.api.web.http.common.UsualResult;
import com.zzjdyf.common.tools.utils.CommonResultUtil;

import java.util.List;

public class ResultUtil extends CommonResultUtil {
	
	public static <T> ListResult<T> listResult(PageInfo<T> pageInfo){
		ListResult<T> result = new ListResult<T>(UsualResult.SUCCESS, null);
		result.setIsPage(true);
		result.setListData(pageInfo.getList());
		result.setPageNumber(pageInfo.getPageNum());
		result.setPageSize(pageInfo.getPageSize());
		result.setTotalNumbers(pageInfo.getTotal());
		result.setTotalPages(pageInfo.getPages());
		result.setFirstNumber(pageInfo.getNavigateFirstPage());
		result.setLastNumber(pageInfo.getNavigateLastPage());
		result.setPreNumber(pageInfo.getPrePage());
		result.setNextNumber(pageInfo.getNextPage());
		return result;
	}
	
	/**
	 * 将JSON字符串解析为ListResult对象
	 * @param jsStr
	 * @return
	 */
	public static <T> ListResult<T> parseListResult(String jsStr, Class<T> cls){
		return convertListResult(JSONObject.parseObject(jsStr), cls);
	}
	/**
	 * 将JSON对象转换成ListResult对象
	 * @param js
	 * @return
	 */
	public static <T> ListResult<T> convertListResult(JSONObject js, Class<T> cls){
		ListResult<T> result = new ListResult<T>();
		if(js == null) {
			return null;
		}
		result.setOptCode(js.getInteger("optCode"));
		result.setModelCode(js.getString("modelCode"));
		result.generateUniqueCode();
		result.setFirstNumber(js.getInteger("firstNumber"));
		result.setIsPage(js.getBoolean("isPage"));
		result.setLastNumber(js.getInteger("lastNumber"));
		result.setMessage(js.getString("message"));
		result.setNextNumber(js.getInteger("nextNumber"));
		result.setPageNumber(js.getInteger("pageNumber"));
		result.setPageSize(js.getInteger("pageSize"));
		result.setPreNumber(js.getInteger("preNumber"));
		result.setSuccess(js.getBoolean("success"));
		result.setTotalNumbers(js.getLong("totalNumbers"));
		result.setTotalPages(js.getInteger("totalPages"));
		if(js.containsKey("listData")) {
			List<T> listData = JSONArray.parseArray(js.getString("listData"), cls);
			result.setListData(listData);
		}
		return result;
	}
	/**
	 * 老版方法名称
	 * @param <T>
	 * @param js
	 * @param temp
	 * @return
	 */
	@Deprecated
	public static <T> ListResult<T> converterListResult(JSONObject js, Class<T> cls){
		return convertListResult(js, cls);
	}
	/**
	 * 将JSON字符串解析为EntityResult对象
	 * @param jsStr
	 * @return
	 */
	public static <T> EntityResult<T> parseEntityResult(String jsStr, Class<T> cls){
		//当泛型是字符串的时候返回JSON泛型序列化方式
		return convertEntityResult(JSONObject.parseObject(jsStr), cls);
	}
	/**
	 * 将JSON对象转换成EntityResult对象
	 * @param js
	 * @return
	 */
	public static <T> EntityResult<T> convertEntityResult(JSONObject js, Class<T> cls){
		EntityResult<T> result = new EntityResult<T>();
		result.setOptCode(js.getInteger("optCode"));
		result.setModelCode(js.getString("modelCode"));
		result.generateUniqueCode();
		result.setMessage(js.getString("message"));
		result.setSuccess(js.getBoolean("success"));
		if(js.containsKey("data")) {
			T data = JSONObject.parseObject(js.getString("data"), cls);
			result.setData(data);
		}
		return result;
	}
	
	public static <T> PageResult<T> parsePageResult(String jsonStr, Class<T> cls) {
		return convertPageResult(JSONObject.parseObject(jsonStr), cls);
	}
	
	public static <T> PageResult<T> convertPageResult(JSONObject js, Class<T> cls) {
		PageResult<T> result = new PageResult<T>();
		result.setOptCode(js.getInteger("optCode"));
		result.setModelCode(js.getString("modelCode"));
		result.generateUniqueCode();
		result.setMessage(js.getString("message"));
		result.setSuccess(js.getBoolean("success"));
		result.setNextPage(js.getBoolean("nextPage"));
		result.setPageNumber(js.getInteger("pageNumber"));
		result.setPageSize(js.getInteger("pageSize"));
		result.setPageInfo(JSONObject.parseArray(js.getString("pageInfo"), cls));
		return result;
	}
	
	/**
	 * 老版方法名称
	 * @param <T>
	 * @param js
	 * @param temp
	 * @return
	 */
	@Deprecated
	public static <T> EntityResult<T> converterEntityResult(JSONObject js, Class<T> temp){
		return convertEntityResult(js, temp);
	}
	
}
