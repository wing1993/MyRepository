package com.df.action.reply;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.df.dao.pojo.Question;
import com.df.dao.pojo.StudentReply;
import com.df.dao.pojo.User;
import com.df.dao.util.DateUtil;
import com.df.service.impl.QuestionServiceImpl;
import com.df.service.iservice.IQuestionService;
import com.df.service.iservice.IReplyService;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction implements Serializable,RequestAware{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("replyService")
	private IReplyService<Object> replyService;

	@Autowired
	@Qualifier("questionService")
	private IQuestionService questionService;
	
	private Map<String, Object> requestMap;
	private List<Object> replyList;
	private String replyContent;
	private int replyId;
	private String replyTime;
	private String sharezone;
	private String con1;
	private int QId;
	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	HttpServletResponse response=ServletActionContext.getResponse();
	
	/**
	 * 保存用户评论
	 * @return
	 * @throws IOException
	 */
	public String saveReply() throws IOException {
		String msg = "error";
		replyTime = DateUtil.getDateyMdHms();//获取当前时间
		Object object;
		Question q = new Question();
		if("公开区".equals(sharezone)){
			PublicReply pr = new PublicReply();
			if(replyId!=0){//保存二级回复（对问题回复的回复）
				pr.setReplyId(replyId);
				object = new PublicReply(pr,u.getUsername(),replyTime,replyContent);
			}
			else{//保存对问题的回复
				q.setQId(QId);
				object = new PublicReply(q,u.getUsername(),replyTime,replyContent);
				//将最后回复的用户，时间存放到对应的question对象中去
				try {
					questionService.updateLastReplyData(u.getUsername(),replyTime,QId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			msg = replyService.saveReply(object,sharezone);
		}
		else if("学员区".equals(sharezone)){
			StudentReply sr = new StudentReply();
			if(replyId!=0){
				sr.setReplyId(replyId);
				object = new StudentReply(sr,u.getUsername(),replyTime,replyContent);
			}
			else{
				q.setQId(QId);
				object = new StudentReply(q,u.getUsername(),replyTime,replyContent);
				//将最后回复的用户，时间存放到对应的question对象中去
				try {
					questionService.updateLastReplyData(u.getUsername(),replyTime,QId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			msg = replyService.saveReply(object,sharezone);
		}
		else if("弟子区".equals(sharezone)){
			DiscipleReply dr = new DiscipleReply();
			if(replyId!=0){
				dr.setReplyId(replyId);
				object = new DiscipleReply(dr,u.getUsername(),replyTime,replyContent);
			}
			else{
				q.setQId(QId);
				object = new DiscipleReply(q,u.getUsername(),replyTime,replyContent);
				//将最后回复的用户，时间存放到对应的question对象中去
				try {
					questionService.updateLastReplyData(u.getUsername(),replyTime,QId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			msg = replyService.saveReply(object,sharezone);
		}
		else if("我的问题".equals(sharezone)){
			MyquestionReply mr = new MyquestionReply();
			if(replyId!=0){
				mr.setReplyId(replyId);
				object = new MyquestionReply(mr,u.getUsername(),replyTime,replyContent);
			}
			else{
				q.setQId(QId);
				object = new MyquestionReply(q,u.getUsername(),replyTime,replyContent);
				//将最后回复的用户，时间存放到对应的question对象中去
				try {
					questionService.updateLastReplyData(u.getUsername(),replyTime,QId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			msg = replyService.saveReply(object,sharezone);
		}
		System.out.println(msg);
		return msg;
	}

	public String shieldReply() {
		String msg = "error";
		try {
			msg = replyService.shieldReply(replyId,sharezone,con1);
			PrintWriter out = response.getWriter();
			out.print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public int getQId() {
		return QId;
	}

	public void setQId(int qId) {
		QId = qId;
	}

	public String getCon1() {
		return con1;
	}

	public void setCon1(String con1) {
		this.con1 = con1;
	}
	
}
