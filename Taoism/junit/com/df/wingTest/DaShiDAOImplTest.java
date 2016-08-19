package com.df.wingTest;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.df.dao.idao.IDaShiDAO;
import com.df.dao.pojo.User;

public class DaShiDAOImplTest {
	private IDaShiDAO ids;
	{
		ids = (IDaShiDAO) new ClassPathXmlApplicationContext(
				"applicationContext.xml").getBean("dashiDao");
	}

	@Test
	public void testGetSession() {
		fail("Not yet implemented");
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
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllIntegerInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindDaShiLoc() throws Exception {

		List<String> strs = ids.findDaShiLoc();
		/* System.out.println(strs.toString()); */
		for (String str : strs) {
			System.out.println(str.toString());
		}
	}

	@Test
	public void testFindDaShiByLoc() throws Exception {

	}

}
