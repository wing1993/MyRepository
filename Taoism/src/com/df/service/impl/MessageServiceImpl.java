package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IMessageDAO;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;
import com.df.service.iservice.IMessageService;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {
	@Autowired
	@Qualifier("messageDao")
	private IMessageDAO messageDao;
	private String msg="error";
	
	@Transactional
	@Override
	public String save(Message t) {
		try {
			messageDao.save(t);
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public String delete(Message t) {
		try {
			messageDao.delete(t);
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public String update(Message newObj) {
		try {
			messageDao.update(newObj);
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(msg);
		return msg;
	}

	@Transactional
	@Override
	public List<Message> findAll() {
		List<Message> AllMessage=null;
		try {
			AllMessage=messageDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AllMessage;
	}

	@Override
	public Message getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Message> findLatest() {
		List<Message> latest=null;
		try {
			latest=messageDao.findLatest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latest;
	}

	@Transactional
	@Override
	public List<Message> findByAuthor(User user) {
		List<Message> list=null;
		try {
			list=messageDao.findByAuthor(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
