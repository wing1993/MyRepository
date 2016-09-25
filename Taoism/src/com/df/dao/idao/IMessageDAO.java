package com.df.dao.idao;

import java.util.List;

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
}
