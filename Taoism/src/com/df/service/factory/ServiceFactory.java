package com.df.service.factory;

import com.df.service.impl.ServiceUserImpl;
import com.df.service.iservice.IUserService;

public class ServiceFactory {

	/**
	 * 返回User的service层实现对象
	 * 
	 * @return
	 */
	public static IUserService getUserServiceInstance() {
		return new ServiceUserImpl();
	}

}
