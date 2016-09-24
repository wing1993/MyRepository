package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.QueryCriteria;

public interface IPostService<T> {
	public List<Object> findByDynamicData(QueryCriteria t,String s) throws Exception;
}
