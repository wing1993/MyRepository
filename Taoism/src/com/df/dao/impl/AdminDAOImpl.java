package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IAdminDAO;
import com.df.dao.idao.IBaseHibernateDAO;
import com.df.dao.pojo.Admin;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;


@Repository("adminDao")
public class AdminDAOImpl  extends BaseDAOSupport implements IAdminDAO {



	@Override
	public void save(Admin admin) throws Exception {
		this.getSessionFactory().getCurrentSession().save(admin);
		
	}

	@Override
	public void delete(Admin admin) throws Exception {
		this.getSessionFactory().getCurrentSession().delete(admin);
		
	}

	@Override
	public void update(Admin admin) throws Exception {
		this.getSessionFactory().getCurrentSession().update(admin);
		
	}

	@Override
	public Admin getById(Integer adminId) throws Exception {
		Admin admin = (Admin) this.getSessionFactory().getCurrentSession().load(
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

	public List<Admin> findByAdminId(Integer userId) throws Exception{
		@SuppressWarnings("unchecked")
		List<Admin> adminList = (List<Admin>) this.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Admin a where a.parentId=?")
				.setInteger(0, userId).list();
		return adminList;
	}

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin) {
		admin = (Admin) this.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Admin a where a.adminName=? and a.password=?")
				.setString(0, admin.getAdminName())
				.setString(1, admin.getPassword())
				.uniqueResult();

		return admin;
	}

}
