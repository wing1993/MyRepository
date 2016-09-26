package com.df.action.user;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.QuestionType;
import com.df.dao.pojo.User;
import com.df.service.iservice.IDaShiService;
import com.df.service.iservice.IQuestionTypeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("dashiAction")
@Scope("prototype")
public class DaShiAction extends ActionSupport implements ModelDriven<User>,
		RequestAware,SessionAware {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("dashiService")
	private IDaShiService dashiService;
	
	@Autowired
	@Qualifier("questionTypeService")
	private IQuestionTypeService questionTypeService;

	// 使用值栈中的Map栈
	private Map<String, Object> requestMap;
	private Map<String,Object> sessionMap;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap=arg0;
		
	}
	// 1-取值（自动取值，自动类型转换，自动封装成对象供方法使用 --ModelDriven接口 ）
	private User user;
	private QuestionType qtype;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public QuestionType getQtype() {
		return qtype;
	}

	public void setQtype(QuestionType qtype) {
		this.qtype = qtype;
	}

	@Override
	public User getModel() {
		user = new User();
		return user;
	}

	public void setModel(User user) {
		this.user = user;
	}

	private List<User> dashis;

	public List<User> getDashis() {
		return dashis;
	}

	@SuppressWarnings("unchecked")
	private User u =  (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");

	// 初始化页面
	public String load() {
		List<String> locs = dashiService.findDaShiLoc();
		requestMap.put("loc", locs);
		System.out.println(QTypeList());
		sessionMap.put("qtList", QTypeList());
		return this.findAll();
	}

	public String findAll() {	
		if(u==null||u.getUserType()=="弟子"){
			dashis = dashiService.findAll();//当用户还没有登录时，直接查找所有大师信息
		}
		else{
			dashis = dashiService.findAllDaShi(u.getUsername());
		}
		System.out.println(dashis);
		requestMap.put("dashis", dashis);
		return "dashiList";
	}

	public String findByLoc() {
		// System.out.println(user.getCon2());要加入user的set和get方法
		if(u==null){
			dashis = dashiService.findDaShiByLoc(user.getCon2(), " ");
		}else{
			dashis = dashiService.findDaShiByLoc(user.getCon2(), u.getUsername());
		}
		return "dashiList";
	}
	
	public List<QuestionType> QTypeList(){
		return questionTypeService.findAll();
	}

	
}
