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

@Controller("listUserAction")
@Scope("prototype")
public class ListUserAction extends JqGridBaseAction<User> {
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	public String getUserGridModel() {
		System.out.println("1"+this.getGridModel());
		return this.refreshGridModel();
	}
	
	@Override
	public int getResultSize() {
		int resultSize = 0;
		try {
			resultSize = this.userService.queryResultsCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSize;
	}
	
	@Override
	public List<User> listResults(int from, int length) {
		List<User> results = Collections.emptyList();
		
		try {
			results = this.userService.queryByPage(from, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	
}
