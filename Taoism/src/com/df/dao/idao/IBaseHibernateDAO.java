package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.QueryResult;

/**
 * Data access interface for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO<T, K> {

	/**
	 * 保存
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void save(T t) throws Exception;

	/**
	 * 删除
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void delete(T t) throws Exception;

	/**
	 * 更新
	 * 
	 * @param newObj
	 * @throws Exception
	 */
	public void update(T newObj) throws Exception;

	/**
	 * 根据id查找对象
	 * 
	 * @param k
	 * @return
	 * @throws Exception
	 */
	public T getById(K k) throws Exception;

	/**
	 * 查询所有数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception;

	/**
	 * 分页查找数据
	 * 
	 * @param k1
	 *            起止数据
	 * @param k2
	 *            一页的数据条数
	 * @return
	 * @throws Exception
	 */
	public QueryResult findAll(K k1, K k2) throws Exception;
}