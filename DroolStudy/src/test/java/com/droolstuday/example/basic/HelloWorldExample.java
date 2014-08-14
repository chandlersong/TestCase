package com.droolstuday.example.basic;

import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Test;

import com.droolstuday.example.model.Account;
import com.droolstuday.example.utils.DroolUtils;

public class HelloWorldExample {

	@Test
	public void helloWorld() {

		StatelessKnowledgeSession ksession = DroolUtils.createKSession("Hello_World.drl");
		Account account = new Account(200);
		account.withdraw(150);
		ksession.execute(account);

	}

}
