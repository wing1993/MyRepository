package com.df.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.User;
import com.df.dao.pojo.QueryResult;



@Repository("userDao")
public class UserDAOImpl  implements IUserDAO  {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	

	@Override
    public void save(User transientInstance) throws Exception{
		transientInstance.setState(0);
		sessionFactory.getCurrentSession().save(transientInstance);
		
    }
	@Override
	public void delete(User persistentInstance) {
		
		sessionFactory.getCurrentSession().save(persistentInstance);
    }
   
    
 
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() throws Exception{
		
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery(
				"FROM User u where u.state=1")
				.list();
			
		return userList;
	}

	@Override
	public void update(User user) {
		
		sessionFactory.getCurrentSession().update(user);
	
		
	}
	@Override
	public User getById(Integer id) {
			
		User user = (User)sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
	
		List<User> userList = new ArrayList<User>();
		Long count = (long) 0;
	
		
		//查询总记录数
		count = (Long) sessionFactory.getCurrentSession().createQuery(
				"SELECT COUNT(*) FROM User") 	
				.uniqueResult();  
		System.out.println(firstResult+"---"+maxResults+"---"+count);
		//查询一页的数据列表
		userList = sessionFactory.getCurrentSession().createQuery(
				"FROM User")
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.list();
		
		return new QueryResult(count.intValue(),userList);
	}

	@Override
	public String login(User user) throws Exception {
		User loginUser = null;
	
		loginUser = (User)sessionFactory.getCurrentSession().createQuery(
				"from User u where u.username=? and u.password=? and u.state=1")
				.setString(0, user.getUsername())
				.setString(1, user.getPassword())
				.uniqueResult();
			
		return loginUser != null ? "success" : "error";
	}

	@Override
	public void registry(User user) throws Exception {
		
		//在事务中运行
		user.setState(0);//待审核状态
		sessionFactory.getCurrentSession().save(user);
			
	}

	@Override
	public void examine(User user) throws Exception {
		//审核成功将state置1
		user.setState(1);
		sessionFactory.getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findNeedExamine(Integer firstResult, Integer maxResults) 
			throws Exception {
	
		List<User> userList = new ArrayList<User>();
		Long count = (long) 0; //总记录数
	
		//查询总记录数
		count = (Long) sessionFactory.getCurrentSession().createQuery(
				"SELECT COUNT(*) FROM User") 	
				.uniqueResult();  
		System.out.println(firstResult+"---"+maxResults+"---"+count);
		//查询一页的数据列表
		userList = sessionFactory.getCurrentSession().createQuery(
				"FROM User u WHERE u.state=0")
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.list();
		
		return new QueryResult(count.intValue(),userList);
	}

	@Override
	public void changeUserType(User user) throws Exception {
		
		//审核通过将con1标志 赋值给userType属性
		user.setUserType(user.getCon1());
		user.setCon1(null);
		sessionFactory.getCurrentSession().update(user);
	
		
	}  
	@Override
	public List<User> findByUsername(User user) throws Exception {
		user = (User)sessionFactory.getCurrentSession().createQuery(
				"from User u where u.username=? and u.password=? and u.state=1")
				.setString(0, user.getUsername())
				.setString(1, user.getPassword())
				.uniqueResult();
		List<User> users = new ArrayList<User>();
		users.add(user);
		return users;
	}
	
}