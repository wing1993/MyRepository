package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.PublicReply;

public interface IPublicReplyDAO extends
		IBaseHibernateDAO<PublicReply, Integer> {

	public List<Object> findByQid(Integer k1) throws Exception;
}
