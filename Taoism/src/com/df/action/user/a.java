package com.df.action.user;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.User;
import com.df.service.impl.UserServiceImpl;
import com.df.service.iservice.IUserService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("a")
@Scope("prototype")
public class a implements Serializable, ModelDriven<User>, RequestAware {
	/**
	 * }
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	private User user;
	private Map<String, Object> requestMap;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	@Override
	public User getModel() {
		user = new User();
		return user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}

	public String login() {
		System.out.println(userService + "------1312222223131");
		String msg = userService.login(user);

		return msg;
	}

}
