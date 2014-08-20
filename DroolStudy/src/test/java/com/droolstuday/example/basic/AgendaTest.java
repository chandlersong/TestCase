package com.droolstuday.example.basic;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.rule.Agenda;
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

    @Test
    public void testGroup() {
        AgendaObject fact = new AgendaObject("group");

        ksession.insert(fact);

        Agenda agenda = ksession.getAgenda();
        agenda.getAgendaGroup("group1").setFocus();
        agenda.getAgendaGroup("group2").setFocus();
        agenda.getAgendaGroup("group3").setFocus();
        ksession.fireAllRules();

    }

    @Resource(name = "ksessionAllStateful")
    private StatefulKnowledgeSession ksession;
}
