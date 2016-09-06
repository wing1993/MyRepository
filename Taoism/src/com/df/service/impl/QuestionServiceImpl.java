package com.df.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
import com.df.service.iservice.IQuestionService;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	@Qualifier("questionDao")
	private IQuestionDAO questionDao;

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
		// TODO Auto-generated method stub
		return null;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	@Override
	public int selectSumCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	@Override
	public List<Question> findByDynamicData(Question t,String s) throws Exception {
		List<Question> questionList = new ArrayList<Question>();
		try{
			questionList = questionDao.findByDynamicData(t,s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return questionList;
	}

}
