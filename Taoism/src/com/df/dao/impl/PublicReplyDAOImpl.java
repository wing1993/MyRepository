package com.df.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.df.dao.idao.IPublicReplyDAO;
import com.df.dao.pojo.PublicReply;
import com.df.dao.pojo.QueryResult;
@Repository("publicReplyDao")
public class PublicReplyDAOImpl implements IPublicReplyDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void save(PublicReply t) throws Exception {
		sessionFactory.getCurrentSession().save(t);
	}
	
	@Override
	public int saveReply(PublicReply t) throws Exception {
		Serializable pKey = sessionFactory.getCurrentSession().save(t);
		return Integer.parseInt(pKey.toString());
	}
	
	@Override
	public void delete(PublicReply t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PublicReply newObj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PublicReply getById(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PublicReply> findAll() throws Exception {
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
		List<Object> publicReplyList = new ArrayList<Object>();
		publicReplyList = sessionFactory.getCurrentSession()
				.createQuery("FROM PublicReply p where p.question.QId=? ORDER BY replyTime")
				.setInteger(0,k1 )
				.list();
		//System.out.println(publicReplyList);
		return publicReplyList;
	}

	@Override
	public void shieldReply(int replyId, String con1) throws Exception{
		sessionFactory.getCurrentSession()
			.createQuery("UPDATE PublicReply p SET p.con1=? WHERE p.replyId=?")
			.setString(0, con1)
			.setInteger(1, replyId)
			.executeUpdate();
	}
}
