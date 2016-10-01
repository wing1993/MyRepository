package com.df.dao.impl;

// default package

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IMessageDAO;
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;
import com.df.dao.util.PageUtil;

@Repository("messageDao")
public class MessageDAOImpl implements IMessageDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void save(Message transientInstance) {
		sessionFactory.getCurrentSession().save(transientInstance);
	}

	@Override
	public void delete(Message persistentInstance) {
		sessionFactory.getCurrentSession().delete(persistentInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findAll() {
		List<Message> messageList = new ArrayList<Message>();
		messageList = sessionFactory.getCurrentSession()
				.createQuery("FROM Message order by publish_time desc").list();
		return messageList;
	}

	@Override
	public void update(Message message) {
		sessionFactory.getCurrentSession().update(message);
	}

	@Override
	public Message getById(Integer id) {
		Message message = (Message) sessionFactory.getCurrentSession().get(
				Message.class, id);
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
		List<Message> messageList = new ArrayList<Message>();
		Long count = (long) 0;

		// 查询总记录数
		count = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM Message").uniqueResult();
		System.out.println(firstResult + "---" + maxResults + "---" + count);
		// 查询一页的数据列表
		messageList = sessionFactory.getCurrentSession()
				.createQuery("FROM Message").setFirstResult(firstResult)
				.setMaxResults(maxResults).list();
		return new QueryResult(count.intValue(), messageList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findLatest() throws Exception {
		List<Message> Latest=null;
		String hql="from Message order by publish_time desc";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setMaxResults(5);//hql不能用limit
		Latest=query.list();
		return Latest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findByAuthor(User user) throws Exception {
		List<Message> list=null;
		String hql="from Message where author=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql).setString(0, user.getUsername());
		list=query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public DataPage<Message> findAlldata(int currentPage){
		Long count = null;
		count = (Long)sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM Message")
				.uniqueResult();
		List<Message> messageList = new ArrayList<Message>();
		messageList = sessionFactory.getCurrentSession()
				.createQuery("FROM Message order by publish_time desc")
				.setFirstResult((currentPage-1)*3)
				.setMaxResults(3).list();
		DataPage<Message> dp = PageUtil.paging(messageList,count.intValue(), currentPage);
		return dp;
	}
	
	

}