package com.df.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.df.dao.pojo.Question;
import com.df.service.iservice.IQuestionService;
import com.df.service.iservice.IUserService;

public class QuestionServiceImplTest {

	private IQuestionService iqs;
	{
		iqs = (IQuestionService) new ClassPathXmlApplicationContext(
				"applicationContext.xml").getBean("questionService");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSave() {
		Question q = new Question("root","2016-04-05 18:00:00","求法","也热","微微一笑很倾城","今晚开播了",0,"公开区",1);
		for(int i=0;i<=20;i++)
		iqs.save(q);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllIntegerInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectSumCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByDynamicData() {
		Question question = new Question();
		question.setQTypeName("问事");
		question.setSharezone("公开区");
		System.out.println("--------"+question);
		List<Question> questionList;
		try {
			questionList = iqs.findByDynamicData(question);
			System.out.println("------------"+questionList.size());
			if (questionList != null && questionList.size() > 0) {
				for(Question q:questionList){
					System.out.println(q.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
