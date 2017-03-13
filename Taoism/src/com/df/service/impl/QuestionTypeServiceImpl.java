package com.df.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IQuestionDAO;
import com.df.dao.idao.IQuestionTypeDAO;
import com.df.dao.pojo.QueryResult;
import com.df.dao.pojo.QuestionType;
import com.df.service.iservice.IQuestionTypeService;

@Service("questionTypeService")
public class QuestionTypeServiceImpl implements IQuestionTypeService {
	@Autowired
	@Qualifier("questionTypeDao")
	private IQuestionTypeDAO questionTypeDao;
	
	@Transactional
	@Override
	public String save(QuestionType t) throws Exception{
		String msg = "error";
		try {
			questionTypeDao.save(t);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public String delete(QuestionType t) throws Exception{
		String msg = "error";
		try {
			questionTypeDao.delete(t);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	@Transactional
	@Override
	public String update(QuestionType newObj)throws Exception {
		String msg = "error";
		try {
			questionTypeDao.update(newObj);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Transactional
	@Override
	public List<QuestionType> findAll() {
		List<QuestionType> questionType=null;
		try {
			questionType=questionTypeDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionType;
	}

	@Override
	public QuestionType getById(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult findAll(Integer k1, Integer k2) {
		// TODO Auto-generated method stub
		return null;
	}

}
