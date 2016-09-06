package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.Message;

public interface IMessageService extends IBaseService<Message, Integer> {
	/**
	 * 查找最近的6条信息
	 * @return
	 */
	List<Message> findLatest();
}
