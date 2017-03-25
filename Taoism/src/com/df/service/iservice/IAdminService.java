package com.df.service.iservice;


import java.util.List;

import com.df.dao.pojo.Admin;

public interface IAdminService extends IBaseService<Admin, Integer> {

	/**
	 * 根据管理员id 获取该管理员添加的管理员
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Admin> findByAdminId(Integer userId)throws Exception;

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	Admin login(Admin admin)throws Exception;
}
