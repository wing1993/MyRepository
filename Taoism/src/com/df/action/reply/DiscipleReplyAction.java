package com.df.action.reply;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.df.dao.pojo.DiscipleReply;
import com.df.dao.pojo.Question;
import com.df.service.iservice.IReplyService;
import com.opensymphony.xwork2.ModelDriven;

public class DiscipleReplyAction implements Serializable, ModelDriven<DiscipleReply>,RequestAware{

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("ReplyService")
	private IReplyService<Object> studentReplyService;
	private DiscipleReply discipleReply ;
	private Map<String, Object> requestMap;
	private Question question = (Question) ServletActionContext.getRequest()
			.getSession().getAttribute("fromActions");
	
	public String save() {
		return studentReplyService.save(discipleReply,question);
	}

	public String delete() {
		return studentReplyService.delete(discipleReply,question);
	}
	
	
	
	
	
	@Override
	public DiscipleReply getModel() {
		discipleReply = new DiscipleReply();
		return discipleReply;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

}
