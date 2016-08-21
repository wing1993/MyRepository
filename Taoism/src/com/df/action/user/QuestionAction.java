package com.df.action.user;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Question;
import com.df.service.iservice.IQuestionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("questionAction")
public class QuestionAction implements ModelDriven<Question>, RequestAware {
	@Autowired
	@Qualifier("questionService")
	private IQuestionService questionService;

	private Question question;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public Question getModel() {
		question = new Question();
		return question;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public String AskDashi() {

		question.setQTime("2016/12/05 18:00:00");
		question.setQTypeName("问事");
		question.setQContent("是打飞阿斯蒂芬机快");
		question.setState(1);
		question.setSharezone("公开区域");
		question.setShareState(1);
		String msg = questionService.save(question);
		return msg;
	}

}
