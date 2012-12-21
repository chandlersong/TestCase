package com.hilatest.hibernateexample.utils;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:basic-jdbc-example.xml")
public class BasicHibernateExample extends AbstractJUnit4SpringContextTests {

	@Resource(name="sessionFactory")
    private SessionFactory hibernateFactory;
	
	@Resource(name="&sessionFactory")
    private LocalSessionFactoryBean springFactory;
	
	@Test
	public void helloWorld(){
		System.out.println("hibernate factory:"+this.hibernateFactory);
		System.out.println("spring factory:"+this.springFactory);
	}
}
