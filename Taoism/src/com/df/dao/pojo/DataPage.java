package com.df.dao.pojo;

import java.util.List;

import com.df.dao.util.ClientPage;

public class DataPage<T> {
	private List<T> tList;
	private List<ClientPage> cList;
	private Page page;
	
	
	
	public DataPage() {
		super();
	}
	public DataPage(List<T> tList, List<ClientPage> cList, Page page) {
		super();
		this.tList = tList;
		this.cList = cList;
		this.page = page;
	}
	public List<T> gettList() {
		return tList;
	}
	public void settList(List<T> tList) {
		this.tList = tList;
	}
	public List<ClientPage> getcList() {
		return cList;
	}
	public void setcList(List<ClientPage> cList) {
		this.cList = cList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
