package com.df.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
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
	
	@Autowired
	@Qualifier("userDao")
	private IUserDAO userDao;
	
	@Transactional
	@Override
	public String save(Question t) throws Exception{
		String msg = "error";
		questionDao.save(t);
		User user = new User();System.out.println(t);
		user.setUsername(t.getUsername());
		user = userDao.getByUsername(user);
		System.out.println(user);
		if(null!=user.getCon5()&&"".equals(user.getCon5())){
			user.setCon5(String.valueOf(Double.parseDouble(user.getCon5())+1));
		}else{
			user.setCon5("1");
		}
		userDao.update(user);
		msg = "success";
		return msg;
	}

	@Override
	public String delete(Question t) {
		
		return null;
	}

	@Transactional
	@Override
	public String update(Question newObj) throws Exception{
		questionDao.update(newObj);
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

	@Transactional
	@Override
	public Question getById(Integer k){
		Question question = new Question();
		try {
			question = questionDao.getById(k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	@Transactional
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
	public DataPage<Question> findByQTime(Map<String, Object> map) {
		DataPage<Question> dp = new DataPage<Question>();
		try{
			dp = questionDao.findByQTime(map);
			System.out.println("service层"+dp.gettList());
		}catch(Exception e){
			e.printStackTrace();
		}
		return dp;
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
	/**
	 * 考虑到转帖的原因  （公开区||学员区||弟子区）且ask_who为空才在对应的区找   剩下的都在我的问题中查找
	 */
	@Transactional
	@Override
	public List<Object> findByQid(Question question) {
		List<Object> replyList = new ArrayList<Object> ();
		try {
			questionDao.addReadTimes(question.getQId());
			if("公开区".equals(question.getSharezone())&&(null==question.getAskWho()||"".equals(question.getAskWho()))){
				replyList = publicReplyDao.findByQid(question.getQId());
			}
			else if("学员区".equals(question.getSharezone())&&(null==question.getAskWho()||"".equals(question.getAskWho()))){
				replyList = studentReplyDao.findByQid(question.getQId());
			}
			else if("弟子区".equals(question.getSharezone())&&(null==question.getAskWho()||"".equals(question.getAskWho()))){
				replyList = discipleReplyDao.findByQid(question.getQId());
			}
			else {
				replyList = myquestionReplyDao.findByQid(question.getQId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyList;
	}
	@Transactional
	@Override
	public void updateLastReplyData(String username, String lastReplyTime,Integer qId)
			throws Exception {
		questionDao.updateLastReplyData(username, lastReplyTime, qId);
		
	}
	@Transactional
	@Override
	public void daShiForwardPost(Question question) throws Exception {
		questionDao.updateSharezone(question);
		
	}

	@Transactional
	@Override
	public int queryCountQuestioninfo(Map<String, Object> map) throws Exception {
		return questionDao.queryCountQuestioninfo(map);
	}

	@Transactional
	@Override
	public List<Object[]> queryListQuestioninfo(Map<String, Object> map) throws Exception {
		return questionDao.queryListQuestioninfo(map);
	}

	@Transactional
	@Override
	public DataPage<Question> findMyPosts(Map<String,Object> map) throws Exception {
		return questionDao.findMyPosts(map);
	}

}
