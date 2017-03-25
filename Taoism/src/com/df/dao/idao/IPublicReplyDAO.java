package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.PublicReply;

public interface IPublicReplyDAO extends
		IBaseHibernateDAO<PublicReply, Integer> {

	/**
	 * 根据帖子id  获取该帖子的评论
	 * @param k1
	 * @return
	 * @throws Exception
	 */
	public List<Object> findByQid(Integer k1) throws Exception;

	/**
	 * 屏蔽帖子评论
	 * @param replyId
	 * @throws Exception 
	 */
	public void shieldReply(int replyId, String con1) throws Exception;
}
