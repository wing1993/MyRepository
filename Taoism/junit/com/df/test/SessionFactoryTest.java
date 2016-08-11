package com.df.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.df.dao.idao.IUserDAO;

@Component
public class SessionFactoryTest {

		private IUserDAO iud;
	@Test
	public void testgetSession() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) ctx
				.getBean("sessionFactory");
		{
			iud = (IUserDAO) new ClassPathXmlApplicationContext("applicationContext.xml")
					.getBean("userDao");
		}
		System.out.println(iud.toString());
	}

}
