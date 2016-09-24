package com.df.service.iservice;

import java.util.List;

import com.df.dao.pojo.Question;
import com.df.dao.pojo.Reply;


public interface IReplyService extends IBaseService<Reply, Integer>{
	public List<Reply> findByQid(Question question);
}