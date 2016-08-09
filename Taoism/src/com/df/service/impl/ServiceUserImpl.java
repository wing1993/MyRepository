package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IUserDAO;
import com.df.dao.factory.DaoFactory;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;



@Service("userService")
public class ServiceUserImpl implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDAO iud;
	
	@Transactional
	@Override
	public String save(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(User newObj) {
		try {
			iud.update(newObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional(readOnly=true)
	@Override
	public List<User> findAll() {
		List<User> users = null;
		try {
			users = iud.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		QueryResult query = null;
		try {
			query = iud.findAll(k1,k2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Override
	public String login(User user) {
		String msg = "error";
		try {
			msg = iud.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String registry(User user) {
		String msg = "error";
		try {
			iud.registry(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String examine(User user) {
		
		String msg = "error";
		try {
			iud.examine(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public QueryResult findNeedExamine(Integer k1, Integer k2) {
		QueryResult query = null;
		try {
			query = iud.findNeedExamine(k1,k2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Override
	public String changeUserType(User user) {
		try {
			iud.changeUserType(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
