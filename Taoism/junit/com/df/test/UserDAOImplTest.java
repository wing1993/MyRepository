package com.df.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IUserDAO;
import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;

public class UserDAOImplTest {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("userDao") private IUserDAO iud;
	 */
	private IUserDAO iud;
	{
		iud = (IUserDAO) new ClassPathXmlApplicationContext(
				"applicationContext.xml").getBean("userDao");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Transactional
	public void testSave() {
		User user = new User("r", "001", "学员", "男", "广东汕头", "18318743492",
				"849306235@qq.com");
		try {
			iud.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {

		System.out.println("2222222222222");
		List<User> airs;
		try {
			airs = iud.findAll();
			for (User air : airs) {
				System.out.println(air.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void testFindAllIntegerInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistry() {
		fail("Not yet implemented");
	}

	@Test
	public void testExamine() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindNeedExamine() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeUserType() {
		fail("Not yet implemented");
	}

}
