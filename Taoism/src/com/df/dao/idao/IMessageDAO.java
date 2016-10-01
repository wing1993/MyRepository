package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.User;

public interface IMessageDAO extends IBaseHibernateDAO<Message, Integer> {
	/**
	 * 查找最近的六条消息
	 * @return
	 * @throws Exception
	 */
	List<Message> findLatest()throws Exception;
	
	/**
	 * 通过作者查找消息
	 * @return
	 * @throws Exception
	 */
	List<Message> findByAuthor(User user)throws Exception;
	
	/**
	 * 根据页码查找页面数据
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
    DataPage<Message> findAlldata(int currentPage) throws Exception;
}
