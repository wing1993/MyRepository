package com.df.action.reply;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.PublicReply;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
import com.df.dao.util.DateUtil;
import com.df.service.iservice.IReplyService;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction implements Serializable,RequestAware{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("replyService")
	private IReplyService<Object> replyService;

	private Map<String, Object> requestMap;
	private List<Object> replyList;
	//private int QId;
	private String replyTime;
	//private String sharezone;
	private String replyContent;
	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	private Question question = (Question) ServletActionContext.getRequest()
			.getSession().getAttribute("QuestionfromActions");
	
	public String save() {
		if("公开区".equals(question.getSharezone())){
			PublicReply studentReply = new PublicReply(question,u.getUsername(),u.getUsername(),replyContent);
			return replyService.save(studentReply,question);}
		if("公开区".equals(question.getSharezone())){
			PublicReply studentReply = new PublicReply(question,u.getUsername(),u.getUsername(),replyContent);
			return replyService.save(studentReply,question);}
		if("公开区".equals(question.getSharezone())){
			PublicReply studentReply = new PublicReply(question,u.getUsername(),u.getUsername(),replyContent);
			return replyService.save(studentReply,question);}
		if("公开区".equals(question.getSharezone())){
			PublicReply studentReply = new PublicReply(question,u.getUsername(),u.getUsername(),replyContent);
			return replyService.save(studentReply,question);}
	}

	public String delete() {
		return replyService.delete(studentReply,question);
	}
	
	
	
	

	public List<Object> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Object> replyList) {
		this.replyList = replyList;
	}

	/*public int getQId() {
		return QId;
	}

	public void setQId(int qId) {
		QId = qId;
	}

	public String getSharezone() {
		return sharezone;
	}

	public void setSharezone(String sharezone) {
		this.sharezone = sharezone;
	}*/

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date date) {
		new DateUtil();
		this.replyTime = DateUtil.getDateyMdHms(date);
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	
	

}
