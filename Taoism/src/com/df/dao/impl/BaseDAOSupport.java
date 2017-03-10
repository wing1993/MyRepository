package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.df.dao.pojo.User;

public class BaseDAOSupport {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*@SuppressWarnings("unchecked")
	public List<Object[]> queryByPage(String sql, int from, int length)throws Exception {
		List<Object[]> objectList = new ArrayList<Object[]>();
		objectList = sessionFactory.getCurrentSession().createQuery(sql)
				.setFirstResult(from).setMaxResults(length).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		return objectList;	
	}*/
	
	public int queryResultsCount(String sql) throws Exception {
		// 查询总记录数
		Long resultCount =  (Long) sessionFactory.getCurrentSession()
				.createQuery(sql).uniqueResult();
		return resultCount.intValue();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> queryByPage_1(String sql, int from, int length)throws Exception {
		List<Object[]> objectList = new ArrayList<Object[]>();
		objectList = sessionFactory.getCurrentSession().createQuery(sql)
				.setFirstResult(from).setMaxResults(length).list();
		
		return objectList;	
	}
	
	
	
	

}
