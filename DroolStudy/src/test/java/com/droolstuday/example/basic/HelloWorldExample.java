package com.droolstuday.example.basic;

import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Test;

import com.droolstuday.example.model.Account;
import com.droolstuday.example.utils.DroolUtils;

public class HelloWorldExample {

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

}
