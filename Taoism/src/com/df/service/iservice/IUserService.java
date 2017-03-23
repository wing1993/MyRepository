package com.df.service.iservice;

import java.util.List;
import java.util.Map;

import com.df.dao.pojo.User;

public interface IUserService extends IBaseService<User, Integer> {

	/**
	 * 执行登录验证功能：审核成功的用户可以登录
	 * 
	 * @param user
	 *            要登录的对象信息封装在POJO类User中
	 * @return 成功 success 失败 error
	 */
	String login(User user);

	/**
	 * 注册功能，将注册信息写入到user表中 state 置为0 表示待审核
	 * 
	 * @param user
	 *            根据注册界面元素封装的POJO类对象
	 * @return 成功 success 失败 error
	 */
	String registry(User user);

	/**
	 * 审核功能 ，将用户的state置为1表示审核成功 可以登录
	 * 
	 * @param user
	 * @return
	 */
	String examine(User user);


	String changeUserType(User user) throws Exception;

	User findByUsername(User user) throws Exception;
	
	/**
	 * 通过邮箱找用户
	 * @param user
	 * @return
	 */
	List<User> findByMail(User user);
	
	/**
	 * 查找相同的用户名
	 * @param user
	 * @return
	 */
	boolean findSameName(User user);
	
	/**
	 * 查找所有未审核的用户
	 * @return
	 */
	List<User> findUnexamined();
	
	/**
	 * 查询记录数
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	int queryCountState0(User user) throws Exception;
	
	/**
	 * 查询要显示的记录 未审核用户
	 * @param from  开始记录
	 * @param length  需要查询的记录条数
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	List<Object[]> queryListState0(int from, int length, User user) throws Exception;

	/**
	 * 查询申请身份升级的用户记录数
	 * @return
	 * @throws Exception
	 */
	int queryCountUpgrade() throws Exception;

	/**
	 * 查询申请身份升级的用户记录
	 * @param from   开始查找记录数
	 * @param length  记录条数
	 * @return
	 * @throws Exception
	 */
	List<Object[]> queryListUpgrade(int from, int length) throws Exception;

	/**
	 * 用户身份审核通过
	 * @param user
	 * @throws Exception
	 */
	void updateExaminUser_1(User user)throws Exception;
	
	/**
	 * 用户身份审核不通过
	 * @param user
	 * @throws Exception
	 */
	void updateExaminUser_2(User user)throws Exception;

	/**
	 * 所有用户资料记录数
	 * @return
	 */
	int queryCountUserinfo(User user) throws Exception;

	/**
	 * 所有用户资料记录数
	 * @param from
	 * @param length
	 * @return
	 */
	List<Object[]> queryListUserinfo(int from, int length, User user) throws Exception;

	/**
	 * 查询出所有弟子 大师
	 * @return
	 * @throws Exception
	 */
	List<User> findDiscipleList()throws Exception;

}