package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IReplyDAO;
import com.df.dao.pojo.Reply;
import com.df.dao.pojo.QueryResult;

@Repository("replyDao")
public class ReplyDAOImpl implements IReplyDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void save(Reply transientInstance) {
		sessionFactory.getCurrentSession().save(transientInstance);
	}

	@Override
	public void delete(Reply persistentInstance) {
		sessionFactory.getCurrentSession().delete(persistentInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findAll() {
		List<Reply> replyList = new ArrayList<Reply>();
		replyList = sessionFactory.getCurrentSession()
				.createQuery("FROM Reply").list();
		return replyList;
	}

	@Override
	public void update(Reply reply) {
		sessionFactory.getCurrentSession().update(reply);
	}

	@Override
	public Reply getById(Integer id) {
		Reply reply = (Reply) sessionFactory.getCurrentSession().get(
				Reply.class, id);
		return reply;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
		List<Reply> replyList = new ArrayList<Reply>();
		Long count = (long) 0;
		// 查询总记录数
		count = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM Reply").uniqueResult();
		// 查询一页的数据列表
		replyList = sessionFactory.getCurrentSession()
				.createQuery("FROM Reply").setFirstResult(firstResult)
				.setMaxResults(maxResults).list();
		return new QueryResult(count.intValue(), replyList);
	}

}