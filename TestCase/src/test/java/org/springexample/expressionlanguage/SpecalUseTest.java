package org.springexample.expressionlanguage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpecalUseTest extends SpringTestBasic {

	private List<ExpressionBean> beanlist;
	
	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("configureexpression.xml");
		return filelist;
	}

	@Before
	public void initial(){
		beanlist = new ArrayList<ExpressionBean>();
		
		ExpressionBean bean;
		for(int i=0;i<10;i++){
			
			bean = new ExpressionBean(String.valueOf(i),i);
			
	
			
			beanlist.add(bean);
			
			
		}
	}
	
	@Test
	public void testFilter1(){
		
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(beanlist);
		
		@SuppressWarnings("unchecked")
		List<ExpressionBean> beans = (List<ExpressionBean>) 
		                  parser.parseExpression("?[desc == '1']").getValue(context);
		
		System.out.println("size:"+beans.size());	
		
		this.printall(beans);
	}
	
	@Test
	public void testFilter2(){
		
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(beanlist);
		
		@SuppressWarnings("unchecked")
		List<ExpressionBean> beans = (List<ExpressionBean>) 
		                  parser.parseExpression("?[number > 3]").getValue(context);
		
		System.out.println("size:"+beans.size());	
		
		this.printall(beans);
	}
	
	@Test
	public void testFilter3(){
		
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(beanlist);
		
		@SuppressWarnings("unchecked")
		List<Integer> beans = (List<Integer>) 
		                  parser.parseExpression("![number]").getValue(context);
		
		System.out.println("size:"+beans.size());	
		
		for(Integer filter: beans){
			System.out.println(filter);
		}
	}
	
	/**
	 * #{}只估算这个里面的
	 */
	@Test
	public void testGetValue(){
		ExpressionParser parser = new SpelExpressionParser();
		
		String randomPhrase =
			parser.parseExpression("random number is #{T(java.lang.Math).random()}",
			new TemplateParserContext()).getValue(String.class);
		
		System.out.println(randomPhrase);
	}
	@Test
	public void testGetById(){
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(beanlist);
		
		@SuppressWarnings("unchecked")
		List<Integer> beans = (List<Integer>) parser.parseExpression("{1,2,3,4}").getValue(context);
		
		System.out.println("size:"+beans.size());	
		
	}
	
	private void printall(List<ExpressionBean> printed){
		
		for(ExpressionBean bean:printed){
			System.out.println("desc:"+bean.getDesc());
			System.out.println("number:"+bean.getNumber());
		}
		
	}
}
