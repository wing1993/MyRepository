package com.df.dao.impl;
// default package

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.df.dao.idao.IQuestionTypeDAO;
import com.df.dao.pojo.QuestionType;
import com.df.dao.pojo.QueryResult;
import com.df.dao.util.HibernateSessionFactory;




public class QuestionTypeDAOImpl  implements IQuestionTypeDAO  {
	
	@Override
    public void save(QuestionType transientInstance) {
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
	public void delete(QuestionType persistentInstance) {
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
	public List<QuestionType> findAll() {
		Session session = null;
		Transaction transaction = null;
		List<QuestionType> questionTypeList = new ArrayList<QuestionType>();
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			questionTypeList = session.createQuery(
					"FROM QuestionType")
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
		return questionTypeList;
	}

	@Override
	public void update(QuestionType questionType) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(questionType);
			
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
	public QuestionType getById(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			QuestionType questionType = (QuestionType)session.get(QuestionType.class, id);
			
			transaction.commit();
			return questionType;
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
		List<QuestionType> questionTypeList = new ArrayList<QuestionType>();
		Long count = (long) 0;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//查询总记录数
			count = (Long) session.createQuery(
					"SELECT COUNT(*) FROM QuestionType") 	
					.uniqueResult();  
			System.out.println(firstResult+"---"+maxResults+"---"+count);
			//查询一页的数据列表
			questionTypeList = session.createQuery(
					"FROM QuestionType")
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
		return new QueryResult(count.intValue(),questionTypeList);
	}
	
	
}