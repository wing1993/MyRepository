package com.df.action.admin.jqGrid;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.action.admin.JqGridBaseAction;
import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("listUserAction")
@Scope("prototype")
public class ListUserAction extends JqGridBaseAction<Object[]>  implements  ModelDriven<User> {
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	//private List<Object[]> listUser;  //查询出来的记录
	private User user;
	private int countUser;  //总记录条数
	private int from;  //分页功能    起始位置
	private int length;  //分页功能   一次性查询的记录数
	private String method;
	
	/**
	 * 获取需要审核的用户
	 * @return
	 */
	public String getUserGridModel() {
		try {
			System.out.println(user+"12");
			this.setCountUser(this.userService.queryCountState0(user));
			//this.setListUser(this.userService.queryListState0(from, length,user));
			System.out.println("前台传值   from："+from+"  length:"+length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("查询的数据"+this.getGridModel());
		return this.refreshGridModel();
	}
	/**
	 * 获取申请身份升级的用户
	 * @return
	 */
	public String getUpgradeUserList() {
		try {
			this.setCountUser(this.userService.queryCountUpgrade());
			//this.setListUser(this.userService.queryListUpgrade(from, length));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1"+this.getGridModel());
		return this.refreshGridModel();
	}
	
	
	/**
	 * 获取所有用户的所有资料
	 * @return
	 */
	public String getInfoUserList() {
		try {
			this.setCountUser(this.userService.queryCountUserinfo(user));
			//this.setListUser(this.userService.queryListUserinfo(from, length, user));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1"+this.getGridModel());
		return this.refreshGridModel();
	}
	
	
	
	@Override
	public int getResultSize() {
		int resultSize = 0;
		try {
			resultSize = this.getCountUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSize;
	}
	
	@Override
	public List<Object[]> listResults(int from, int length) {
		List<Object[]> results = Collections.emptyList();
		/*this.setFrom(from);
		this.setLength(length);*/
		try {
			if(null!=this.method){
				if("userInfo".equals(this.method)){
					results = this.userService.queryListUserinfo(from, length, user);
				}else if("rsgCheck".equals(this.method)){
					results = this.userService.queryListState0(from, length, user);
				}else if("updateClass".equals(this.method)){
					results = this.userService.queryListUpgrade(from, length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	/*public List<Object[]> getListUser() {
		return listUser;
	}

	public void setListUser(List<Object[]> listUser) {
		this.listUser = listUser;
	}*/

	public int getCountUser() {
		return countUser;
	}

	public void setCountUser(int countUser) {
		this.countUser = countUser;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public User getModel() {
		user = new User();
		return user;
	}

	
}
