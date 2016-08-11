package com.df.action.user;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;
import com.opensymphony.xwork2.ModelDriven;




@Controller("userAction")
@Scope("prototype")
public class UserAction implements Serializable, ModelDriven<User>, RequestAware {
	
	/*@Autowired
	@Qualifier("userService")
	private IUserService userService;*/
	@Resource(name="userService")
	private IUserService userService;
	private static final long serialVersionUID = 1L;
	
	private int sumPage;     //总页数
	private int currentPage; //当前页
	//使用值栈中的Map栈
	private Map<String , Object> requestMap;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
		
	}
	
	
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


	public IUserService getUserService() {
		return userService;
	}


	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	// 1-取值（自动取值，自动类型转换，自动封装成对象供方法使用 --ModelDriven接口 ）
	private User user;
	{
		System.out.println(user.getUsername()+"------"+user.getPassword());
	}
	@Override
	public User getModel() {
		user = new User();
		return user;
	}
	
	//2-处理
	public String save(){
		return userService.save(user);
	}
	
	public String delete(){
		return userService.delete(user);
	}
	
	public String update(){
		return userService.update(user);
	}
	
	
	
	public String login() {
		String msg = userService.login(user);
		requestMap.put("user", user);
		return msg;
	}

	public String registry() {
		System.out.println("------");
		String msg = userService.registry(user);
		//requestMap.put("user", user);
		return msg;
	}
}
