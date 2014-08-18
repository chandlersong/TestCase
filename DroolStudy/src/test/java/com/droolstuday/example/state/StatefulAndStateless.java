package com.droolstuday.example.state;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.droolstuday.example.model.Fire;
import com.droolstuday.example.model.Room;
import com.droolstuday.example.model.Sprinkler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:DroolSessionAll.xml")
public class StatefulAndStateless extends AbstractJUnit4SpringContextTests {

    @Resource(name = "ksessionAllStateful")
    private StatefulKnowledgeSession ksession;

    @Test
    public void testStateful() {
        String[] names = new String[] { "kitchen", "bedroom", "office", "livingroom" };

        Map<String, Room> name2room = new HashMap<String, Room>();

        for (String name : names) {

            Room room = new Room(name);
            name2room.put(name, room);

            ksession.insert(room);
            Sprinkler sprinkler = new Sprinkler(room);
            ksession.insert(sprinkler);

        }

        ksession.fireAllRules();

        Fire kitchenFire = new Fire(name2room.get("kitchen"));

        Fire officeFire = new Fire(name2room.get("office"));

        FactHandle kitchenFireHandle = ksession.insert(kitchenFire);

        FactHandle officeFireHandle = ksession.insert(officeFire);

        ksession.fireAllRules();
        
        
        ksession.delete( kitchenFireHandle );

        ksession.delete( officeFireHandle );


        ksession.fireAllRules();
    }
}
