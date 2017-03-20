package com.df.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IBaseHibernateDAO;
import com.df.dao.pojo.Admin;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;


@Repository("adminDao")
public class AdminDAOImpl  implements IBaseHibernateDAO<Admin, Integer> {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void save(Admin admin) throws Exception {
		sessionFactory.getCurrentSession().save(admin);
		
	}

	@Override
	public void delete(Admin admin) throws Exception {
		sessionFactory.getCurrentSession().delete(admin);
		
	}

	@Override
	public void update(Admin admin) throws Exception {
		sessionFactory.getCurrentSession().update(admin);
		
	}

	@Override
	public Admin getById(Integer adminId) throws Exception {
		Admin admin = (Admin) sessionFactory.getCurrentSession().load(
				Admin.class, adminId);
		return admin;
	}

	@Override
	public List<Admin> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
