package com.df.dao.idao;


import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Question;

public interface IQuestionDAO extends IBaseHibernateDAO<Question, Integer> {
	public int selectSumCount() throws Exception;

	
	/**
	 * 通过前台的查询条件查找数据
	 * 按照以下三种方式之一或组合显示问题：
	 * 按问题板块查看、按问题分类查看、按时间远近查看（今天、三天、七天、一个月）。
	 * 另外，也可按提问者、是否回复（未回复、已回复）来显示问题
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public DataPage<Question> findByDynamicData(Question question,int currentPage,String userType)throws Exception;

    /**
     * 当用户点击问题之后问题的阅读量+1
     * @param k1   问题的QId
     * @throws Exception
     */
	public void addReadTimes(Integer k1) throws Exception;

	/**
	 * 最后回复的时间，以及用户名
	 */
	public void updateLastReplyData(String username, String lastReplyTime, Integer qId) throws Exception;

	/**
	 * 大师将对他提问的帖子转发到其他区    修改sharezone为转发的区域，shareState为0，只能转发一次
	 * @param question
	 */
	public void updateSharezone(Question question)throws Exception;


}
