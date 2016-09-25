package com.df.action.reply;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Reply;
import com.df.service.iservice.IReplyService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction implements Serializable, ModelDriven<Reply>,RequestAware{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("questionService")
	private IReplyService replyService;
	
	private Reply reply;
	private Map<String, Object> requestMap;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	@Override
	public Reply getModel() {
		reply = new Reply();
		return reply;
	}
	
	

}
