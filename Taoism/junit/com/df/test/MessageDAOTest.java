package com.df.test;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.df.dao.impl.MessageDAOImpl;
import com.df.dao.pojo.Message;
import com.df.dao.pojo.QueryResult;
import com.df.dao.util.DateUtil;

public class MessageDAOTest {

	private MessageDAOImpl md;

	@Before
	public void setUp() throws Exception {
		md = new MessageDAOImpl();
	}

	@Test
	public void testSave() throws Exception {
		String date = DateUtil.getDateyMdHms(new Date());
		System.out.println(new Date() + "--");
		System.out.println(date + "----");
		Message message = new Message("001", date, "123654789");
		md.save(message);
	}

	@Test
	public void testDelete() {
		Message message = md.getById(1);
		md.delete(message);
	}

	@Test
	public void testFindById() {
		Message message = md.getById(1);
		System.out.println(message);
	}

	@Test
	public void testFindAll() {
		List<Message> ms = md.findAll();
		for (Message message : ms) {
			System.out.println(message.toString());
		}
	}

	@Test
	public void testFindAllIntInt() {
		QueryResult qs = md.findAll(1, 2);
		System.out.println("总记录数：" + qs.getCount());
		for (Message message : (List<Message>) qs.getList()) {
			System.out.println(message.toString());
		}
	}

	@Test
	public void testUpdate() {
		Message message = md.getById(1);
		message.setAuthor("yjh");
		md.update(message);
	}
}
