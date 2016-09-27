package com.df.action.reply;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.df.dao.pojo.PublicReply;
import com.df.dao.pojo.Question;
import com.df.service.iservice.IReplyService;
import com.opensymphony.xwork2.ModelDriven;

public class PublicReplyAction  implements Serializable, ModelDriven<PublicReply>,RequestAware{

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("ReplyService")
	private IReplyService<Object> studentReplyService;
	private PublicReply publicReply ;
	private Map<String, Object> requestMap;
	private Question question = (Question) ServletActionContext.getRequest()
			.getSession().getAttribute("fromActions");
	
	public String save() {
		return studentReplyService.save(publicReply,question);
	}

	public String delete() {
		return studentReplyService.delete(publicReply,question);
	}
	
	
	
	
	
	@Override
	public PublicReply getModel() {
		publicReply = new PublicReply();
		return publicReply;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}
}
