package com.df.dao.pojo;

public class QueryCriteria {
	private String sharezone;  //查询内容的区域
	private String QTypeName;  //查询内容的问题类型
	private String QTime;      //查询内容的时间
	private int state;      //是否回复的状态
	private String QTitle;	//根据标题查找
	private String username;
	
	public QueryCriteria() {
		super();
	}
	
	

	public QueryCriteria(String sharezone, String qTypeName, String qTime,
			int state, String qTitle, String username) {
		super();
		this.sharezone = sharezone;
		QTypeName = qTypeName;
		QTime = qTime;
		this.state = state;
		QTitle = qTitle;
		this.username = username;
	}



	public String getQTitle() {
		return QTitle;
	}



	public void setQTitle(String qTitle) {
		QTitle = qTitle;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSharezone() {
		return sharezone;
	}
	public void setSharezone(String sharezone) {
		this.sharezone = sharezone;
	}
	public String getQTypeName() {
		return QTypeName;
	}
	public void setQTypeName(String qTypeName) {
		QTypeName = qTypeName;
	}
	public String getQTime() {
		return QTime;
	}
	public void setQTime(String qTime) {
		QTime = qTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "QueryCriteria [sharezone=" + sharezone + ", QTypeName="
				+ QTypeName + ", QTime=" + QTime + ", state=" + state + "]";
	}
	
}
