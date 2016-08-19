package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;

@Repository("questionDao")
public class QuestionDAOImpl implements IQuestionDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void save(Question transientInstance) {
		sessionFactory.getCurrentSession().save(transientInstance);
	}

	@Override
	public void delete(Question persistentInstance) {
		sessionFactory.getCurrentSession().delete(persistentInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findAll() {
		List<Question> questionList = new ArrayList<Question>();
		questionList = sessionFactory.getCurrentSession()
				.createQuery("FROM Question").list();
		return questionList;
	}

	@Override
	public void update(Question question) {
		sessionFactory.getCurrentSession().update(question);
	}

	@Override
	public Question getById(Integer id) {
		Question question = (Question) sessionFactory.getCurrentSession().get(
				Question.class, id);
		return question;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {
		List<Question> questionList = new ArrayList<Question>();
		Long count = (long) 0;
		// 查询总记录数
		count = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM Question").uniqueResult();
		System.out.println(firstResult + "---" + maxResults + "---" + count);
		// 查询一页的数据列表
		questionList = sessionFactory.getCurrentSession()
				.createQuery("FROM Question").setFirstResult(firstResult)
				.setMaxResults(maxResults).list();

		return new QueryResult(count.intValue(), questionList);
	}

}