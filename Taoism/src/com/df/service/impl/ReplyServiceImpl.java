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
import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.DiscipleReply;
import com.df.dao.pojo.MyquestionReply;
import com.df.dao.pojo.PublicReply;
import com.df.dao.pojo.StudentReply;
import com.df.dao.pojo.User;
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
	
	@Autowired
	@Qualifier("userDao")
	private IUserDAO userDao;

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
	public int saveReply(Object t,String sharezone) {
		int replyId = 0;
		User user = new User();
		try{
			if("公开区".equals(sharezone)){
				PublicReply pr = (PublicReply)t;
				user.setUsername(pr.getRespondent());
				replyId = publicReplyDao.saveReply(pr);
				System.out.println("1"+pr);
			}
			else if("学员区".equals(sharezone)){
				StudentReply sr = (StudentReply)t;
				user.setUsername(sr.getRespondent());
				replyId = studentReplyDao.saveReply(sr);
				System.out.println("2"+sr);
			}
			else if("弟子区".equals(sharezone)){
				DiscipleReply dr = (DiscipleReply)t;
				user.setUsername(dr.getRespondent());
				replyId = discipleReplyDao.saveReply(dr);
				System.out.println("3"+dr);
			}
			else if("我的问题".equals(sharezone)){
				MyquestionReply mr = (MyquestionReply)t;
				user.setUsername(mr.getRespondent());
				replyId = myquestionReplyDao.saveReply(mr);
				System.out.println("4"+mr);
			}
			/*用户评论之后评论次数+1*/
			System.out.println(user);
			user = userDao.getByUsername(user);
			System.out.println(user);
			if(null!=user.getCon6()&&!"".equals(user.getCon6())){
				
				user.setCon6(String.valueOf(Integer.parseInt(user.getCon6())+1));
				System.out.println(user);
			}else{
				user.setCon6("1");
			}
			userDao.update(user);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return replyId;
	}

	@Override
	public String delete(Object t,String sharezone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public String shieldReply(int replyId, String sharezone, String con1)throws Exception {
		String msg = "error";
		try{
			if("公开区".equals(sharezone)){
				publicReplyDao.shieldReply(replyId,con1);
				msg = "success";
			}
			else if("学员区".equals(sharezone)){
				studentReplyDao.shieldReply(replyId,con1);
				msg = "success";
			}
			else if("弟子区".equals(sharezone)){
				discipleReplyDao.shieldReply(replyId,con1);
				msg = "success";
			}
			else if("我的问题".equals(sharezone)){
				myquestionReplyDao.shieldReply(replyId,con1);
				msg = "success";  
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return msg;
	}

	
}
