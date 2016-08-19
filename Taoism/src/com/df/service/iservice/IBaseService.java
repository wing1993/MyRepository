package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.QueryResult;

public interface IBaseService<T, K> {

	String save(T t);

	String delete(T t);

	String update(T newObj);

	List<T> findAll();

	T getById(K k);

	QueryResult findAll(K k1, K k2);

}
