package com.hilatest.hibernateexample.jta;

import java.util.Random;

import javax.annotation.Resource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilatest.hibernateexample.chapter1.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:basic-atomikios-jta-transaction.xml")
public class AtomikiosJtaBasicTest extends AbstractJUnit4SpringContextTests {

	@Resource(name="sessionFactory")
    private SessionFactory hibernateFactory;
	
	@Resource(name="&sessionFactory")
    private LocalSessionFactoryBean springFactory;
	
	@Test
	public void helloWorld(){
		System.out.println("hibernate factory:"+this.hibernateFactory);
		System.out.println("spring factory:"+this.springFactory);
	}
	
	
	@Test
	public void AddOneMessage() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException{
		Session session = hibernateFactory.openSession();

		
	    UserTransaction  tx = new com.atomikos.icatch.jta.UserTransactionImp();
	    Message m = new Message("atomikos"+RandomStringUtils.randomAlphanumeric(10));
	    m.setId(new Random().nextLong());
	    tx.begin();
	    
	    Long id = m.getId();
	    System.out.println("id:"+ id);
	    session.save(m);
    
	    System.out.println("==============================");
	    System.out.println("print transaction:"+ tx);
	    session.flush();
	    session.close();
	    tx.commit();
	    
	    Session verifySession = hibernateFactory.openSession();
	    Object o = verifySession.get(Message.class,id);
	    System.out.println("get item:"+ o);
	    verifySession.close();
	
	}
}
