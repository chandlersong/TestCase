package com.droolstuday.example.basic;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.droolstuday.example.model.agenda.AgendaObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:AgendaDrool.xml")
public class AgendaTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testSalience() {
        AgendaObject fact = new AgendaObject("salience");

        ksession.insert(fact);

        ksession.fireAllRules();
    }

    @Resource(name = "ksessionAllStateful")
    private StatefulKnowledgeSession ksession;
}
