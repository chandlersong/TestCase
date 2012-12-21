package org.springexample.expressionlanguage;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TestExpressionParser extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		return filelist;
	}
	
	@Test
	public void testBasic(){
		
		String example = "Hello World";
		
		ExpressionParser parser = new SpelExpressionParser();
		//这里要加单引号。因为这里是表达式。
		Expression exp =parser.parseExpression("'Hello World'");
		
		try {
			String message = (String) exp.getValue();
			String message2 =  exp.getValue(String.class);
			System.out.println("message1:"+message);
			System.out.println("message2:"+message2);
			Assert.assertEquals(example, message);
			Assert.assertEquals(example, message2);
		} catch (EvaluationException e) {
			/*
			 * get Value will throw EvaluationException. 
			 */
		}
	}
	
	@Test
	public void testFunction(){
		
		String example = "Hello World";
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp =parser.parseExpression("'hello world'.toUpperCase()");
			
		String message = (String) exp.getValue();
		System.out.println(message);
		Assert.assertEquals(example.toUpperCase(), message);
	
	}
	
	@Test(expected=EvaluationException.class)
	public void testVarible(){
		
		String example = "Hello World";
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp =parser.parseExpression("example.toUpperCase()");
			
		String message = (String) exp.getValue();
		System.out.println(message);
		Assert.assertEquals(example.toUpperCase(), message);
	
		Expression experror =parser.parseExpression("NotExisted.toUpperCase()");
		String messageerror = (String) experror.getValue();
		System.out.println(messageerror);
		Assert.assertEquals(null, messageerror);
	}
	
	@Test
	public void pracePropertyMethod1(){
		// Create and set a calendar
		Calendar c = Calendar.getInstance();
		c.set(1856, 7, 9);
		
		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name");
		EvaluationContext context = new StandardEvaluationContext(tesla);
		String name = (String) exp.getValue(context);
		
		System.out.print(name);
	}
	
	@Test
	public void pracePropertyMethod2(){
		// Create and set a calendar
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);
		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name");
		String name = (String) exp.getValue(tesla);
		
		System.out.print(name);
	}
	
	@Test
	public void pracePropertyMethod3(){
		// Create and set a calendar
		Calendar c = Calendar.getInstance();
		c.set(1856, 7, 9);
		
		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		ExpressionParser parser = new SpelExpressionParser();
	
		EvaluationContext context = new StandardEvaluationContext(tesla);
		
		Expression exp = parser.parseExpression("name == 'Nikola Tesla'");
		boolean result = exp.getValue(context, Boolean.class); // evaluates to true
		
		System.out.print(result);
	}
	
	@Test
	public void testEval(){
		
		// Create and set a calendar
		Calendar c = Calendar.getInstance();
		c.set(1856, 7, 9);
		
		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
	
		EvaluationContext context = new StandardEvaluationContext(tesla);
		
		ExpressionParser parser = new SpelExpressionParser();
		// evals to "Hello World"
		String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
		System.out.println(helloWorld);
		
		double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
		// evals to 2147483647
		System.out.println(avogadrosNumber);
		
		int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
		System.out.println(maxValue);
		
		boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
		System.out.println(trueValue);
		
		Object nullValue = parser.parseExpression("null").getValue();
		System.out.println(nullValue);
		//here will thorw nullPointException
		
		// evals to 1856
		int year = (Integer) parser.parseExpression("Birthdate.Year + 1900").getValue(context);
		System.out.println(year);
		
		Inventor[] a = new Inventor[]{tesla,tesla,tesla};
		EvaluationContext listcontext = new StandardEvaluationContext(a);
		
		// evaluates to "Induction motor"
		String invention = parser.parseExpression("[0].toString()").getValue(listcontext,
		String.class);
		System.out.println(invention);
		
	}
	
	/**
	 * Avoid NullPointerException
	 */
	@Test
	public void praceSafeNavigationOperator(){
		// Create and set a calendar
		Calendar c = Calendar.getInstance();
		c.set(1856, 7, 9);
		
		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		ExpressionParser parser = new SpelExpressionParser();
	
		EvaluationContext context = new StandardEvaluationContext(tesla);
		
		String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
		System.out.println(city); // Smiljan

	}
}
