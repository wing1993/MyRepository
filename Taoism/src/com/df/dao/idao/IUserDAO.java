package com.df.dao.idao;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 查找相同用户名
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User findSameName(User user) throws Exception;
	
	/**
	 * 查询未审核的用户
	 * @return
	 * @throws Exception
	 */
	public List<User> findUnexamined()throws Exception;

	/**
	 * 查询未审核用户数量
	 * @param user 
	 */
	public int queryCountState0(User user)throws Exception;

	/**
	 * 查询未审核用户记录
	 * @param from
	 * @param length
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> queryListState0(int from, int length, User user)throws Exception;

	/**
	 * 查询申请身份升级的用户记录数
	 * @return
	 * @throws Exception
	 */
	int queryCountUpgrade()throws Exception;

	/**
	 * 查询申请身份升级的用户记录
	 * @param from
	 * @param length
	 * @return
	 * @throws Exception
	 */
	List<Object[]> queryListUpgrade(int from, int length)throws Exception;

	/**
	 * 根据用户名username获取用户信息记录数
	 * @param user
	 * @return
	 */
	User getByUsername(User user)throws Exception;

	/**
	 * 获取用户信息
	 * @param from
	 * @param length
	 * @param user
	 * @return
	 */
	List<Object[]> queryListUserinfo(int from, int length, User user)throws Exception;

	/**
	 * 获取用户信息记录数
	 * @param user
	 * @return
	 */
	int queryCountUserinfo(User user) throws Exception;

	
}