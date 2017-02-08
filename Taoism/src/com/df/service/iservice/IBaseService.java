package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.QueryResult;

public interface IBaseService<T, K> {

	String save(T t) throws Exception;

	String delete(T t) throws Exception;

	String update(T newObj) throws Exception;

	List<T> findAll() throws Exception;

	T getById(K k) throws Exception;

	QueryResult findAll(K k1, K k2) throws Exception;

}
