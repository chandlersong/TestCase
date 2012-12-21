package com.hilatest.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class DateFormateTest {
	
	 @Test
	 public void testTime() throws ParseException{
		 String example ="18:00:00";
		 
		 SimpleDateFormat sdf  =   new  SimpleDateFormat( "HH:mm:ss" );  
		 
		 Date result = sdf.parse(example);
		 
		 
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testCompareTime() throws ParseException{
         String example ="18:00:00";
		 
         Calendar now = Calendar.getInstance();
         
         Calendar time = Calendar.getInstance();
         
         time.set(now.get(Calendar.YEAR),
        		  now.get(Calendar.MONTH), 
        		  now.get(Calendar.DATE), 
        		  Integer.valueOf(example.substring(0, 2)), 
        		  Integer.valueOf(example.substring(3, 4)), 
        		  Integer.valueOf(example.substring(6, 7))); 
         
         System.out.println("time:"+time.getTime());
         System.out.println("after 18:00:"+ (time.before(now)));
         
	 }
	 
	 @Test
	 public void testDateUtilsgetgetFragmentInSeconds() throws ParseException{
		 String example ="18:00:00";		 
		 SimpleDateFormat sdf  =   new  SimpleDateFormat( "HH:mm:ss" );   
		 Date result = sdf.parse(example);
		 
		 
		 long time = DateUtils.getFragmentInSeconds(result, Calendar.DATE);
		 
		 System.out.println("time"+time);
		 
         time = DateUtils.getFragmentInSeconds(new Date(), Calendar.DATE);
		 
		 System.out.println("now:"+time);
	 }
	 
	 @Test
	 public void testDate() throws ParseException{
		 String example ="1980/09/09";		 
		 SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy/MM/dd" );   
		 Date result = sdf.parse(example);
		 
		
		 System.out.println("time"+result);
		 
	 }
	 
	 @Test
	 public void getEndofMonth(){
		 System.out.println(DateUtils.addDays(
				              DateUtils.ceiling(new Date(), Calendar.MONTH)
				              ,-1
				              ));
	 }

	 @Test(expected=ParseException.class)
	 public void testDateimpossble() throws ParseException{
		 String example ="1980/13/13";		 
		 SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy/MM/dd" ); 
		 sdf.setLenient(false);	
		 Date result = sdf.parse(example);
		 
		 System.out.println("time"+result);
		 
	 }
	 
	 @Test
	 public void  isVaildDate(){
		 
		   String example ="1980/13/13";	
			String[] parttens = new String[]{
					 "yyyy/MM/dd", 			   
				      "MM-dd-yy"
				   };
			Date result = null;
			try{
				result = DateUtils.parseDate(example, parttens);
				
			}catch(ParseException e){
			}
			
			System.out.println(result);
		}
	 
	 @Test
	 public void testbefore(){
		 Date date1 = DateUtils.truncate(new Date(), Calendar.DATE);
		 Date date2 = DateUtils.truncate(new Date(), Calendar.DATE);
		 System.out.println("date1:"+date1);
		 System.out.println("date2:"+date2);
		 System.out.println(date1.before(date2));
	 }
	 
	 @Test
	 public void testprace_1() throws ParseException{
	     String example = "Feb 4, 0:00PM CST";
	     String example_1 = "Feb 4, 1:39PM GMT";
	     
		 Date date = new Date();
		 String format = "MMM dd, hh:mma zz";
		 SimpleDateFormat f = new SimpleDateFormat(format,Locale.US);
		 System.out.println(f.format(date));
		 System.out.println(f.parse(example));
		 System.out.println(f.parse(example_1));
	 }
}
