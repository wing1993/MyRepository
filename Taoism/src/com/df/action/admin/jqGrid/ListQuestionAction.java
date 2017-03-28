package com.df.action.admin.jqGrid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.action.admin.JqGridBaseAction;
import com.df.dao.pojo.ClientPage;
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Page;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
import com.df.dao.util.DateUtil;
import com.df.service.iservice.IQuestionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("listQuestionAction")
@Scope("prototype")
public class ListQuestionAction implements Serializable, ModelDriven<Question>,RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("questionService")
	private IQuestionService questionService;
	private Map<String,Object> requestMap;
	private Question question;
	private List<Question> qList;   //保存获取的问题
	private List<ClientPage> cList; //保存要显示的页码
	private Page page;              //分页的情况（每页显示的数量，总页数，当前页，是否有上一页下一页等）
	private int rows;
	private String startTime;
	private String endTime;
	private int currentPage; //当前页
	private DataPage<Question> dp;
	/**
	 * 根据查询条件获取帖子信息
	 * @return
	 */
	public String findByQTime() {
		String msg = "error";
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("username", question.getUsername());
			map.put("startTime", this.getStartTime());
			if(null!=this.getEndTime()&&!"".equals(this.getEndTime())){
		    	map.put("endTime", DateUtil.getAfterDayDate(this.getEndTime(), 1));//结束时间+1天
			}
			map.put("currentPage", this.getCurrentPage());
			map.put("rows", this.getRows());
			System.out.println(map);
		
			this.setDp(questionService.findByQTime(map));
			if (dp.gettList() != null && dp.gettList().size() > 0) {
				this.setcList(dp.getcList());
				this.setqList(dp.gettList());
				this.setPage(dp.getPage());
				requestMap.put("qList",dp.gettList());
				requestMap.put("cList",dp.getcList());
				requestMap.put("startTime", this.getStartTime());
				requestMap.put("endTime", this.getEndTime());
				requestMap.put("author", question.getUsername());
				System.out.println("----"+this.getqList());
				System.out.println("----"+dp.gettList());
				msg = "success";
			}
			
			System.out.println(msg);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 根据用户id获取发布的帖子     userId  用户id     rows  一页显示的记录数     currentPage   当前页
	 * @return
	 */
	public String findMyPosts(){
		try {
			//获取登录者的信息
			User user = (User)ServletActionContext.getRequest()
					.getSession().getAttribute("UsersfromActions");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("currentPage", this.getCurrentPage());
			map.put("rows", this.getRows());
			this.setDp(questionService.findMyPosts(map));
			if (dp.gettList() != null && dp.gettList().size() > 0) {
				this.setcList(dp.getcList());
				this.setqList(dp.gettList());
				this.setPage(dp.getPage());
				requestMap.put("qList",dp.gettList());
				requestMap.put("cList",dp.getcList());
				System.out.println("----"+this.getqList());
				System.out.println("----"+dp.gettList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "MyPosts";
	}
	
	
	public List<Question> getqList() {
		return qList;
	}
	public void setqList(List<Question> qList) {
		this.qList = qList;
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
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public DataPage<Question> getDp() {
		return dp;
	}
	public void setDp(DataPage<Question> dp) {
		this.dp = dp;
	}
	@Override
	public Question getModel() {
		question = new Question();
		return question;
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap=arg0;
		
	}




}
