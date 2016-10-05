package com.df.action.reply;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.DiscipleReply;
import com.df.dao.pojo.MyquestionReply;
import com.df.dao.pojo.PublicReply;
import com.df.dao.pojo.StudentReply;
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
	private String replyContent;
	private int replyId;
	private String replyTime;
	private String sharezone;
	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	/*private Question question = (Question) ServletActionContext.getRequest()
			.getSession().getAttribute("QuestionfromActions");*/
	HttpServletResponse response=ServletActionContext.getResponse();
	public String saveSubReply() throws IOException {
		String msg = "error";
		replyTime = DateUtil.getDateyMdHms();
		System.out.println(sharezone+"lasdkjfdlfj");
		if("公开区".equals(sharezone)){
			PublicReply pr = new PublicReply();
			pr.setReplyId(replyId);
			Object object = 
					new PublicReply(pr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		else if("学员区".equals(sharezone)){
			StudentReply sr = new StudentReply();
			sr.setReplyId(replyId);
			Object object = 
					new StudentReply(sr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		else if("弟子区".equals(sharezone)){
			DiscipleReply dr = new DiscipleReply();
			dr.setReplyId(replyId);
			Object object = 
					new DiscipleReply(dr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		else if("我的问题".equals(sharezone)){
			MyquestionReply mr = new MyquestionReply();
			mr.setReplyId(replyId);
			Object object = 
					new MyquestionReply(mr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		System.out.println(msg);
		return msg;
	}

	public String saveReply() throws IOException {
		String msg = "error";
		replyTime = DateUtil.getDateyMdHms();
		System.out.println(sharezone+"lasdkjfdlfj");
		if("公开区".equals(sharezone)){
			PublicReply pr = new PublicReply();
			pr.setReplyId(replyId);
			Object object = 
					new PublicReply(pr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		else if("学员区".equals(sharezone)){
			StudentReply sr = new StudentReply();
			sr.setReplyId(replyId);
			Object object = 
					new StudentReply(sr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		else if("弟子区".equals(sharezone)){
			DiscipleReply dr = new DiscipleReply();
			dr.setReplyId(replyId);
			Object object = 
					new DiscipleReply(dr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		else if("我的问题".equals(sharezone)){
			MyquestionReply mr = new MyquestionReply();
			mr.setReplyId(replyId);
			Object object = 
					new MyquestionReply(mr,u.getUsername(),replyTime,replyContent);
			msg = replyService.saveReply(object,sharezone);
		}
		System.out.println(msg);
		return msg;
	}
	
	public String delete() {
		//return replyService.delete(Object object,String s);
		return null;
	}
	
	
	

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	public List<Object> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Object> replyList) {
		this.replyList = replyList;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getSharezone() {
		return sharezone;
	}

	public void setSharezone(String sharezone) {
		this.sharezone = sharezone;
	}
}
