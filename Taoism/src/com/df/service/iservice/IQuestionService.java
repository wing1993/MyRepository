package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.Question;

public interface IQuestionService extends IBaseService<Question, Integer> {
	//查询的总记录数
	public int selectSumCount() throws Exception;
	
	public List<Question> findByDynamicData(Question question) throws Exception;
}
