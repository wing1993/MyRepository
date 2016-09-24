package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.df.dao.idao.IDiscipleReplyDAO;
import com.df.dao.idao.IPublicReplyDAO;
import com.df.dao.idao.IStudentReplyDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.Reply;
import com.df.service.iservice.IReplyService;

@Service("replyService")
public class ReplyServiceImpl implements IReplyService {

	/*@Autowired
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
	private IMyquestionDAO myquestionReplyDao;*/
	
	@Override
	public String save(Reply t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Reply t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Reply newObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Reply> findByQid(Question question){
		/*List<Reply> replyList = new ArrayList<Reply> ();
		if("公开区".equals(question.getSharezone())){
			
		}*/
		return null;
	}
}
