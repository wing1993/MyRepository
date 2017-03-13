package com.df.action.admin.jqGrid;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.action.admin.JqGridBaseAction;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
import com.df.service.iservice.IQuestionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("listQuestionAction")
@Scope("prototype")
public class ListQuestionAction extends JqGridBaseAction<Object[]>   implements  ModelDriven<Question>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("questionService")
	private IQuestionService questionService;
	private List<Object[]> listQuestion;
	private int countQuestion;
	private int from;
	private int length;
	private String username;
	private String startTime;
	private String endTime;
	private Question question;
	
	/**
	 * 根据查询条件获取帖子信息
	 * @return
	 */
	public String getInfoUserList() {System.out.println("1");
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("from", from);
			map.put("length", length);
			map.put("username", username);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			this.setCountQuestion(this.questionService.queryCountQuestioninfo(map));
			this.setListQuestion(this.questionService.queryListQuestioninfo(map));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1"+this.getGridModel());
		return this.refreshGridModel();
	}
	
	
	
	@Override
	public int getResultSize() {
		int resultSize = 0;
		try {
			resultSize = this.getCountQuestion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSize;
	}

	@Override
	public List<Object[]> listResults(int from, int length) {

		List<Object[]> results = Collections.emptyList();
		this.setFrom(from);
		this.setLength(length);
		try {
			results = this.getListQuestion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public List<Object[]> getListQuestion() {
		return listQuestion;
	}
	
	public void setListQuestion(List<Object[]> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public int getCountQuestion() {
		return countQuestion;
	}
	
	public void setCountQuestion(int countQuestion) {
		this.countQuestion = countQuestion;
	}
	
	public int getFrom() {
		return from;
	}
	
	public void setFrom(int from) {
		this.from = from;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
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



	@Override
	public Question getModel() {
		question = new Question();
		return question;
	}
	
}
