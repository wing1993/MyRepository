package com.df.action.questionType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Question;
import com.df.dao.pojo.QuestionType;
import com.df.dao.pojo.User;
import com.df.service.iservice.IQuestionTypeService;
import com.opensymphony.xwork2.ModelDriven;


@Controller("questionTypeAction")
@Scope("prototype")
public class QuestionTypeAction implements Serializable, ModelDriven<QuestionType>,RequestAware{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("questionTypeService")
	private IQuestionTypeService questionTypeService;
	
	private QuestionType questionType;
	private List<QuestionType> questionTypeList;
	private Map<String, Object> requestMap;
	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	
	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public List<QuestionType> getQuestionTypeList() {
		return questionTypeList;
	}

	public void setQuestionTypeList(List<QuestionType> questionTypeList) {
		this.questionTypeList = questionTypeList;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
		
	}

	@Override
	public QuestionType getModel() {
		questionType = new QuestionType();
		return questionType;
	}
	
	public String save() {
		String msg = "error";
		try {
			msg = questionTypeService.save(questionType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String delete() {
		String msg = "error";
		try {
			msg = questionTypeService.delete(questionType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String update() {
		String msg = "error";
		try {
			msg = questionTypeService.update(questionType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String findAll(){
		String msg = "error";
		try {
			questionTypeList =  questionTypeService.findAll();
			System.out.println(questionTypeList);;
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
}
