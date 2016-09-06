package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;

public interface IQuestionService extends IBaseService<Question, Integer> {
	//查询的总记录数
	public int selectSumCount() throws Exception;
	
	public List<Question> findByDynamicData(Question question,String userType) throws Exception;
}
