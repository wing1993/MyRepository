package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.MyquestionReply;

public interface IMyquestionReplyDAO extends IBaseHibernateDAO<MyquestionReply, Integer> {
	public List<Object> findByQid(Integer k1) throws Exception ;
	/**
	 * 屏蔽帖子评论
	 * @param replyId
	 * @throws Exception 
	 */
	public void shieldReply(int replyId, String con1) throws Exception;
	/**
	 * 保存评论
	 * @param mr
	 * @return 
	 * @throws Exception
	 */
	public int saveReply(MyquestionReply mr)throws Exception;
}
