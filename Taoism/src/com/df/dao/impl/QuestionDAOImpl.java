package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;

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

	@Override
	public int selectSumCount() throws Exception {
		int count = 0;
		// 查询总记录数
		count =  (int) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM Question")
				.uniqueResult();
		return count;
	}

	@Override
	public List<Question> findByDynamicData(Question question,User user) throws Exception {
		Session session = sessionFactory.openSession();
		DetachedCriteria dc = DetachedCriteria. forClass (Question. class );
		System.out.println(question.getSharezone()+"========="+(question.getSharezone()!=null));
		System.out.println("---------------");
		System.out.println(question.toString());
		if("所有问题".equals(question.getSharezone())){
			String[] sharezone = null; 
			if("普通".equals(user.getUserType())){
				sharezone = new String[] {"公开区"};
				dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
						Restrictions.eq("username", question.getUsername())));
			}else if("学员".equals(user.getUserType())){
				sharezone = new String[] {"公开区","学员区"};
				dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
						Restrictions.eq("username", question.getUsername())));
			}else if("弟子".equals(user.getUserType())||"老先生".equals(user.getUserType())){
				sharezone = new String[] {"公开区","学员区","弟子区"};
				dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
						Restrictions.eq("username", question.getUsername()),
						Restrictions.eq("askWho", question.getAskWho())));
			}	
		}else{
			if(!"".equals(question.getSharezone())){
				dc.add(Restrictions.eq("sharezone", question.getSharezone()));
			}
			if(!"".equals(question.getQTypeName())){
				dc.add(Restrictions.eq("QTypeName", question.getQTypeName()));
			}
			if(!"".equals(question.getQTime())){
				dc.add(Restrictions.ge("QTime", question.getQTime()));
			}
			if((question.getState()!=null)){
				dc.add(Restrictions.eq("state", question.getState()));
			}
			if(question.getAskWho()!=null){
				dc.add(Restrictions.eq("askWho", question.getAskWho()));
			}
		}
		
		Criteria c = dc.getExecutableCriteria(session);
		List<Question> questionList = c.list();
		System.out.println("dao层"+questionList.toString());
		return questionList;
	}


}