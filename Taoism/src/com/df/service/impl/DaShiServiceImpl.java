package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IDaShiDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;
import com.df.service.iservice.IDaShiService;

@Service("dashiService")
public class DaShiServiceImpl implements IDaShiService {
	@Autowired
	@Qualifier("dashiDao")
	private IDaShiDAO dashiDao;

	@Transactional
	@Override
	public String save(User t) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<User> findAll() {
		List<User> dashis = null;
		try {
			dashis = dashiDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dashis;
	}

	@Override
	public User getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<String> findDaShiLoc() {
		List<String> locs = null;
		try {
			locs = dashiDao.findDaShiLoc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locs;
	}

	@Transactional
	@Override
	public List<User> findDaShiByLoc(String loc, String self) {
		List<User> ds = null;
		try {
			ds = dashiDao.findDaShiByLoc(loc, self);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * 查找所有地区的大师
	 */
	@Transactional
	@Override
	public List<User> findAllDaShi(String self) {
		List<User> dashis = null;
		try {
			dashis = dashiDao.findAllDaShi(self);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dashis;
	}

}
