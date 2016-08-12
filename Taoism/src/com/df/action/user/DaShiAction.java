package com.df.action.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.User;
import com.df.service.iservice.IDaShiService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("dashiAction")
@Scope("prototype")
public class DaShiAction implements ModelDriven<User>, RequestAware {

	@Autowired
	@Qualifier("dashiService")
	private IDaShiService dashiService;
	
	//使用值栈中的Map栈
	private Map<String , Object> requestMap;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
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
	public void setModel(User user) {
		this.user = user;
	}
	
	public String load(){
		List<String> locs=dashiService.findDaShiLoc();
		requestMap.put("loc", locs);
		System.out.println(requestMap.toString());
		return "success";
	}
}
