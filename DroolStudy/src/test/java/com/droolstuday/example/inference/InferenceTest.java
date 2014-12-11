package com.droolstuday.example.inference;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.droolstuday.example.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:DroolSessionAll.xml")
public class InferenceTest {

    /**
     * inference is actual a transfer. transfer one object to another
     */
    @Test
    public void testInference() {
        Person p1 = new Person(19, "chandler");
        Person p2 = new Person(9, "monica");
        ksession.insert(p1);
        ksession.insert(p2);

        ksession.fireAllRules();
    }

    @Resource(name = "ksessionAllStateful")
    private StatefulKnowledgeSession ksession;
}
