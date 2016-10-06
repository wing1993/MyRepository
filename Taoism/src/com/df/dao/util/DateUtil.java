package com.df.dao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public String changeToDate(String str) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();  
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		if("".equals(str)){
			return null;
			/*cal = new GregorianCalendar(1970,01,01);
			System.out.println("5555555555555555555555555555555555555555"+sf.format(cal.getTime()));*/
		}
		else if("今天".equals(str)){
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		else if("最近三天".equals(str)){
			cal.add(Calendar.DAY_OF_MONTH, -3);
		}
		else if("最近七天".equals(str)){
			cal.add(Calendar.DAY_OF_MONTH, -7);
		}
		else if("最近一个月".equals(str)){
			cal.add(Calendar.MONTH, -1);
		}
		System.out.println(sf.format(cal.getTime())+" 23:59:59");
    	return sf.format(cal.getTime())+" 23:59:59";
	}
	
	public static Date stringToDate(String str) throws ParseException {
		String string = str.substring(0, 10);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(string);
		return date;
	}

	public static String getDateyMdHms(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getDateyMdHms() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getDate() {
		Date date = new Date();
		return getDate(date);
	}

	public static String getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		String result = "";
		switch (week) {
		case 1:
			result = result + "星期日";
			break;
		case 2:
			result = result + "星期一";
			break;
		case 3:
			result = result + "星期二";
			break;
		case 4:
			result = result + "星期三";
			break;
		case 5:
			result = result + "星期四";
			break;
		case 6:
			result = result + "星期五";
			break;
		case 7:
			result = result + "星期六";
			break;
		}
		return result;
	}

	public static String getDateAndWeek() {
		String date = getDate();
		String week = getWeek(new Date());
		String temp[] = date.split("-");

		String result = temp[0] + "年" + temp[1] + "月" + temp[2] + "日 " + week;

		return result;
	}

	
}
