package com.df.action.message;

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

import com.df.dao.pojo.ClientPage;
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.Page;
import com.df.dao.pojo.User;
import com.df.service.iservice.IMessageService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("messageAction")
@Scope("prototype")
public class MessageAction implements ModelDriven<Message>, Serializable,RequestAware {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("messageService")
	private IMessageService messageService;
	
	private Message message;
	private List<Message> messages;
	private Map<String,Object> requestMap;
	private String msg="error";
	private int sumPage;     //总页数
	private int currentPage; //当前页
	private List<Message> mList;
	private List<ClientPage> cList;
	private Page page;
	public int getSumPage() {
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Message> getmList() {
		return mList;
	}

	public void setmList(List<Message> mList) {
		this.mList = mList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public Message getModel() {
		message=new Message();
		return message;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	/**
	 * 查找最近的6条信息
	 * @return
	 */
	public String findLatest() {
		messages=messageService.findLatest();
		return "messageList";
	}

	/**
	 * 根据页码查询分页数据
	 * 并按照时间顺序查找
	 * @return
	 */
	public String findAll(){
		DataPage<Message> dp = messageService.findAlldata(currentPage);
		if (dp.gettList() != null && dp.gettList().size() > 0) {
			requestMap.put("mList",dp.gettList());
			requestMap.put("cList",dp.getcList());
			requestMap.put("page",dp.getPage());
		}
		return "AllList";
	}
	
	@SuppressWarnings("unchecked")
	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");

	//通过作者查找信息
	public String findByAuthor(){
		messages=messageService.findByAuthor(u);
		requestMap.put("messages", messages);
		return "findByAuthor";
	}
	
	HttpServletResponse response=ServletActionContext.getResponse();
	//更新消息
	public String update() throws Exception{
		System.out.println(message+"00000");
		msg=messageService.update(message);
		System.out.println(message+msg);
		response.getWriter().print(msg);
		return null;
	}
	
	//删除消息
	public String delete() throws IOException{
		try {
			msg=messageService.delete(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().print(msg);
		return null;
	}
	
	//插入消息
	public String sava()throws Exception{
		msg=messageService.save(message);
		response.getWriter().print(msg);
		return null;
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap=arg0;
		
	}

}
