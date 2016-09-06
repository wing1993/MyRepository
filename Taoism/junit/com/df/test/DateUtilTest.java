package com.df.test;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.df.dao.util.DateUtil;

public class DateUtilTest {

	
	DateUtil du = new DateUtil();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testChangeToDate() throws ParseException {
		String str;
		str = du.changeToDate("今天");
		System.out.println(str);
		str = du.changeToDate("最近三天");
		System.out.println(str);
		str = du.changeToDate("最近七天");
		System.out.println(str);
		str = du.changeToDate("最近一个月");
		System.out.println(str);
	}

}
