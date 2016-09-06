package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.Message;

public interface IMessageDAO extends IBaseHibernateDAO<Message, Integer> {
	/**
	 * 查找最近的六条消息
	 * @return
	 * @throws Exception
	 */
	List<Message> findLatest()throws Exception;
}
