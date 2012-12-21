package com.hilatest.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalendarTestCase {

	private static Log logger = LogFactory.getLog(CalendarTestCase.class);
	public final static List<Integer> weekdays = new LinkedList<Integer>();
	
	private static final SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" ); 
	@Before
	public void initial(){
		weekdays.add(Calendar.SUNDAY);
		weekdays.add(Calendar.MONTH);
		weekdays.add(Calendar.TUESDAY);
		weekdays.add(Calendar.WEDNESDAY);
		weekdays.add(Calendar.THURSDAY);
		weekdays.add(Calendar.FRIDAY);
		weekdays.add(Calendar.SATURDAY);
	}
	
	@Test
	public void testGetweekday(){
		Calendar c = Calendar.getInstance();
		
		Assert.assertTrue(weekdays.contains(c.get(Calendar.DAY_OF_WEEK)));
	}
	
	@Test
	public void setDay(){
		Calendar c = Calendar.getInstance();
		logger.info("time before set:"+sdf.format(c.getTime()));
		
		//set to Monday of this week
 		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
 		logger.info("set to monday of this week:"+sdf.format(c.getTime()));
	}
	
	/**
	 * 设置sunday的话，是上周日
	 * time before set:2011/05/19 14:04:06
	 * set to Sunday of this week:2011/05/15 14:04:06
	 */
	@Test
	public void setSunday(){
		Calendar c = Calendar.getInstance();
		logger.info("time before set:"+sdf.format(c.getTime()));
		
		//set to Monday of this week
 		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
 		logger.info("set to Sunday of this week:"+sdf.format(c.getTime()));
	}
	
	
	public Calendar getEndofMonth(){
		final Calendar c = Calendar.getInstance();
		
		int month = c.get(Calendar.MONTH);
		
		switch (month){
		case Calendar.JANUARY:
		case Calendar.MARCH:
		case Calendar.MAY:
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.OCTOBER:
		case Calendar.DECEMBER:{
			c.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		case Calendar.APRIL:
		case Calendar.JUNE:
		case Calendar.SEPTEMBER:
		case Calendar.NOVEMBER:{
			c.set(Calendar.DAY_OF_MONTH, 30);
			break;
		}
		case Calendar.FEBRUARY:
			int year = c.get(Calendar.YEAR);
			if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
				c.set(Calendar.DAY_OF_MONTH, 29);
			}else{
				c.set(Calendar.DAY_OF_MONTH, 28);
			}
			break;
		}
		
		return c;
		
	}
}
