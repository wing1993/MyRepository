package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.DiscipleReply;

public interface IDiscipleReplyDAO extends IBaseHibernateDAO<DiscipleReply, Integer> {
	public List<Object> findByQid(Integer k1) throws Exception ;
	/**
	 * 屏蔽帖子评论
	 * @param replyId
	 * @throws Exception 
	 */
	public void shieldReply(int replyId, String con1) throws Exception;
	/**
	 * 保存评论
	 * @param dr
	 */
	public int saveReply(DiscipleReply dr)throws Exception;
}
