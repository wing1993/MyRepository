package com.df.action.sysadmin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
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
public class AdminAction implements Serializable, ModelDriven<Admin>,RequestAware{

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
	
	private Admin admin;
	private Map<String, Object> requestMap;
	private Integer userId;
	private String admin_qxs;//管理员拥有的权限
	private List<Admin> adminList;
	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	
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
			if(null!=u) {
				admin.setAddAdmin(u.getUserId());
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
			if(null!=u) {
				adminList = adminService.findByAdminId(u.getUserId());
			}
			requestMap.put("adminList", adminList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	@Override
	public Admin getModel() {
		admin = new Admin();
		return admin;
	}

}
