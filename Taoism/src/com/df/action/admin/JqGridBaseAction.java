package com.df.action.admin;

import java.util.Collections;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
 
@SuppressWarnings("serial")
public abstract class JqGridBaseAction<T> extends ActionSupport {
	// 和jqGrid组件相关的参数属性
	private List<T> gridModel = Collections.emptyList();	//要显示的记录
	private Integer rows = 0;		//每页中显示的记录行数
	private Integer page = 0;		//当前页码
	private Integer total = 0;		//总页数
	private Integer record = 0;		//总记录数
	private String sord;			//排序的方式
	private String sidx;			//用于排序的列名
	private String search;			//是否是用于查询的请求
 
	public abstract int getResultSize();
 
	public abstract List<T> listResults(int from, int length);
 
	public String refreshGridModel() {
		try {
			List<T> results = Collections.emptyList();
			record = this.getResultSize();
			int from = rows * (page - 1);
			int length = rows;
			results = this.listResults(from, length);
			this.setGridModel(results);
			total = (int) Math.ceil((double) record / (double) rows);
			System.out.println("2"+gridModel);
			System.out.println("2"+record);
			System.out.println("2"+rows);
			System.out.println("2"+page);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
 
	public List<T> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<T> gridModel) {
		this.gridModel = gridModel;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
}

