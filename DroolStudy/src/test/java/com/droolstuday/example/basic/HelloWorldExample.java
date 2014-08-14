package com.droolstuday.example.basic;

import javax.annotation.Resource;

import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.droolstuday.example.model.Account;
import com.droolstuday.example.utils.DroolUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:DroolSessionAll.xml")
public class HelloWorldExample extends AbstractJUnit4SpringContextTests {

	@Test
	public void helloWorld() {

		StatelessKnowledgeSession ksession = DroolUtils.createKSession("Hello_World.drl");
		Account account1 = new Account(200);
		account1.withdraw(150);
		ksession.execute(account1);

		Account account2 = new Account(200);
		ksession.execute(account2);

		Object object = new Object();
		ksession.execute(object);

	}

	@Resource(name = "ksessionAllStateless")
	private StatelessKnowledgeSession ksessionAll;

	@Test
	public void helloWorldWithSpring() {
		Account account1 = new Account(200);
		account1.withdraw(150);
		ksessionAll.execute(account1);

	}

}
