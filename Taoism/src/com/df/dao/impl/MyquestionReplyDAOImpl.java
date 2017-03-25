package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IMyquestionReplyDAO;
import com.df.dao.pojo.MyquestionReply;
import com.df.dao.pojo.QueryResult;
@Repository("myquestionReplyDao")
public class MyquestionReplyDAOImpl implements IMyquestionReplyDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void save(MyquestionReply t) throws Exception {
		sessionFactory.getCurrentSession().save(t);
		}

	@Override
	public void delete(MyquestionReply t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MyquestionReply newObj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MyquestionReply getById(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyquestionReply> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByQid(Integer k1) throws Exception {
		List<Object> myquestionList = new ArrayList<Object>();
		myquestionList = sessionFactory.getCurrentSession()
				.createQuery("FROM MyquestionReply m where m.question.QId=? ORDER BY replyTime")
				.setInteger(0,k1 )
				.list();
		return myquestionList;
	}

	@Override
	public void shieldReply(int replyId, String con1)throws Exception {
		sessionFactory.getCurrentSession()
			.createQuery("UPDATE MyquestionReply m SET m.con1=? WHERE m.replyId=?")
			.setString(0, con1)
			.setInteger(1, replyId)
			.executeUpdate();
	}
}
