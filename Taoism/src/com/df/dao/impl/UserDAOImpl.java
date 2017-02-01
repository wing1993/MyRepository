package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;

@Repository("userDao")
public class UserDAOImpl implements IUserDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private User u;

	@Override
	public void save(User transientInstance) throws Exception {
		transientInstance.setState(0);
		sessionFactory.getCurrentSession().save(transientInstance);

	}

	@Override
	public void delete(User persistentInstance) {

		sessionFactory.getCurrentSession().save(persistentInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() throws Exception {

		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession()
				.createQuery("FROM User u where u.state=1").list();

		return userList;
	}

	@Override
	public void update(User user) {

		sessionFactory.getCurrentSession().update(user);

	}

	@Override
	public User getById(Integer id) {

		User user = (User) sessionFactory.getCurrentSession().get(User.class,
				id);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {

		List<User> userList = new ArrayList<User>();
		Long count = (long) 0;

		// 查询总记录数
		count = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM User").uniqueResult();
		System.out.println(firstResult + "---" + maxResults + "---" + count);
		// 查询一页的数据列表
		userList = sessionFactory.getCurrentSession().createQuery("FROM User")
				.setFirstResult(firstResult).setMaxResults(maxResults).list();

		return new QueryResult(count.intValue(), userList);
	}

	@Override
	public String login(User user) throws Exception {
		User loginUser = null;

		loginUser = (User) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username=? and u.password=? and u.state=1")
				.setString(0, user.getUsername())
				.setString(1, user.getPassword()).uniqueResult();

		return loginUser != null ? "success" : "error";
	}

	@Override
	public void registry(User user) throws Exception {

		// 在事务中运行
		user.setState(0);// 待审核状态
		sessionFactory.getCurrentSession().save(user);

	}

	@Override
	public void examine(User user) throws Exception {
		// 审核成功将state置1
		user.setState(1);
		sessionFactory.getCurrentSession().update(user);
	}

	

	@Override
	public void changeUserType(User user) throws Exception {

		// 审核通过将con1标志 赋值给userType属性
		user.setUserType(user.getCon1());
		user.setCon1(null);
		sessionFactory.getCurrentSession().update(user);

	}

	@Override
	public User findByUsername(User user) throws Exception {
		user = (User) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username=? and u.password=? and u.state=1")
				.setString(0, user.getUsername())
				.setString(1, user.getPassword()).uniqueResult();
		/*List<User> users = new ArrayList<User>();
		users.add(user);*/
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByMail(User user) throws Exception {
		List<User> users = null;
		users = (List<User>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.mail=?")
				.setString(0, user.getMail()).list();
		
		return users;
	}

	@Override
	public User findSameName(User user) throws Exception {
		u=(User) sessionFactory.getCurrentSession()
				.createQuery("from User u where u.username=?")
				.setString(0, user.getUsername()).uniqueResult();	
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUnexamined() throws Exception {
		List<User> users=new ArrayList<User>();
		String hql="from User u where u.state=0";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		users=query.list();
		return users;
	}

	@Override
	public int queryResultsCount() throws Exception {
		// 查询总记录数
		Long resultCount =  (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM User u where u.state=0").uniqueResult();
		return resultCount.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryByPage(int firstResult, int maxResults)throws Exception {
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery("FROM User u where u.state=0")
				.setFirstResult(firstResult).setMaxResults(maxResults).list();

		return userList;
	}

}