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


public class ServiceUserImplTest {
	

	private IUserService ius;
	{
		ius = (IUserService) new ClassPathXmlApplicationContext("applicationContext.xml")
				.getBean("userService");
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testSave() {
		User user = new User("r","001","学员","女","广东汕头","18318743492","849306235@qq.com");
		user.setUserId(4);
		System.out.println(ius.save(user));
		user.setUserId(100);
		System.out.println(ius.save(user));
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
		IUserService ius;
		{
			ius = (IUserService) new ClassPathXmlApplicationContext("applicationContext.xml")
					.getBean("userService");
		}
		System.out.println("2222222222222");
		List<User> airs;
		try {
			airs = ius.findAll();
			for (User air : airs) {
				System.out.println(air.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		User user = new User("root","123");
		System.out.println(ius.login(user));
	}

	@Test
	public void testRegistry() {
		User user = new User("r","001","学员","男","广东汕头","18318743492","849306235@qq.com");
		System.out.println(ius.registry(user));
	}

	@Test
	public void testExamine() throws Exception {
		System.out.println(ius.examine(DaoFactory.getUserDAOInstance().getById(1)));
	}

}
