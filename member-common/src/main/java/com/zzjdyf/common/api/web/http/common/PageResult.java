package com.zzjdyf.common.api.web.http.common;

import com.zzjdyf.common.result.Result;

import java.util.List;

/**
 * 视图分页实体--controller层返回结果
 * @author Administrator
 *
 * @param <T>
 */
public class PageResult<T> extends Result {
	private List<T> pageInfo;
	private Integer pageSize;
	private Integer pageNumber;
	private Boolean nextPage;
	public PageResult() {
		super();
	}
	public PageResult(Result opt) {
		super(opt);
	}
	public PageResult(Result opt, List<T> pageInfo, Boolean nextPage, Integer pageSize, Integer pageNumber) {
		super(opt);
		this.pageInfo = pageInfo;
		this.nextPage = nextPage;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	public List<T> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(List<T> pageInfo) {
		this.pageInfo = pageInfo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Boolean getNextPage() {
		return nextPage;
	}
	public void setNextPage(Boolean nextPage) {
		this.nextPage = nextPage;
	}
}
