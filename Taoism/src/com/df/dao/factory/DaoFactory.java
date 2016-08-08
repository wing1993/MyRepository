package com.df.dao.factory;

import com.df.dao.idao.IMessageDAO;
import com.df.dao.idao.IQuestionDAO;
import com.df.dao.idao.IQuestionTypeDAO;
import com.df.dao.idao.IReplyDAO;
import com.df.dao.idao.IUserDAO;
import com.df.dao.impl.MessageDAOImpl;
import com.df.dao.impl.QuestionDAOImpl;
import com.df.dao.impl.QuestionTypeDAOImpl;
import com.df.dao.impl.ReplyDAOImpl;
import com.df.dao.impl.UserDAOImpl;


public class DaoFactory {
	
	
	public static IMessageDAO getMessageDAOInstance() {
		return new MessageDAOImpl();
	}
	public static IQuestionDAO getQuestionDAOInstance() {
		return new QuestionDAOImpl();
	}
	public static IQuestionTypeDAO getQuestionTypeDAOInstance() {
		return new QuestionTypeDAOImpl();
	}
	public static IReplyDAO getReplyDAOInstance() {
		return new ReplyDAOImpl();
	}
	public static IUserDAO getUserDAOInstance() {
		return new UserDAOImpl();
	}
}
