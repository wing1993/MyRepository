package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.User;

public interface IMessageService extends IBaseService<Message, Integer> {
	/**
	 * 查找最近的6条信息
	 * @return
	 */
	List<Message> findLatest();
	
	/**
	 * 通过作者查找消息
	 * @return
	 */
	List<Message> findByAuthor(User user);
	
	/**
	 * 分页查找消息记录
	 * @param currentPage
	 * @return
	 */
	DataPage<Message> findAlldata(int currentPage);
}
