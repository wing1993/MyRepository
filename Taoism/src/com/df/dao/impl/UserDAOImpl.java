package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.User;

@Repository("userDao")
public class UserDAOImpl  extends BaseDAOSupport implements IUserDAO{

	/*@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;*/
	private User u;

	@Override
	public void save(User transientInstance) throws Exception {
		transientInstance.setState(0);
		this.getSessionFactory().getCurrentSession().save(transientInstance);

	}

	@Override
	public void delete(User persistentInstance) {

		this.getSessionFactory().getCurrentSession().save(persistentInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() throws Exception {

		List<User> userList = new ArrayList<User>();
		userList = this.getSessionFactory().getCurrentSession()
				.createQuery("FROM User u where u.state=1").list();

		return userList;
	}

	@Override
	public void update(User user) throws Exception{

		this.getSessionFactory().getCurrentSession().update(user);

	}

	@Override
	public User getById(Integer id) {

		User user = (User) this.getSessionFactory().getCurrentSession().get(User.class,
				id);
		return user;
	}
	
	@Override
	public User getByUsername(User user) throws Exception{
		
		user = (User) this.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from User u where u.username=?")
				.setString(0, user.getUsername()).uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult findAll(Integer firstResult, Integer maxResults) {

		List<User> userList = new ArrayList<User>();
		Long count = (long) 0;

		// 查询总记录数
		count = (Long) this.getSessionFactory().getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM User").uniqueResult();
		System.out.println(firstResult + "---" + maxResults + "---" + count);
		// 查询一页的数据列表
		userList = this.getSessionFactory().getCurrentSession().createQuery("FROM User")
				.setFirstResult(firstResult).setMaxResults(maxResults).list();

		return new QueryResult(count.intValue(), userList);
	}

	@Override
	public String login(User user) throws Exception {
		User loginUser = null;

		loginUser = (User) this.getSessionFactory()
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
		this.getSessionFactory().getCurrentSession().save(user);

	}

	@Override
	public void examine(User user) throws Exception {
		// 审核成功将state置1
		user.setState(1);
		this.getSessionFactory().getCurrentSession().update(user);
	}

	

	@Override
	public void changeUserType(User user) throws Exception {

		// 审核通过将con1标志 赋值给userType属性
		user.setUserType(user.getCon1());
		user.setCon1("");
		this.getSessionFactory().getCurrentSession().update(user);

	}

	@Override
	public User findByUsername(User user) throws Exception {
		user = (User) this.getSessionFactory()
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
		users = (List<User>) this.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from User u where u.mail=?")
				.setString(0, user.getMail()).list();
		
		return users;
	}

	@Override
	public User findSameName(User user) throws Exception {
		u=(User) this.getSessionFactory().getCurrentSession()
				.createQuery("from User u where u.username=?")
				.setString(0, user.getUsername()).uniqueResult();	
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUnexamined() throws Exception {
		List<User> users=new ArrayList<User>();
		String hql="from User u where u.state=0";
		Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		users=query.list();
		return users;
	}

	
	/**
	 *  查询未审核用户总记录数
	 */
	public int queryCountState0(User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM User u where u.state=0");
		if(null!=user&&null!=user.getUserType()&&""!=user.getUserType()){
			System.out.println("1"+user.getUserType());
			sql.append(" and userType = '"+ user.getUserType()+"'");
		}
		return this.queryResultsCount(sql.toString());
	}

	/**
	 * 查询未审核用户记录
	 */
	public List<Object[]> queryListState0(int from, int length, User user)throws Exception {
		System.out.println("dao层  from："+from+"  length:"+length);
		Session session = this.getSessionFactory().openSession();
		DetachedCriteria dc = DetachedCriteria. forClass (User. class );
		dc.add(Restrictions.eq("state", 0));
		if(null!=user&&null!=user.getUserType()&&""!=user.getUserType()){
			dc.add(Restrictions.eq("userType", user.getUserType()));
		}
		Criteria c = dc.getExecutableCriteria(session);
		c.setMaxResults(length);
	    c.setFirstResult(from);
	    return c.list();
		//String sql = "FROM User u where u.state=0";
		//return this.queryByPage_1(sql, from, length);
	}
	
	/*
	 * 查询申请身份升级的用户记录数
	 */
	public int queryCountUpgrade() throws Exception {
		String sql = "SELECT COUNT(*) FROM User u where u.con1 <> ''";
		return this.queryResultsCount(sql);
	}
	
	/*
	 * 查询申请身份升级的用户记录
	 */
	/*public List<Object[]> queryListUpgrade(int from, int length)throws Exception {
		String sql = "SELECT u.userId as userId,u.realname as realname,u.username as username,u.userType as userType,u.introduce as introduce,"
				+ "u.con1 as con1,COUNT(q.QId) as sumQuestion FROM User u,Question q "
				+ "where u.con1 <> '' and u.username=q.username group by u.userId,u.username,u.userType,u.introduce,u.con1";
		return this.queryByPage(sql, from, length);
	}
	*/
	public List<Object[]> queryListUpgrade(int from, int length)throws Exception {
		String sql = "from User u where u.con1 <> ''";
		return this.queryByPage_1(sql, from, length);
	}

	public List<Object[]> queryListUserinfo(int from, int length, User user)
			throws Exception {

		Session session = this.getSessionFactory().openSession();
		DetachedCriteria dc = DetachedCriteria. forClass (User. class );
		if(null!=user&&null!=user.getUserType()&&""!=user.getUserType()){
			dc.add(Restrictions.eq("userType", user.getUserType()));
		}
		if(null!=user&&null!=user.getUsername()&&""!=user.getUsername()){
			dc.add(Restrictions.like("username", user.getUsername(), MatchMode.ANYWHERE));
		}
		Criteria c = dc.getExecutableCriteria(session);
		c.setMaxResults(length);
	    c.setFirstResult(from);
	    return c.list();
	}

	public int queryCountUserinfo(User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM User u WHERE 1=1 ");
		if(null!=user&&null!=user.getUserType()&&""!=user.getUserType()){
			System.out.println("1"+user.getUserType());
			sql.append(" AND userType = '"+ user.getUserType()+"'");
		}
		if(null!=user&&null!=user.getUsername()&&""!=user.getUsername()){
			sql.append(" AND username LIKE CONCAT(CONCAT('%', "+user.getUsername()+"),'%')");
		}
		return this.queryResultsCount(sql.toString());
	}

	@Override
	public List<User> findDiscipleList() throws Exception {
		List<User> userList=new ArrayList<User>();
		String hql="select new com.df.dao.pojo.User(u.userId,u.username,u.password) from User u where u.state=1 and u.userType='弟子'";
		Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
		userList = query.list();System.out.println(userList);
		return userList;
	}

	
	 
	
}