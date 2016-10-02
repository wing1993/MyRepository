package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IStudentReplyDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.StudentReply;

@Repository("studentReplyDao")
public class StudentReplyDAOImpl implements IStudentReplyDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	
	@Override
	public void save(StudentReply t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(StudentReply t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(StudentReply newObj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public StudentReply getById(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentReply> findAll() throws Exception {
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
		List<Object> stdentList = new ArrayList<Object>();
		stdentList = sessionFactory.getCurrentSession()
				.createQuery("FROM StudentReply s where s.question.QId=? ORDER BY replyTime")
				.setInteger(0,k1 )
				.list();
		return stdentList;
	}

}
