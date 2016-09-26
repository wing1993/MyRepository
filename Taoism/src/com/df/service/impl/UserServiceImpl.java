package com.df.service.impl;

import java.util.ArrayList;
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
	private String msg="error";

	@Transactional
	@Override
	public String save(User t) {
		msg = "error";
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

	@Transactional
	@Override
	public String update(User newObj) {
		try {
			userDao.update(newObj);
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional(readOnly = true)
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
		User user=new User();
		try {
			user=userDao.getById(k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Transactional
	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		QueryResult query = null;
		try {
			query = userDao.findAll(k1, k2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Transactional
	@Override
	public String login(User user) {
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
			query = userDao.findNeedExamine(k1, k2);
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
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public User findByUsername(User user) {
		User u=new User();
		try {
			u = userDao.findByUsername(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Transactional
	@Override
	public List<User> findByMail(User user) {
		List<User> users = new ArrayList<User>();
		try {
			users = userDao.findByMail(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}