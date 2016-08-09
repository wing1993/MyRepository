package com.df.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.QueryResult;
import com.df.dao.util.HibernateSessionFactory;




public class QuestionDAOImpl  implements IQuestionDAO  {
	

	@Override
    public void save(Question transientInstance) {
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
	public void delete(Question persistentInstance) {
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
	public List<Question> findAll() {
		Session session = null;
		Transaction transaction = null;
		List<Question> questionList = new ArrayList<Question>();
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			questionList = session.createQuery(
					"FROM Question")
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
		return questionList;
	}

	@Override
	public void update(Question question) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			session.update(question);
			
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
	public Question getById(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			Question question = (Question)session.get(Question.class, id);
			
			transaction.commit();
			return question;
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
		List<Question> questionList = new ArrayList<Question>();
		Long count = (long) 0;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			//查询总记录数
			count = (Long) session.createQuery(
					"SELECT COUNT(*) FROM Question") 	
					.uniqueResult();  
			System.out.println(firstResult+"---"+maxResults+"---"+count);
			//查询一页的数据列表
			questionList = session.createQuery(
					"FROM Question")
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
		return new QueryResult(count.intValue(),questionList);
	}
	
	
}