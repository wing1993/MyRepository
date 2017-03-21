package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.impl.AdminDAOImpl;
import com.df.dao.pojo.Admin;
import com.df.dao.pojo.QueryResult;
import com.df.service.iservice.IAdminService;
import com.df.service.iservice.IBaseService;


@Service("adminService")
public class AdminServiceImpl implements IAdminService{

	@Autowired
	@Qualifier("adminDao")
	private AdminDAOImpl adminDao;

	@Transactional
	@Override
	public String save(Admin admin) throws Exception {
		adminDao.save(admin);
		return null;
	}

	@Transactional
	@Override
	public String delete(Admin admin) throws Exception {
		adminDao.delete(admin);
		return null;
	}

	@Transactional
	@Override
	public String update(Admin admin) throws Exception {
		adminDao.update(admin);
		return null;
	}

	@Transactional
	@Override
	public List<Admin> findAll() throws Exception {
		adminDao.findAll();
		return null;
	}

	@Transactional
	@Override
	public Admin getById(Integer adminId) throws Exception {
		adminDao.getById(adminId);
		return null;
	}

	@Transactional
	@Override
	public QueryResult findAll(Integer k1, Integer k2) throws Exception {
		adminDao.findAll(k1, k2);
		return null;
	}

	@Transactional
	@Override
	public List<Admin> findByAdminId(Integer userId) throws Exception {
		List<Admin> adminList = adminDao.findByAdminId(userId);
		return adminList;
	}

}
