package com.cheshun.common.tools.utils;


import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.api.web.http.common.ListResult;
import com.cheshun.common.api.web.http.common.PageResult;
import com.cheshun.common.api.web.http.common.UsualResult;
import com.cheshun.common.tools.utils.api.ObjectConverter;
import com.cheshun.common.result.Result;

import java.util.ArrayList;
import java.util.List;

import static com.cheshun.common.result.Result.SUCC_CODE;


public class CommonResultUtil {
	
	public static <T,V> ListResult<T> convertListResult(ListResult<V> ls, ObjectConverter<V, T> converter){
		if(ls == null) {
			return null;
		}
		ListResult<T> t = new ListResult<T>();
		copyListResultPageInfo(ls, t);
		List<V> list = ls.getListData();
		if(list == null) {
			t.setListData(null);
		}else {
			List<T> ts = new ArrayList<T>(list.size());
			for(V v : list) {
				ts.add(converter.convert(v));
			}
			t.setListData(ts);
		}
		return t;
	}
	
	/**
	 * 仅复制分页信息.
	 * @param fr
	 * @param to
	 */
	public static void copyListResultPageInfo(ListResult<?> fr, ListResult<?> to) {
		to.setFirstNumber(fr.getFirstNumber());
		to.setIsPage(fr.getIsPage());
		to.setLastNumber(fr.getLastNumber());
		to.setNextNumber(fr.getNextNumber());
		to.setPageNumber(fr.getPageNumber());
		to.setPageSize(fr.getPageSize());
		to.setPreNumber(fr.getPreNumber());
		to.setTotalNumbers(fr.getTotalNumbers());
		to.setTotalPages(fr.getTotalPages());
	}
	
	public static void copyPageResult(PageResult<?> fr, PageResult<?> to) {
		to.setNextPage(fr.getNextPage());
		to.setPageNumber(fr.getPageNumber());
		to.setPageSize(fr.getPageSize());
	}
	/**
	 * 复制操作结果
	 * @param fr
	 * @param to
	 */
	public static void copyOptResult(Result fr, Result to) {
		to.setMessage(fr.getMessage());
		to.setModelCode(fr.getModelCode());
		to.setSuccess(fr.getSuccess());
		to.setOptCode(fr.getOptCode());
		to.setUniqueCode(fr.getUniqueCode());
	}
	
	public static <T> ListResult<T> listResult(Result opt) {
		return new ListResult<T>(opt, null);
	}
	
	public static <T> ListResult<T> listResult(List<T> list){
		return new ListResult<T>(UsualResult.SUCCESS, list);
	}
	
	public static <T> ListResult<T> listResult(Result opt, List<T> list){
		return new ListResult<T>(opt, list); 
	}
	
	public static <T, V> EntityResult<T> convertEntityResult(EntityResult<V> ent, ObjectConverter<V, T> converter){
		return entResult(ent, converter.convert(ent.getEntityData()));
	}
	
	public static <T> EntityResult<T> entResult(Result opt, T entity){
		if(opt == null) {
			opt = UsualResult.SUCCESS;
		}
		return new EntityResult<T>(opt, entity);
	}
	
	public static <T> EntityResult<T> entResult(T entity){
		return entResult(null, entity);
	}
	
	public static <T> EntityResult<T> entResult(Result opt){
		return entResult(opt, null);
	}


	public static <T> Result<T> result(String code, String message, T entity){
		return new Result<T>(code, message, entity);
	}

	public static <T> Result<T> result(T entity){
		return result(SUCC_CODE, null, entity);
	}

	public static <T> Result<T> result(String code, String message){
		return result(code,  message, null);
	}


	public static <T> PageResult<T> pageResult(Result opt){
		return new PageResult<T>(opt);
	}
	
	public static <T> PageResult<T> pageResult(Result opt, List<T> list) {
		return new PageResult<T>(opt, list, false, null, null);
	}
	
}
