package com.df.dao.impl;
// default package

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IQuestionTypeDAO;
import com.df.dao.pojo.QuestionType;
import com.df.dao.pojo.QueryResult;



@Repository("questionTypeDao")
public class QuestionTypeDAOImpl  implements IQuestionTypeDAO  {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
    public void save(QuestionType transientInstance) {
		sessionFactory.getCurrentSession().save(transientInstance);
    }
	
	@Override
	public void delete(QuestionType persistentInstance) {
		sessionFactory.getCurrentSession().delete(persistentInstance);
    }
   
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionType> findAll() {
		List<QuestionType> questionTypeList = new ArrayList<QuestionType>();
		questionTypeList = sessionFactory.getCurrentSession().createQuery(
					"FROM QuestionType")
					.list();
		return questionTypeList;
	}

	@Override
	public void update(QuestionType questionType) {
		sessionFactory.getCurrentSession().update(questionType);	
	}
	
	@Override
	public QuestionType getById(Integer id) {
		QuestionType questionType = (QuestionType)sessionFactory
				.getCurrentSession()
				.get(QuestionType.class, id);
		return questionType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
		List<QuestionType> questionTypeList = new ArrayList<QuestionType>();
		Long count = (long) 0;
		//查询总记录数
		count = (Long) sessionFactory.getCurrentSession().createQuery(
				"SELECT COUNT(*) FROM QuestionType") 	
				.uniqueResult();  
		System.out.println(firstResult+"---"+maxResults+"---"+count);
		//查询一页的数据列表
		questionTypeList = sessionFactory.getCurrentSession().createQuery(
				"FROM QuestionType")
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.list();
		return new QueryResult(count.intValue(),questionTypeList);
	}
	
	
}