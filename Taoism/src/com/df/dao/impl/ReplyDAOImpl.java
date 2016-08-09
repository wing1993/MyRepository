package com.df.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.df.dao.idao.IReplyDAO;
import com.df.dao.pojo.Reply;
import com.df.dao.pojo.QueryResult;
import com.df.dao.util.HibernateSessionFactory;




public class ReplyDAOImpl  implements IReplyDAO  {
	
	@Override
    public void save(Reply transientInstance) {
    	Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
    }
	@Override
	public void delete(Reply persistentInstance) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.delete(persistentInstance);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
    }
   
    
 
	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findAll() {
		Session session = null;
		Transaction transaction = null;
		List<Reply> replyList = new ArrayList<Reply>();
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			replyList = session.createQuery(
					"FROM Reply")
					.list();
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return replyList;
	}

	@Override
	public void update(Reply reply) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(reply);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		
	}
	@Override
	public Reply getById(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			Reply reply = (Reply)session.get(Reply.class, id);
			
			transaction.commit();
			return reply;
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
		System.out.println(firstResult+"---"+maxResults);
		Session session = null;
		Transaction transaction = null;
		List<Reply> replyList = new ArrayList<Reply>();
		Long count = (long) 0;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//查询总记录数
			count = (Long) session.createQuery(
					"SELECT COUNT(*) FROM Reply") 	
					.uniqueResult();  
			System.out.println(firstResult+"---"+maxResults+"---"+count);
			//查询一页的数据列表
			replyList = session.createQuery(
					"FROM Reply")
					.setFirstResult(firstResult)
					.setMaxResults(maxResults)
					.list();
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return new QueryResult(count.intValue(),replyList);
	}
	
	
}