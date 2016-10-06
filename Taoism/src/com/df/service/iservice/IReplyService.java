package com.df.service.iservice;

import java.util.List;




public interface IReplyService<T>{
	/**
	 * 通过点击贴的标题查找出关于该标题下的所有回复
	 * @param i   贴或问题的id
	 * @param s   贴或问题的区域
	 * @return
	 */
	public List<T> findByQid(Integer i,String s);
	
	/**
	 * 保存回复
	 * @param t  使用了分表 不同的区域不同的类
	 * @return
	 */
	public String saveReply(Object t,String sharezone);
	/**
	 * 删除回复
	 * @param t
	 * @return
	 */
	public String delete(T t,String sharezone);
}