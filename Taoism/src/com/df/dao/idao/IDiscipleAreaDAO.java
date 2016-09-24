package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.DiscipleArea;
import com.df.dao.pojo.QueryCriteria;

public interface IDiscipleAreaDAO extends IBaseHibernateDAO<DiscipleArea, Integer> {
	public List<DiscipleArea> findByDynamicData(QueryCriteria t) throws Exception;
}
