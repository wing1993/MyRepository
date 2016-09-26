package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;

public interface IUserDAO extends IBaseHibernateDAO<User, Integer> {

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	String login(User user) throws Exception;

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws Exception
	 */
	void registry(User user) throws Exception;

	/**
	 * 管理人员审核注册用户 state置为1
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	void examine(User user) throws Exception;

	/**
	 * 查询出已注册需要管理员审核的用户
	 * 
	 * @param k1
	 * @param k2
	 * @return
	 * @throws Exception
	 */
	public QueryResult findNeedExamine(Integer k1, Integer k2) throws Exception;

	/**
	 * 当用户修改用户类型时需要管理员审核 审核成功执行update操作
	 * 
	 * @param user
	 * @throws Exception
	 */
	void changeUserType(User user) throws Exception;

	/**
	 * 通过用户登录的用户名获取用户信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User findByUsername(User user) throws Exception;
	
	/**
	 * 通过邮箱找用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> findByMail(User user)throws Exception;
}