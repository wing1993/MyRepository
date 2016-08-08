package com.df.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.df.dao.factory.DaoFactory;
import com.df.dao.pojo.User;
import com.df.service.factory.ServiceFactory;
import com.df.service.iservice.IUserService;

public class ServiceUserImplTest {

	IUserService ius = null;
	
	@Before
	public void setUp() throws Exception {
		ius = ServiceFactory.getUserServiceInstance();
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
		System.out.println(ius.findAll().toString());
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
