package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.MyquestionReply;

public interface IMyquestionReplyDAO extends IBaseHibernateDAO<MyquestionReply, Integer> {
	public List<Object> findByQid(Integer k1) throws Exception ;
}
