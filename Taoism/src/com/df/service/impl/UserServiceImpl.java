package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;



@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDAO userDao;
	
	@Transactional
	@Override
	public String save(User t) {
		String msg = "error";
		try {
			userDao.save(t);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public String delete(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(User newObj) {
		try {
			userDao.update(newObj);
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
			users = userDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Transactional
	@Override
	public User getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		QueryResult query = null;
		try {
			query = userDao.findAll(k1,k2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Transactional
	@Override
	public String login(User user) {
		String msg = "error";
		try {
			msg = userDao.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public String registry(User user) {
		String msg = "error";
		try {
			userDao.registry(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public String examine(User user) {
		
		String msg = "error";
		try {
			userDao.examine(user);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public QueryResult findNeedExamine(Integer k1, Integer k2) {
		QueryResult query = null;
		try {
			query = userDao.findNeedExamine(k1,k2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Transactional
	@Override
	public String changeUserType(User user) {
		try {
			userDao.changeUserType(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
