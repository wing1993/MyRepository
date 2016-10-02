package com.df.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IDiscipleReplyDAO;
import com.df.dao.pojo.DiscipleReply;
import com.df.dao.pojo.QueryResult;

@Repository("discipleReplyDao")
public class DiscipleReplyDAOImpl implements IDiscipleReplyDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void save(DiscipleReply t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DiscipleReply t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DiscipleReply newObj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public DiscipleReply getById(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiscipleReply> findAll() throws Exception {
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
		List<Object> discipleReplyList = new ArrayList<Object>();
		discipleReplyList = sessionFactory.getCurrentSession()
				.createQuery("FROM DiscipleReply d where d.question.QId=4 ORDER BY d.replyId")
				.list();
		System.out.println("5555555555555555"+discipleReplyList);
		return discipleReplyList;
	}
}
