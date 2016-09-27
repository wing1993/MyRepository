package com.df.action.reply;

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
import com.df.dao.pojo.StudentReply;
import com.df.dao.pojo.User;
import com.df.service.iservice.IReplyService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("studentReplyAction")
@Scope("prototype")
public class StudentReplyAction implements Serializable, ModelDriven<StudentReply>,RequestAware{

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("ReplyService")
	private IReplyService<Object> studentReplyService;
	private StudentReply studentReply ;
	private Map<String, Object> requestMap;
	private Question question = (Question) ServletActionContext.getRequest()
			.getSession().getAttribute("fromActions");
	
	public String save() {
		return studentReplyService.save(studentReply,question);
	}

	public String delete() {
		return studentReplyService.delete(studentReply,question);
	}
	
	
	
	
	
	@Override
	public StudentReply getModel() {
		studentReply = new StudentReply();
		return studentReply;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

}
