package com.df.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private User u;

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
	public int queryCountState0(User user) throws Exception{
		return userDao.queryCountState0(user);
	}
	
	@Transactional
	@Override
	public List<Object[]> queryListState0(int from, int length, User user) throws Exception{
		return userDao.queryListState0(from, length, user);
	}
	
	
	
	@Transactional
	@Override
	public int queryCountUpgrade() throws Exception{
		return userDao.queryCountUpgrade();
	}
	
	@Transactional
	@Override
	public List<Object[]> queryListUpgrade(int from, int length) throws Exception{
		return userDao.queryListUpgrade(from, length);
	}
	
	@Transactional
	@Override
	public int queryCountUserinfo(User user) throws Exception {
		return userDao.queryCountUserinfo(user);
	}
	
	@Transactional
	@Override
	public List<Object[]> queryListUserinfo(int from, int length,User user)
			throws Exception {
		return userDao.queryListUserinfo(from, length, user);
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
	public String changeUserType(User user) throws Exception{
		userDao.changeUserType(user);
		msg="success";
		return msg;
	}

	@Transactional
	@Override
	public User findByUsername(User user) {
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

	@Transactional
	@Override
	public boolean findSameName(User user) {
		try {
			u=userDao.findSameName(user);
			System.out.println("ture/false"+u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null==u)
			return true;
		else
			return false;
	}

	@Transactional
	@Override
	public List<User> findUnexamined() {
		List<User> users=new ArrayList<User>();
		try {
			users=userDao.findUnexamined();
			System.out.println("未审核的用户"+users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}


	@Transactional
	@Override
	public void updateExaminUser_1(User user) throws Exception{
		user = userDao.getById(user.getUserId());
		user.setState(1);//审核通过置1
		userDao.update(user);
	}
	
	@Transactional
	@Override
	public void updateExaminUser_2(User user) throws Exception{
		user = userDao.getById(user.getUserId());
		user.setState(2);//审核不通过置2
		userDao.update(user);
	}

	
}