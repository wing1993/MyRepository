package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.User;

public interface IDaShiDAO extends IBaseHibernateDAO<User, Integer> {
	/**
	 * 查找有大师的所有地区
	 * @return
	 */
	List<String> findDaShiLoc() throws Exception;

	/**
	 * 根据地区查找大师信息
	 * @param loc
	 * @return
	 * @throws Exception
	 */
	List<User> findDaShiByLoc(String loc) throws Exception;
	
}
