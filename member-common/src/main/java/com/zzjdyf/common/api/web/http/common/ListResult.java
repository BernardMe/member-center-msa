package com.zzjdyf.common.api.web.http.common;

import com.zzjdyf.common.result.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 视图列表实体--controller层返回结果
 * @author Administrator
 *
 * @param <T>
 */
public class ListResult<T> extends Result {
	private Boolean isPage;
	private Integer totalPages;
	private Long totalNumbers;
	private Integer pageSize;
	private Integer pageNumber;
	private Integer preNumber;
	private Integer nextNumber;
	private Integer firstNumber;
	private Integer lastNumber;
	private List<T> listData;
	public ListResult() {
		this(UsualResult.SUCCESS, null);
	}
	public ListResult(Result opt, List<T> list) {
		super(opt);
		setList(list);
	}
	public ListResult(List<T> list) {
		this(null, list);
	}
	public ListResult(Result opt) {
		this(opt, null);
	}
	private void setList(List<T> list) {
		if(list == null) {
			list = new ArrayList<T>();
		}
		isPage = false;
		this.listData = list;
	}
	public Boolean getIsPage() {
		return isPage;
	}
	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalNumbers() {
		return totalNumbers;
	}
	public void setTotalNumbers(Long totalNumbers) {
		this.totalNumbers = totalNumbers;
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
	public List<T> getListData() {
		return listData;
	}
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	public Integer getPreNumber() {
		return preNumber;
	}
	public void setPreNumber(Integer preNumber) {
		this.preNumber = preNumber;
	}
	public Integer getNextNumber() {
		return nextNumber;
	}
	public void setNextNumber(Integer nextNumber) {
		this.nextNumber = nextNumber;
	}
	public Integer getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(Integer firstNumber) {
		this.firstNumber = firstNumber;
	}
	public Integer getLastNumber() {
		return lastNumber;
	}
	public void setLastNumber(Integer lastNumber) {
		this.lastNumber = lastNumber;
	}
}
