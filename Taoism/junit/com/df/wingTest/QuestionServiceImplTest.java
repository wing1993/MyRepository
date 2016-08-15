package com.df.wingTest;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.df.dao.pojo.Question;
import com.df.service.iservice.IQuestionService;

public class QuestionServiceImplTest {
	private IQuestionService iqs;
	{
		iqs=(IQuestionService) new ClassPathXmlApplicationContext(
				"applicationContext.xml").getBean("questionService");
	}
	@Test
	public void testSave() {
		Question q=new Question("root","2016-08-14","问事","r","说啥好呢","不知道",0,"0",0);
		System.out.println(iqs.save(q));
		
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

}
