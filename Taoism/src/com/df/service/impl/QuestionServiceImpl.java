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
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.service.iservice.IQuestionService;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	@Qualifier("questionDao")
	private IQuestionDAO questionDao;
	
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
	
	@Transactional
	@Override
	public String save(Question t) {
		String msg = "error";
		try {
			questionDao.save(t);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String delete(Question t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Question newObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> findAll() {
		List<Question> ql = null;
		try {
			ql = questionDao.findAll();
			System.out.println(ql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ql;
	}

	@Override
	public Question getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		QueryResult query = null;
		try {
			query = questionDao.findAll(k1, k2);
			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}


	@Transactional
	@Override
	public DataPage<Question> findByDynamicData(Question t,int i,String s) {
		DataPage<Question> dp = new DataPage<Question>();
		try{
			dp = questionDao.findByDynamicData(t,i,s);
			System.out.println("service层"+dp.gettList());
		}catch(Exception e){
			e.printStackTrace();
		}
		return dp;
	}
	@Transactional
	@Override
	public List<Object> findByQid(Question question) {
		List<Object> replyList = new ArrayList<Object> ();
		try {
			questionDao.addReadTimes(question.getQId());
			if("公开区".equals(question.getSharezone())){
				replyList = publicReplyDao.findByQid(question.getQId());
			}
			else if("学员区".equals(question.getSharezone())){
				replyList = studentReplyDao.findByQid(question.getQId());
			}
			else if("弟子区".equals(question.getSharezone())){
				replyList = discipleReplyDao.findByQid(question.getQId());
			}
			else if("我的问题".equals(question.getSharezone())){
				replyList = myquestionReplyDao.findByQid(question.getQId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyList;
	}
}
