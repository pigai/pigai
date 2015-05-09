package com.pigai.util;

import com.pigai.constant.Constants;

public class PageModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int currentIndex;

	private int pageSize=Constants.PAGE_Size;

	private int totalRecord;

	@SuppressWarnings("unused")
	private int offset;

	private java.lang.Object pageData;

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
		if (this.currentIndex < 1) {
			this.currentIndex = 1;
		}

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getOffset() {
		return (currentIndex-1)*pageSize;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public java.lang.Object getPageData() {
		return pageData;
	}

	public void setPageData(java.lang.Object pageData) {
		this.pageData = pageData;
	}

	public int getPrevious() {
		return currentIndex - 1;
	}

	public int getNext() {
		return currentIndex + 1;
	}

}