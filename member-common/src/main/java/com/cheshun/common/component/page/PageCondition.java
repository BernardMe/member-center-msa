package com.cheshun.common.component.page;

public class PageCondition {
	private Boolean isPage;
	private Integer pageSize;
	private Integer pageNum;
	public PageCondition() {
		isPage = true;
		pageSize = 10;
		pageNum = 1;
	}
	public PageCondition(boolean isPage) {
		if(isPage) {
			this.isPage = true;
			pageNum = 1;
			pageSize = 10;
		}else {
			this.isPage = false;
		}
	}
	public PageCondition(boolean isPage, Integer pageNum, Integer pageSize) {
		this.pageSize = pageSize;
		this.isPage = isPage;
		this.pageNum = pageNum;
	}
	public Boolean getIsPage() {
		return isPage;
	}
	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
}
