package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.df.dao.pojo.Message;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
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
				.createQuery("FROM Question")
				.setFirstResult(firstResult)
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
	    DataPage<Question> dp = PageUtil.paging(questionList,count.intValue(),currentPage,10);
		System.out.println("查询的记录"+questionList);
		System.out.println("查询的数量"+count );
		System.out.println("dao层"+dp.gettList().toString());
		return dp;
	}

	@Override
	public void addReadTimes(Integer k1) throws Exception {
		System.out.println("dao层addReadTimes函数获取的qid为"+k1);
		sessionFactory.getCurrentSession()
				.createQuery("UPDATE Question q SET q.visits=q.visits+1 WHERE q.QId=?")
				.setInteger(0, k1)
				.executeUpdate();
		
	}

	/**
	 * 将问题最后回复的用户，时间，保存到Question中，回复人数+1，是否回复标志state置1
	 */
	@Override
	public void updateLastReplyData(String username, String lastReplyTime,Integer qId)
			throws Exception {
		sessionFactory.getCurrentSession()
			.createQuery("UPDATE Question q SET state=1,q.con1=?,q.con2=?,q.con3=q.con3+1 WHERE q.QId=?")
			.setString(0, username).setString(1, lastReplyTime).setInteger(2, qId)
			.executeUpdate();

		
	}

	/**
	 * 大师将提问他的问题分享到其他区域
	 */
	@Override
	public void updateSharezone(Question question) throws Exception {
		sessionFactory.getCurrentSession()
			.createQuery("UPDATE Question q SET q.sharezone=?,q.shareState=0 WHERE q.QId=?")
			.setString(0,question.getSharezone()).setInteger(1, question.getQId()).executeUpdate();
		
	}
	
	
	/**
	 * 获取查询的记录数
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public int queryCountQuestioninfo(Map<String, Object> map) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM User u where 1=1 ");
		if(null!=map.get("startTime")&&null!=map.get("endTime")){
			sql.append(" and QTime >= '"+ map.get("startTime").toString()
					+"' and QTime <= '"+map.get("endTime").toString()+"'");
		}if(null!=map.get("username")){
			sql.append(" and username = '"+ map.get("username").toString());
		}
		Long resultCount =  (Long) sessionFactory.getCurrentSession()
				.createQuery(sql.toString()).uniqueResult();
		return resultCount.intValue();
	}

	/**
	 * 根据  时间 ||发帖人   获取帖子记录
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> queryListQuestioninfo(Map<String, Object> map)
			throws Exception {
		DetachedCriteria dc = DetachedCriteria. forClass (Question. class );
		if(null!=map.get("startTime")&&null!=map.get("endTime")){
			dc.add(Restrictions.between("QTime", map.get("startTime").toString(),
					map.get("endTime").toString()));
		}
		if(null!=map.get("username")){
			dc.add(Restrictions.eq("username", map.get("username").toString()));
		}

		Criteria c = dc.getExecutableCriteria(sessionFactory.getCurrentSession());
		c.setMaxResults(Integer.parseInt(map.get("length").toString()));
	    c.setFirstResult(Integer.parseInt(map.get("from").toString()));
	    return c.list();
	}

	/**
	 * 管理员 帖子管理
	 */
	@Override
	public DataPage<Question> findByQTime(Map<String, Object> map)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM Question u where 1=1 ");
		if(null!=map.get("startTime")&&!"".equals(map.get("startTime").toString())
				&&null!=map.get("endTime")&&!"".equals(map.get("endTime").toString())){
			sql.append(" and u.QTime >= '"+ map.get("startTime").toString()
					+"' and u.QTime <= '"+map.get("endTime").toString()+"'");
		}if(null!=map.get("username")){
			sql.append(" and u.username = '"+ map.get("username").toString()+"'");
		}
		Long resultCount =  (Long) sessionFactory.getCurrentSession()
				.createQuery(sql.toString()).uniqueResult();

		DetachedCriteria dc = DetachedCriteria. forClass (Question. class );
		if(null!=map.get("startTime")&&!"".equals(map.get("startTime").toString())
				&&null!=map.get("endTime")&&!"".equals(map.get("endTime").toString())){
			dc.add(Restrictions.between("QTime", map.get("startTime").toString(),
					map.get("endTime").toString()));
		}
		if(null!=map.get("username")){
			dc.add(Restrictions.eq("username", map.get("username").toString()));
		}

		int rows = Integer.parseInt(map.get("rows").toString());
		int currentPage = Integer.parseInt(map.get("currentPage").toString());
		Criteria c = dc.getExecutableCriteria(sessionFactory.getCurrentSession());
		c.setMaxResults(rows);
	    c.setFirstResult((currentPage-1)*rows);
		
	    DataPage<Question> dp = PageUtil.paging(c.list(),resultCount.intValue(),currentPage,rows);
		return dp;
	}

	
}