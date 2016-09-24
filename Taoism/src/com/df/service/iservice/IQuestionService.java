package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;

public interface IQuestionService extends IBaseService<Question, Integer> {
	//查询的总记录数
	public int selectSumCount() throws Exception;
	/**
	 * 贴的动态数据查询  
	 * @param question      查询的内容  根据回复状态   问题类型   问题区域  发帖时间
	 * @param currentPage   分页查询  需要查询的页码
	 * @param userType      根据不同的用户类型查询不同的内容
	 * @return
	 * @throws Exception
	 */
	public DataPage<Question> findByDynamicData(Question question,int currentPage,String userType) throws Exception;
}
