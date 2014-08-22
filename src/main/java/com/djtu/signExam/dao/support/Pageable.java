package com.djtu.signExam.dao.support;

public class Pageable {
	
	private int currentPage;
	private int pageSize;
	private int pageCount;
	private int offset;
	
	public Pageable(int currentPage,int pageSize){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public static Pageable inPage(int currentPage,int pageSize){
		return new Pageable(currentPage, pageSize);
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getOffset() {
        if(this.pageCount != 0 && this.pageSize != 0 && this.currentPage != 0){
            this.offset = (this.currentPage-1)*pageSize;
            return this.offset;
        }
		return 0;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	

}
