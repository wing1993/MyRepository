package com.df.dao.impl;
// default package

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.df.dao.idao.IMessageDAO;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.QueryResult;
import com.df.dao.util.HibernateSessionFactory;




public class MessageDAOImpl  implements IMessageDAO  {
	
	@Override
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	@Override
    public void save(Message transientInstance) {
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
	public void delete(Message persistentInstance) {
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
	public List<Message> findAll() {
		Session session = null;
		Transaction transaction = null;
		List<Message> messageList = new ArrayList<Message>();
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			messageList = session.createQuery(
					"FROM Message")
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
		return messageList;
	}

	@Override
	public void update(Message message) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(message);
			
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
	public Message getById(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			Message message = (Message)session.get(Message.class, id);
			
			transaction.commit();
			return message;
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
		List<Message> messageList = new ArrayList<Message>();
		Long count = (long) 0;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//查询总记录数
			count = (Long) session.createQuery(
					"SELECT COUNT(*) FROM Message") 	
					.uniqueResult();  
			System.out.println(firstResult+"---"+maxResults+"---"+count);
			//查询一页的数据列表
			messageList = session.createQuery(
					"FROM Message")
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
		return new QueryResult(count.intValue(),messageList);
	}
	
	
}