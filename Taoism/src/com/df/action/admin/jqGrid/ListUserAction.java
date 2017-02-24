package com.df.action.admin.jqGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.action.admin.JqGridBaseAction;
import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;

@Controller("listUserAction")
@Scope("prototype")
public class ListUserAction extends JqGridBaseAction<Object[]> {
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	private List<Object[]> listUser;
	private int countUser;
	private int from;
	private int length;
	
	
	public String getUserGridModel() {
		try {
			this.setCountUser(this.userService.queryCountState0());
			this.setListUser(this.userService.queryListState0(from, length));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1"+this.getGridModel());
		return this.refreshGridModel();
	}
	
	public String getUpgradeUserList() {
		try {
			this.setCountUser(this.userService.queryCountUpgrade());
			this.setListUser(this.userService.queryListUpgrade(from, length));
			
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
		this.setFrom(from);
		this.setLength(length);
		try {
			results = this.getListUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}

	public List<Object[]> getListUser() {
		return listUser;
	}

	public void setListUser(List<Object[]> listUser) {
		this.listUser = listUser;
	}

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

	
}
