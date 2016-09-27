package com.df.action.reply;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.df.dao.pojo.MyquestionReply;
import com.df.dao.pojo.Question;
import com.df.service.iservice.IReplyService;
import com.opensymphony.xwork2.ModelDriven;

public class MyquestionReplyAction implements Serializable, ModelDriven<MyquestionReply>,RequestAware{

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("ReplyService")
	private IReplyService<Object> studentReplyService;
	private MyquestionReply myquestionReply ;
	private Map<String, Object> requestMap;
	private Question question = (Question) ServletActionContext.getRequest()
			.getSession().getAttribute("fromActions");
	
	public String save() {
		return studentReplyService.save(myquestionReply,question);
	}

	public String delete() {
		return studentReplyService.delete(myquestionReply,question);
	}
	
	
	
	
	
	@Override
	public MyquestionReply getModel() {
		myquestionReply = new MyquestionReply();
		return myquestionReply;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

}

