package com.df.wingTest;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.df.dao.pojo.QuestionType;
import com.df.service.iservice.IQuestionTypeService;

public class QuestionTypeServiceImplTest {
	private IQuestionTypeService iqt;
	{
		iqt = (IQuestionTypeService) new ClassPathXmlApplicationContext(
				"applicationContext.xml").getBean("questionTypeService");
	}
	@Test
	public void testSave() {
		fail("Not yet implemented");
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
		List<QuestionType> qtype=iqt.findAll();
		System.out.println(qtype.toString());
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
