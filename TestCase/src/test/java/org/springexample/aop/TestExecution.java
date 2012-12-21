package org.springexample.aop;

import java.rmi.AlreadyBoundException;
import java.util.List;

import org.junit.Test;
import org.springexample.SpringTestBasic;
import org.springexample.aop.tagert.TargetBean;
import org.springexample.aop.tagert.execution.ExecutionBean;
import org.springexample.aop.tagert.execution.ExecutionParaBean;

public class TestExecution extends SpringTestBasic{

	@Override
	protected List<String> addConfigfile(List<String> filelist) {
		filelist.add("aopexecution.xml");
		return filelist;
	}
	
	/*
	 * Spring AOP only support Public method.
	 * for more details
	 * https://jira.springsource.org/browse/SPR-4393?page=com.atlassian.jira.plugin.system.issuetabpanels%3Aall-tabpanel#issue-tabs
	 * 
	 * spring AOP just will be invoked once.
	 * 
	 * for this case .
	 * testAop(int) invoke testscope .and test scope will invoked testaop.
	 * but if we invoke testAoP,AOP will only be run once.
	 * it because only first invoke will use enhance bean.
	 * if the invoke is from internal, it will not use enhance method. 
	 */
	@Test
	public void testScope(){
		TargetBean bean = this.getContext().getBean("testScopeBean",TargetBean.class);
		
		System.out.println(bean.getClass());
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		bean.testAop();
	}


	@Test
	public void testReturnValue(){
		ExecutionBean bean = this.getContext().getBean("returnvalue",ExecutionBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT run AOP");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("///////////////");
		
		
		System.out.println("run AOP");
		System.out.println(bean.getValue());
	}
	
	@Test(expected=AlreadyBoundException.class)
	public void testthrowException() throws AlreadyBoundException{
		ExecutionBean bean = this.getContext().getBean("throwexceptionbean",ExecutionBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT run AOP");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("///////////////");
		
		
		System.out.println("run AOP");
		bean.throwsException();
	}
	
	@Test
	public void testPara() throws AlreadyBoundException{
		ExecutionParaBean bean = this.getContext().getBean("parabean",ExecutionParaBean.class);
		
		System.out.println(bean.getClass());
		

		System.out.println("run AOP once");
		bean.testAop(1,0.0);
		System.out.println("//////////////////////");
		bean.testAop(1);
		System.out.println("-------------------------------");
		System.out.println("run AOP twice");	
		bean.testAop(1,"a");
	}
}
