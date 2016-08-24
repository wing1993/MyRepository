package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.Question;

public interface IQuestionDAO extends IBaseHibernateDAO<Question, Integer> {
	public int selectSumCount() throws Exception;

	/**
	 * 查找我的问题  用户向大师或者老先生提问的问题
	 * @return
	 * @throws Exception
	 *//*
	public List<Question> findMyquestion(User user) throws Exception;
	*//**
	 * 查找公开区域的问题  大师分享在公开区的问题
	 * @return
	 * @throws Exception
	 *//*
	public List<Question> findOpenarea() throws Exception;
	*//**
	 * 查找答疑区  大师或者老先生解答其他用户的问题
	 * @return
	 * @throws Exception
	 *//*
	public List<Question> findAnswerarea() throws Exception;
	*//**
	 * 查找大师区  大师分享在大师区的问题
	 * @return
	 * @throws Exception
	 *//*
	public List<Question> findMasterarea() throws Exception;
	*//**
	 * 查找学员区 大师分享在学院区的问题
	 * @return
	 * @throws Exception
	 *//*
	public List<Question> findStudentrarea() throws Exception;*/
	/**
	 * 通过前台的查询条件查找数据
	 * 按照以下三种方式之一或组合显示问题：
	 * 按问题板块查看、按问题分类查看、按时间远近查看（今天、三天、七天、一个月）。
	 * 另外，也可按提问者、是否回复（未回复、已回复）来显示问题
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public List<Question> findByDynamicData(Question question) throws Exception;
}
