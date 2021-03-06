package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.User;

public interface IDaShiService extends IBaseService<User, Integer> {
	List<User> findAllDaShi(String self);

	List<String> findDaShiLoc();

	List<User> findDaShiByLoc(String loc, String self);
}
