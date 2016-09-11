package com.df.action.message;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.Page;
import com.df.dao.pojo.Question;
import com.df.dao.util.ClientPage;
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
	private int sumPage;     //总页数
	private int currentPage; //当前页
	private DataPage<Message> dp;
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
	 * 按照时间顺序查找所有的信息
	 * @return
	 */
	public String findAll(){
		messages=messageService.findAll();
		requestMap.put("messages", messages);
		return "success";
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap=arg0;
		
	}

}
