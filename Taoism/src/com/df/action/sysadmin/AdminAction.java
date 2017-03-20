package com.df.action.sysadmin;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Admin;
import com.df.service.impl.AdminServiceImpl;
import com.df.service.iservice.IBaseService;
import com.opensymphony.xwork2.ModelDriven;


@Controller("adminAction")
@Scope("prototype")
public class AdminAction implements Serializable, ModelDriven<Admin>,RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("adminService")
	private IBaseService<Admin,Integer> adminService;
	
	private Admin admin;
	private Map<String, Object> requestMap;

	public String save() {
		Admin admin = new Admin("admin", "123456", 1, 1, 1, 1, 1, 1, 1, "123", "127.0.0.1", 1, 1);
		try {
			adminService.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("success");
		return null;
	}
	
	
	
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;	
	}

	@Override
	public Admin getModel() {
		admin = new Admin();
		return admin;
	}

}
