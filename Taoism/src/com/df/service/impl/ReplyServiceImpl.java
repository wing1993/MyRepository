package com.df.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.df.dao.idao.IDiscipleReplyDAO;
import com.df.dao.idao.IMyquestionReplyDAO;
import com.df.dao.idao.IPublicReplyDAO;
import com.df.dao.idao.IStudentReplyDAO;
import com.df.service.iservice.IReplyService;

@Service("replyService")
public class ReplyServiceImpl implements IReplyService<Object> {

	@Autowired
	@Qualifier("publicReplyDao")
	private IPublicReplyDAO publicReplyDao;
	
	@Autowired
	@Qualifier("discipleReplyDao")
	private IDiscipleReplyDAO discipleReplyDao;
	
	@Autowired
	@Qualifier("studentReplyDao")
	private IStudentReplyDAO studentReplyDao;
	
	@Autowired
	@Qualifier("myquestionReplyDao")
	private IMyquestionReplyDAO myquestionReplyDao;

	@Override
	public List<Object> findByQid(Integer qId,String sharezone) {
		List<Object> replyList = new ArrayList<Object> ();
		try {
			if("公开区".equals(sharezone)){
				replyList = publicReplyDao.findByQid(qId);
			}
			else if("学员区".equals(sharezone)){
				replyList = studentReplyDao.findByQid(qId);
			}
			else if("弟子区".equals(sharezone)){
				replyList = discipleReplyDao.findByQid(qId);
			}
			else if("我的问题".equals(sharezone)){
				replyList = myquestionReplyDao.findByQid(qId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyList;
	}

	@Override
	public String save(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
