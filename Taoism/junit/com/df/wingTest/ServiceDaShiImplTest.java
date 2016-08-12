package com.df.wingTest;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.df.dao.pojo.User;
import com.df.service.iservice.IDaShiService;

public class ServiceDaShiImplTest {
	private IDaShiService idashi;
	{
		idashi=(IDaShiService) new ClassPathXmlApplicationContext(
				"applicationContext.xml").getBean("dashiService");
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
	public void testFindDaShiLoc() {
		List<String> strs=idashi.findDaShiLoc();
		System.out.println(strs.toString());
	}

	@Test
	public void testFindDaShiByLoc() {
		System.out.println(idashi.findDaShiByLoc("汕头市"));
	}

}
