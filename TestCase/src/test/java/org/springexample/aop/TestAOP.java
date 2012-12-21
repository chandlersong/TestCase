package org.springexample.aop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springexample.aop.tagert.TargetBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aopbasic.xml")
public class TestAOP extends AbstractJUnit4SpringContextTests {

    @Resource
    private TargetBean beforebean;
    
	@Test
	public void testbefore(){
		TargetBean bean = this.beforebean;
		
		System.out.println(bean.getClass());
		bean.testAop();
	}
	
	@Test(expected=RuntimeException.class)
	public void testafterreturning(){
		TargetBean bean = this.applicationContext.getBean("afterreturningbean",TargetBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT throw exception");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("////////////////////////");
		//not run aop
		System.out.println("throw exception");
		bean.testAop(TargetBean.THROWEXCPTION);
	}

	@Test(expected=RuntimeException.class)
	public void testafterthrow(){
		TargetBean bean = this.applicationContext.getBean("throwbean",TargetBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT throw exception");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("////////////////////////");
		//not run aop
		System.out.println("throw exception");
		bean.testAop(TargetBean.THROWEXCPTION);
	}
	
	@Test(expected=RuntimeException.class)
	public void testafterthrowCatch(){
		TargetBean bean =this.applicationContext.getBean("throwcatch",TargetBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT throw exception");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("////////////////////////");
		//not run aop
		System.out.println("throw exception");
		bean.testAop(TargetBean.THROWEXCPTION);
	}
	
	
	@Test(expected=RuntimeException.class)
	public void testafter(){
		TargetBean bean = this.applicationContext.getBean("afterbean",TargetBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT throw exception");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("////////////////////////");
		//not run aop
		System.out.println("throw exception");
		bean.testAop(TargetBean.THROWEXCPTION);
	}
	
	
	@Test
	public void testaround(){
		TargetBean bean = this.applicationContext.getBean("aroundbean",TargetBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT throw exception");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("////////////////////////");
		//not run aop
		System.out.println("throw exception");
		bean.testAop(TargetBean.THROWEXCPTION);
	}
	
	@Test
	public void testParamter(){
		TargetBean bean = this.applicationContext.getBean("paramterbean",TargetBean.class);
		
		System.out.println(bean.getClass());
		
		System.out.println("NOT throw exception");
		bean.testAop(TargetBean.NOTTHROWEXCPTION);
		System.out.println("////////////////////////");
		//not run aop
		System.out.println("throw exception");
		bean.testAop(TargetBean.THROWEXCPTION);
	}
}
