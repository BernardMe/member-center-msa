package com.zzjdyf.common.api.web.http.common;

import com.zzjdyf.common.result.Result;

/**
 * 视图实体--controller层返回结果
 * @author Administrator
 *
 * @param <T>
 */
public class EntityResult<T> extends Result {

	public EntityResult() {
		super(null);
	}

	public EntityResult(Result opt){
		this(opt,null);
	}

	public EntityResult(Result opt,T data){
		super(opt);
		this.entityData = data;
	}

	private T entityData;

	public T getEntityData() {
		return entityData;
	}

	public void setEntityData(T entityData) {
		this.entityData = entityData;
	}

}
