package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.DiscipleReply;

public interface IDiscipleReplyDAO extends IBaseHibernateDAO<DiscipleReply, Integer> {
	public List<Object> findByQid(Integer k1) throws Exception ;
}
