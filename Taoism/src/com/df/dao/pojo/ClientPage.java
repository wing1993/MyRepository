package com.df.dao.pojo;

public class ClientPage {
	int page;  //页码

	
	public ClientPage() {
		super();
	}

	public ClientPage(int page) {
		super();
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "ClientPage [page=" + page + "]";
	}
	
	
	
}
