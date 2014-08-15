package com.droolstuday.example.kis;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.droolstuday.example.model.Account;

public class TestKis {

    @Test
    public void setupKis() {
        KieServices ks = KieServices.Factory.get();

        KieContainer kContainer = ks.getKieClasspathContainer();

        StatelessKieSession kSession = kContainer.newStatelessKieSession("ksession-All");

        Account account1 = new Account(200);
        account1.withdraw(150);
        kSession.execute(account1);
    }
}
