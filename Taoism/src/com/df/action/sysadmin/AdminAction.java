package com.df.action.sysadmin;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Admin;
import com.df.dao.pojo.User;
import com.df.service.iservice.IAdminService;
import com.df.service.iservice.IBaseService;
import com.df.service.iservice.IUserService;
import com.opensymphony.xwork2.ModelDriven;


@Controller("adminAction")
@Scope("prototype")
public class AdminAction implements Serializable, ModelDriven<Admin>,RequestAware,SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	HttpServletResponse response = ServletActionContext.getResponse(); 
	private Admin admin;
	private Map<String, Object> requestMap;
	private Map<String,Object> sessionMap;
	private Integer userId;
	private String admin_qxs;//管理员拥有的权限
	private List<Admin> adminList;
	private Admin a = (Admin) ServletActionContext.getRequest()
			.getSession().getAttribute("admin");
	
	/**
	 * 新增管理员
	 * @return
	 */
	public String save() {
		//Admin admin = new Admin("admin", "123456", 1, 1, 1, 1, 1, 1, 1, "123", "127.0.0.1", 1, 1);
		String msg = "error";
		try {
			User user = userService.getById(userId);
			admin.setAdminName(user.getUsername());
			admin.setPassword(user.getPassword());
			if(null!=a) {
				admin.setAddAdmin(a.getAdminId());
			}
			//获取权限
			String[] adminqxs = admin_qxs.split(",");
			admin.setRsgCheck(Integer.parseInt(adminqxs[0]));
			admin.setUpdateClass(Integer.parseInt(adminqxs[1]));
			admin.setShieldUser(Integer.parseInt(adminqxs[2]));
			admin.setQtype(Integer.parseInt(adminqxs[3]));
			admin.setPostsManage(Integer.parseInt(adminqxs[4]));
			admin.setAddAdmin(Integer.parseInt(adminqxs[5]));
			admin.setCon1(0);
			admin.setCon2(0);
			
			adminService.save(admin);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 修改管理员权限（只能是权限）
	 * @return
	 */
	public String edit() {
		//Admin admin = new Admin("admin", "123456", 1, 1, 1, 1, 1, 1, 1, "123", "127.0.0.1", 1, 1);
		String msg = "error";
		try {
			//获取权限
			String[] adminqxs = admin_qxs.split(",");
			admin.setRsgCheck(Integer.parseInt(adminqxs[0]));
			admin.setUpdateClass(Integer.parseInt(adminqxs[1]));
			admin.setShieldUser(Integer.parseInt(adminqxs[2]));
			admin.setQtype(Integer.parseInt(adminqxs[3]));
			admin.setPostsManage(Integer.parseInt(adminqxs[4]));
			admin.setAddAdmin(Integer.parseInt(adminqxs[5]));
			admin.setCon1(0);
			admin.setCon2(0);
			
			adminService.update(admin);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 删除管理员权限   直接从表记录中删除
	 * @return
	 */
	public String delete() {
		String msg = "error";
		try {
			adminService.delete(admin);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 根据管理员id获取该管理员添加的管理员
	 * @return
	 */
	public String findByAdminId(){
		try {
			if(null!=a) {
				adminList = adminService.findByAdminId(a.getAdminId());
			}
			requestMap.put("adminList", adminList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 管理员登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		//user.setPassword(MD5Util.md5(user.getPassword()+user.getUsername()));
		System.out.println("登录加密密码："+admin.getPassword());
		System.out.println(admin);
		admin = adminService.login(admin);
		System.out.println("login"+admin);
		if(null!=admin){
			sessionMap.put("admin", admin);
			return "success";
		}else{
			return "error";
			
		}
	}
	public String load() throws Exception {
		System.out.println("load");
		return "success";
	}
	
	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public String getAdmin_qxs() {
		return admin_qxs;
	}

	public void setAdmin_qxs(String admin_qxs) {
		this.admin_qxs = admin_qxs;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;	
	}
	/*public Map<String, Object> getRequestMap() {
		return requestMap;
	}*/

	@Override
	public Admin getModel() {
		admin = new Admin();
		return admin;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap=arg0;
		
	}

	/*public Map<String, Object> getSessionMap() {
		return sessionMap;
	}*/

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}

	public Admin getAdmin() {
		return admin;
	}

}
