package com.df.service.iservice;


import java.util.List;
import java.util.Map;

import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Question;

public interface IQuestionService extends IBaseService<Question, Integer> {
	
	/**
	 * 贴的动态数据查询  
	 * @param question      查询的内容  根据回复状态   问题类型   问题区域  发帖时间
	 * @param currentPage   分页查询  需要查询的页码
	 * @param userType      根据不同的用户类型查询不同的内容
	 * @return
	 * @throws Exception
	 */
	public DataPage<Question> findByDynamicData(Question question,int currentPage,String userType);
	/**
	 * 通过点击贴的标题查找出关于该标题下的所有回复
	 * @param i   贴或问题的id
	 * @param s   贴或问题的区域
	 * @return
	 */
	public List<Object> findByQid(Question question)throws Exception;
	/**
	 * 用户回复之后保存最后回复的用户名，以及时间
	 * @param username
	 * @param replyTime
	 * @throws Exception
	 */
	public void updateLastReplyData(String username, String lastReplyTime, Integer qId)throws Exception;
	/**
	 * 大师将用户对他的提问转发到其他区
	 * @param question
	 * @throws Exception
	 */
	public void daShiForwardPost(Question question)throws Exception;
	/**
	 * 根据条件查询帖子   返回记录数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int queryCountQuestioninfo(Map<String, Object> map) throws Exception;
	/**
	 * 根据条件查询帖子  返回查询结果
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Object[]> queryListQuestioninfo(Map<String, Object> map) throws Exception;
	/**
	 * 根据查询条件获取帖子信息    发帖时间   发帖人
	 * @param map
	 * @return
	 */
	public DataPage<Question> findByQTime(Map<String, Object> map)throws Exception;
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public DataPage<Question> findMyPosts(Map<String,Object> map)throws Exception;

}
