package com.droolstuday.example.utils;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class DroolUtils {

    public static StatelessKieSession createKSession(String kbaseName, String sessionName) {
        KieServices ks = KieServices.Factory.get();

        KieContainer kContainer = ks.getKieClasspathContainer();

        StatelessKieSession kSession = kContainer.newStatelessKieSession(sessionName);

        return kSession;
    }

    public static StatelessKieSession createKSession(String sessionName) {
        return createKSession("separated_rules", sessionName);
    }

    public static StatelessKieSession createKSession() {

        return createKSession("all_rules", "ksession-All");
    }

}
