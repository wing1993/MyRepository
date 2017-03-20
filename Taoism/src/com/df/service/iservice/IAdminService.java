package com.df.service.iservice;


import java.util.List;

import com.df.dao.pojo.Admin;

public interface IAdminService extends IBaseService<Admin, Integer> {

	List<Admin> findByAdminId(Integer userId)throws Exception;

}
