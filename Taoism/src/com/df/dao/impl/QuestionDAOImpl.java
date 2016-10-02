package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.util.PageUtil;

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
		//String str = null;
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

	@SuppressWarnings("unchecked")
	@Override
	public DataPage<Question> findByDynamicData(Question question,int currentPage,String userType) throws Exception {
		System.out.println("前台获取的question"+question);
		Session session = sessionFactory.openSession();
		DetachedCriteria dc = DetachedCriteria. forClass (Question. class );
		DetachedCriteria dc1 = DetachedCriteria. forClass (Question. class );
		System.out.println(question.getSharezone()+"========="+(question.getSharezone()!=null));
		System.out.println("---------------");
		System.out.println(question.toString());
		if(!"".equals(question.getQTitle())){
			dc.add(Restrictions.like("QTitle",question.getQTitle(),MatchMode.ANYWHERE));
			dc1.add(Restrictions.like("QTitle",question.getQTitle(),MatchMode.ANYWHERE));
		}
		else {
			if("所有问题".equals(question.getSharezone())){
				String[] sharezone = null; 
				if("".equals(userType)){
					sharezone = new String[] {"公开区"};
					dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone)));
					dc1.add(Restrictions.or(Restrictions.in("sharezone", sharezone)));
				}
				if("普通".equals(userType)){
					sharezone = new String[] {"公开区"};
					dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
							Restrictions.eq("username", question.getUsername())));
					dc1.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
							Restrictions.eq("username", question.getUsername())));
				}else if("学员".equals(userType)){
					sharezone = new String[] {"公开区","学员区"};
					dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
							Restrictions.eq("username", question.getUsername())));
					dc1.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
							Restrictions.eq("username", question.getUsername())));
				}else if("弟子".equals(userType)||"老先生".equals(userType)){
					sharezone = new String[] {"公开区","学员区","弟子区"};
					dc.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
							Restrictions.eq("username", question.getUsername())));
					dc1.add(Restrictions.or(Restrictions.in("sharezone", sharezone),
							Restrictions.eq("username", question.getUsername())));
				}	
			}else{
				if("公开区".equals(question.getSharezone())||"弟子区".equals(question.getSharezone())
						||"学员区".equals(question.getSharezone())){System.out.println("1");
					dc.add(Restrictions.eq("sharezone", question.getSharezone()));
					dc1.add(Restrictions.eq("sharezone", question.getSharezone()));
				}
				if("我的问题".equals(question.getSharezone())){System.out.println("5");
						dc.add(Restrictions.and(Restrictions.eq("username", question.getUsername()),
								Restrictions.eq("sharezone", question.getSharezone())));
						dc1.add(Restrictions.and(Restrictions.eq("username", question.getUsername()),
								Restrictions.eq("sharezone", question.getSharezone())));
				}
				if("答疑区".equals(question.getSharezone())){System.out.println("6");
						dc.add(Restrictions.eq("askWho", question.getAskWho()));
						dc1.add(Restrictions.eq("askWho", question.getAskWho()));
				}
				if(!"".equals(question.getQTypeName())){System.out.println("2");
					dc.add(Restrictions.eq("QTypeName", question.getQTypeName()));
					dc1.add(Restrictions.eq("QTypeName", question.getQTypeName()));
				}
				if(question.getQTime()!=null){System.out.println("3");
					dc.add(Restrictions.ge("QTime", question.getQTime()));
					dc1.add(Restrictions.ge("QTime", question.getQTime()));
				}
				if(question.getState()!=null){System.out.println("4");
					dc.add(Restrictions.eq("state", question.getState()));
					dc1.add(Restrictions.eq("state", question.getState()));
				}
			}
		}
		Criteria c = dc.getExecutableCriteria(session);
		Criteria c1 = dc1.getExecutableCriteria(session);
		//总记录数
		Long count = (long)c1.setProjection(Projections.rowCount())
	            .uniqueResult();
	    c.setMaxResults(10);
	    c.setFirstResult((currentPage - 1) * 10);
		List<Question> questionList = c.list();
		System.out.println("daoceng"+questionList);
	    DataPage<Question> dp = PageUtil.paging(questionList,count.intValue(),currentPage);
		System.out.println("查询的记录"+questionList);
		System.out.println("查询的数量"+count );
		System.out.println("dao层"+dp.gettList().toString());
		return dp;
	}

	@Override
	public void addReadTimes(Integer k1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}