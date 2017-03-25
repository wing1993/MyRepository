package com.df.dao.idao;

import java.util.List;

import com.df.dao.pojo.StudentReply;

public interface IStudentReplyDAO extends IBaseHibernateDAO<StudentReply, Integer> {
	public List<Object> findByQid(Integer k1)throws Exception;
	/**
	 * 屏蔽帖子评论
	 * @param replyId
	 */
	public void shieldReply(int replyId, String con1);
}
