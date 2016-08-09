package com.df.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.User;
import com.df.dao.pojo.QueryResult;
import com.df.dao.util.HibernateSessionFactory;



@Repository("userDao")
public class UserDAOImpl  implements IUserDAO  {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	

	@Override
    public void save(User transientInstance) {
   
		sessionFactory.getCurrentSession().save(transientInstance);
	
    }
	@Override
	public void delete(User persistentInstance) {
		
		sessionFactory.getCurrentSession().save(persistentInstance);
    }
   
    
 
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		System.out.println("1111111111111111111");
		
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery(
				"FROM User u where u.state=1")
				.list();
			
		return userList;
	}

	@Override
	public void update(User user) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(user);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		
	}
	@Override
	public User getById(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			User user = (User)session.get(User.class, id);
			
			transaction.commit();
			return user;
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
		System.out.println(firstResult+"---"+maxResults);
		Session session = null;
		Transaction transaction = null;
		List<User> userList = new ArrayList<User>();
		Long count = (long) 0;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//查询总记录数
			count = (Long) session.createQuery(
					"SELECT COUNT(*) FROM User") 	
					.uniqueResult();  
			System.out.println(firstResult+"---"+maxResults+"---"+count);
			//查询一页的数据列表
			userList = session.createQuery(
					"FROM User")
					.setFirstResult(firstResult)
					.setMaxResults(maxResults)
					.list();
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return new QueryResult(count.intValue(),userList);
	}

	@Override
	public String login(User user) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		User loginUser = null;
		try{
			transaction = session.beginTransaction();
			
			loginUser = (User)session.createQuery(
					"from User u where u.username=? and u.password=? and u.state=1")
					.setString(0, user.getUsername())
					.setString(1, user.getPassword())
					.uniqueResult();
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return loginUser != null ? "success" : "error";
	}

	@Override
	public void registry(User user) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//在事务中运行
			user.setState(0);//待审核状态
			session.save(user);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}finally{
			if(session != null) session.close();
		}
	}

	@Override
	public void examine(User user) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			//审核成功将state置1
			user.setState(1);
			session.update(user);
		
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findNeedExamine(Integer firstResult, Integer maxResults) 
			throws Exception {
		System.out.println(firstResult+"---"+maxResults);
		Session session = null;
		Transaction transaction = null;
		List<User> userList = new ArrayList<User>();
		Long count = (long) 0; //总记录数
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//查询总记录数
			count = (Long) session.createQuery(
					"SELECT COUNT(*) FROM User") 	
					.uniqueResult();  
			System.out.println(firstResult+"---"+maxResults+"---"+count);
			//查询一页的数据列表
			userList = session.createQuery(
					"FROM User u WHERE u.state=0")
					.setFirstResult(firstResult)
					.setMaxResults(maxResults)
					.list();
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return new QueryResult(count.intValue(),userList);
	}

	@Override
	public void changeUserType(User user) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			//审核通过将con1标志 赋值给userType属性
			user.setUserType(user.getCon1());
			user.setCon1(null);
			session.update(user);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		
	}
	
}