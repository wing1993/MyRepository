package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.User;

public interface IDaShiDAO extends IBaseHibernateDAO<User, Integer> {
	/**
	 * 查找所有大师信息（大师不能向自己提问）
	 * 
	 * @param self
	 * @return
	 * @throws Exception
	 */
	List<User> findAllDaShi(String self) throws Exception;

	/**
	 * 查找有大师的所有地区
	 * 
	 * @return
	 */
	List<String> findDaShiLoc() throws Exception;

	/**
	 * 根据地区查找大师信息
	 * @param self
	 * @param loc
	 * @return
	 * @throws Exception
	 */
	List<User> findDaShiByLoc(String loc, String self) throws Exception;
	/**
	 * 未登录时或登陆者不为大师时的大师列表
	 * @param loc
	 * @return
	 * @throws Exception
	 */
	List<User> findDaShiByLoc(String loc)throws Exception;

}
