package com.df.action.question;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.ClientPage;
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Page;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
import com.df.dao.util.DateUtil;
import com.df.service.iservice.IQuestionService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("questionAction")
@Scope("prototype")
public class QuestionAction implements Serializable, ModelDriven<Question>,RequestAware{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("questionService")
	private IQuestionService questionService;
	
	HttpServletResponse response = ServletActionContext.getResponse(); 
	private Question question;
	//private QueryCriteria queryCriteria;
	private Map<String, Object> requestMap;
	private int sumPage;     //总页数
	private int currentPage; //当前页
	private DataPage<Question> dp;
	private List<Question> qList;   //保存获取的问题
	private List<ClientPage> cList; //保存要显示的页码
	private Page page;              //分页的情况（每页显示的数量，总页数，当前页，是否有上一页下一页等）
	private List<Object> replyList; //用户的针对某个问题的回复
	

	private User u = (User) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	
	
	
	public List<ClientPage> getcList() {
		return cList;
	}

	public void setcList(List<ClientPage> cList) {
		this.cList = cList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Question> getqList() {
		return qList;
	}

	public void setqList(List<Question> qList) {
		this.qList = qList;
	}

/*	public DataPage<Question> getDp() {
		return dp;
	}

	public void setDp(DataPage<Question> dp) {
		this.dp = dp;
	}*/

	/**
	 * 用户发帖子
	 * @return
	 */
	public String save() {
		String msg = "error";
		question.setCon1("");
		question.setCon2("");
		question.setCon3("0");
		question.setState(0);
		question.setShareState(1);
		question.setVisits(0);
		System.out.println("save:"+question);
		try {
			msg = questionService.save(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 逻辑删除  删除标志con6置1
	 * @return
	 */
	public String delete() {
		String msg = "error";
		try {
			question = questionService.getById(question.getQId());
			question.setCon6("1");
			questionService.update(question);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String update() {
		String msg = "error";
		try {
			msg = questionService.update(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}	
	
	/**
	 * 帖子置顶
	 * @return
	 */
	public String setTop() {
		String msg = "error";
		try {
			question = questionService.getById(question.getQId());
			question.setCon4("1");
			questionService.update(question);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}	
	
	/**
	 * 设置精华贴
	 * @return
	 */
	public String setNice() {
		String msg = "error";
		try {
			question = questionService.getById(question.getQId());
			question.setCon5("1");
			questionService.update(question);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}	
	
	/**
	 * 用户向大师提问，大师可以向除了自己的大师提问
	 * @return
	 */
	public String AskDashi() {
		question.setCon1("");
		question.setCon2("");
		question.setCon3("0");
		question.setState(0);
		question.setShareState(1);
		question.setVisits(0);
		question.setSharezone("我的问题");
		System.out.println("AskDashi:"+question);
		String msg = "error";
		try {
			msg = questionService.save(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 大师可以把对他提问的问题转发到其他区
	 */
	public String daShiForwardPost(){
		String msg = "error";
		try {
			questionService.daShiForwardPost(question);
			msg = "success";
			PrintWriter out = response.getWriter();
			out.print(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 通过问题的id查找该问题的所有回复
	 * @return
	 */
	public String findReplyByQId(){
		System.out.println("123456789"+question);
		try {
			replyList = questionService.findByQid(question);
		
			question = questionService.getById(question.getQId());
			requestMap.put("replysfromAction", replyList);
			requestMap.put("questionfromAction", question);
			System.out.println("question"+question);
			System.out.println("123456789"+replyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "replys";
	}
	
	/**
	 * 通过问题区域，问题类型，是否回复，按时间查找问题
	 */
	public String findByDynamicData(){
		String userType = null;
		if(null!=u) {userType = u.getUserType();
			question.setAskWho(u.getUsername());
			question.setUsername(u.getUsername());}
		String msg = "error";
		try {
			System.out.println(question+"wwwwwwwwwwwwwwwwww");
			question.setQTime(new DateUtil().changeToDate(question.getQTime()));
			dp = questionService.findByDynamicData(question,currentPage,userType);
			if (dp.gettList() != null && dp.gettList().size() > 0) {
				/*System.out.println(msg);//requestMap.put("questionsFromAction", questionList);
				dp = this.paging(questionList);*/
				qList = dp.gettList();
				cList = dp.getcList();
				System.out.println("----"+currentPage);
				page = dp.getPage();
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(msg);
		return msg;
	}
	
	
	
	
	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getSumPage() {
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	@Override
	public Question getModel() {
		question = new Question();
		return question;
	}

	public List<Object> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Object> replyList) {
		this.replyList = replyList;
	}

}
