package com.df.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IDiscipleReplyDAO;
import com.df.dao.idao.IMyquestionReplyDAO;
import com.df.dao.idao.IPublicReplyDAO;
import com.df.dao.idao.IQuestionDAO;
import com.df.dao.idao.IStudentReplyDAO;
import com.df.dao.pojo.DiscipleReply;
import com.df.dao.pojo.MyquestionReply;
import com.df.dao.pojo.PublicReply;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.StudentReply;
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
	
	@Autowired
	@Qualifier("questionDao")
	private IQuestionDAO questionDao;

	@Transactional
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

	@Transactional
	@Override
	public String saveReply(Object t,String sharezone) {
		String msg = "error";
		try{
			if("公开区".equals(sharezone)){
				PublicReply pr = (PublicReply)t;
				publicReplyDao.save(pr);
				msg = "success";
				System.out.println("1"+pr);
			}
			else if("学员区".equals(sharezone)){
				StudentReply sr = (StudentReply)t;
				studentReplyDao.save(sr);
				msg = "success";
				System.out.println("2"+sr);
			}
			else if("弟子区".equals(sharezone)){
				DiscipleReply dr = (DiscipleReply)t;
				discipleReplyDao.save(dr);
				msg = "success";
				System.out.println("3"+dr);
			}
			else if("我的问题".equals(sharezone)){
				MyquestionReply mr = (MyquestionReply)t;
				myquestionReplyDao.save(mr);
				msg = "success";  
				System.out.println("4"+mr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String delete(Object t,String sharezone) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
