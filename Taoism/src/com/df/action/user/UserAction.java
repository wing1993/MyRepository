package com.df.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Page;
import com.df.dao.pojo.User;
import com.df.dao.util.ClientPage;
import com.df.dao.util.PageUtil;
import com.df.service.iservice.IUserService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope("prototype")
public class UserAction implements Serializable, ModelDriven<User>,
		RequestAware,SessionAware {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	// 1-取值（自动取值，自动类型转换，自动封装成对象供方法使用 --ModelDriven接口 ）
	private User user;
	// 使用值栈中的Map栈
	private Map<String, Object> requestMap;
	private Map<String,Object> sessionMap;
	private int sumPage;     //总页数
	private int currentPage; //当前页
	private String valid_code;
	HttpServletResponse response = ServletActionContext.getResponse(); 
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
	/*
	 * private int sumPage; //总页数 private int currentPage; //当前页
	 */

	public String getValid_code() {
		return valid_code;
	}

	public void setValid_code(String valid_code) {
		this.valid_code = valid_code;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap=arg0;
		
	}
	@Override
	public User getModel() {
		user = new User();
		return user;
	}

	/*
	 * public int getSumPage() { return sumPage; } public void setSumPage(int
	 * sumPage) { this.sumPage = sumPage; } public int getCurrentPage() { return
	 * currentPage; } public void setCurrentPage(int currentPage) {
	 * this.currentPage = currentPage; }
	 */

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
		
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	// 2-处理
	public String save() {
		return userService.save(user);
	}

	public String delete() {
		return userService.delete(user);
	}

	public String update() {
		return userService.update(user);
	}

	public String findAll(){
		List<User> userList = userService.findAll();
		this.paging(userList);
		return "success";
	}
	private void paging(List<User> userList) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(sumPage != 0) {
			if(currentPage > sumPage)  //判断输入的页数是否大于总页数
				currentPage = sumPage;
			else if(currentPage < 0)
				currentPage = 1;
		}
		Page page = PageUtil.createPage(1, userList.size(), currentPage);
		int endPage = page.getBeginIndex() + page.getEveryPage();
		if (page.getBeginIndex() + page.getEveryPage() > page.getTotalCount()) {
			endPage = page.getTotalCount();
		}
		userList = userList.subList(page.getBeginIndex(), endPage);
		request.setAttribute("page", page);
		
		//前台页码显示
		int sum = page.getTotalPage();
		int current = page.getCurrentPage();
		int index;
		List<ClientPage> pageList = PageUtil.getClientPage(current,sum);
		
		if(pageList.size()!=0){
			if(sum<=10){
				index=1;
			}else if(sum>10 && current<=5){
				index=1;
			}else{
				index=current-4;
			}
					
			pageList.get(page.getCurrentPage()-index).setPage(0);
			
			request.setAttribute("pageList", pageList);
		}
		
		request.setAttribute("userByPages", userList);
	}
	public String login() throws IOException {
		String msg = userService.login(user);
		List<User> users = userService.findByUsername(user);
		sessionMap.put("UsersfromActions", users);
		return msg;
	}

	public String registry() throws Exception {
		System.out.println((User)requestMap.get("user"));
		String msg = userService.registry((User)requestMap.get("user"));
		sessionMap.put("User", (User)requestMap.get("user"));
		PrintWriter out = response.getWriter();
		out.print(msg);
		return null;
	}

	public String CheckCode() throws Exception{
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
		String msg="error";
		System.out.println((String)sessionMap.get("vCode")+"----"+valid_code);
		System.out.println(valid_code.equalsIgnoreCase(((String)sessionMap.get("vCode"))));
		if(valid_code.equalsIgnoreCase((String)sessionMap.get("vCode"))){
			msg="success";
			out.print(msg);
		}
		return null;
	}
}