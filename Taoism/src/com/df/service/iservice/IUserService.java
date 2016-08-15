package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;

public interface IUserService extends IBaseService<User, Integer> {

	/**
	 * 执行登录验证功能：审核成功的用户可以登录
	 * @param user 要登录的对象信息封装在POJO类User中
	 * @return 成功 success 失败 error
	 */
	String login(User user);
	
	/**
	 * 注册功能，将注册信息写入到user表中   state 置为0 表示待审核
	 * @param user 根据注册界面元素封装的POJO类对象 
	 * @return 成功 success 失败 error
	 */
	String registry(User user);
	
	/**
	 * 审核功能 ，将用户的state置为1表示审核成功 可以登录
	 * @param user
	 * @return
	 */
	String examine(User user) ;
	
	/**
	 * 需要审核的用户
	 * @param k1
	 * @param k2
	 * @return
	 */
	QueryResult findNeedExamine(Integer k1,Integer k2);
	
	String changeUserType (User user);
	
	List<User> findByUsername (User user);  

}