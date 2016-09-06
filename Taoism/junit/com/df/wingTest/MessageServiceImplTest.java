package com.df.wingTest;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.df.dao.pojo.Message;
import com.df.service.iservice.IMessageService;

public class MessageServiceImplTest {
	private IMessageService ims;
	{
		ims=(IMessageService) new ClassPathXmlApplicationContext("applicationContext.xml").getBean("messageService");
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
	public void testFindLatest() throws Exception {
		List<Message> latest=ims.findLatest();
		for(Message me:latest){
			System.out.println(me.toString());
		}
	}

}
