package com.df.action.question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Page;
import com.df.dao.pojo.Question;
import com.df.dao.pojo.User;
import com.df.dao.util.ClientPage;
import com.df.dao.util.DateUtil;
import com.df.dao.util.PageUtil;
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
	@SuppressWarnings("unchecked")
	private List<User> u = (List<User>) ServletActionContext.getRequest()
			.getSession().getAttribute("UsersfromActions");
	private Question question;
	private Map<String, Object> requestMap;
	private int sumPage;     //总页数
	private int currentPage; //当前页
	private DataPage<Question> dp;
	private List<Question> qList;
	private List<ClientPage> cList;
	private Page page;
	
	
	
	
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

	public String save() {
		return questionService.save(question);
	}

	public String delete() {
		return questionService.delete(question);
	}

	public String update() {
		return questionService.update(question);
	}	
	
	public String AskDashi() {
		String msg = questionService.save(question);
		return msg;
	}

	public String findByDynamicData(){
		String userType = null;
		if(null!=u) {userType = u.get(0).getUserType();}
		String msg = "error";
		List<Question> questionList = new ArrayList<Question>();
		try {
			question.setQTime(new DateUtil().changeToDate(question.getQTime()));
			questionList = questionService.findByDynamicData(question,userType);
			if (questionList != null && questionList.size() > 0) {
				System.out.println(msg);//requestMap.put("questionsFromAction", questionList);
				dp = this.paging(questionList);
				qList = dp.gettList();
				cList = dp.getcList();
				page = dp.getPage();
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DataPage<Question> paging(List<Question> questionList) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(sumPage != 0) {
			if(currentPage > sumPage)  //判断输入的页数是否大于总页数
				currentPage = sumPage;
			else if(currentPage < 0)
				currentPage = 1;
		}
		Page page = PageUtil.createPage(1, questionList.size(), currentPage);
		int endPage = page.getBeginIndex() + page.getEveryPage();
		if (page.getBeginIndex() + page.getEveryPage() > page.getTotalCount()) {
			endPage = page.getTotalCount();
		}
		questionList = questionList.subList(page.getBeginIndex(), endPage);
		request.setAttribute("page", page);
		
		//前台页码显示
		int sum = page.getTotalPage();
		int current = page.getCurrentPage();
		int index;
		List<ClientPage> pageList = PageUtil.getClientPage(current,sum);
		System.out.println("获取的记录数："+questionList.size());
		if(pageList.size()!=0){
			if(sum<=10){
				index=1;
			}else if(sum>10 && current<=5){
				index=1;
			}else{
				index=current-4;
			}
					
			pageList.get(page.getCurrentPage()-index).setPage(0);
			
			request.setAttribute("pageList", pageList);
		}
		DataPage dp = new DataPage(questionList,pageList,page);
		request.setAttribute("questionsFromAction", questionList);
		return dp;
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

}
