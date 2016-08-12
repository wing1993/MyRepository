package com.df.dao.impl;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IDaShiDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;

@Repository("dashiDao")
public class DaShiDAOImpl implements IDaShiDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	


	@Override
	public void save(User t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User newObj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public User getById(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查找所有大师信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() throws Exception {
		List<User> dashiList=null;
		String hql="from User where user_type=? and state=1";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "弟子");
		dashiList=query.list();
		return dashiList;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDaShiLoc() throws Exception {
		List<String> locList=null;
		String hql="select distinct city from User where user_type=? and state=1";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "弟子");
		locList=query.list();
		return locList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findDaShiByLoc(String loc) throws Exception {
		List<User> dashiList=null;
		String hql="from User where user_type=? and state=1 and city=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "弟子");
		query.setString(1,loc);
		dashiList=query.list();
		return dashiList;
	}

}
