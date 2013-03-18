
package com.hilatest.hibernate.inaction.chapter4;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter4_Component.xml")
public class ComponentTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void testBasic() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        Entry entry = new Entry("Hello World");
        Component component = new Component();
        component.setComponentText("I'm a component");
        entry.setComponent(component);

        Long msgId = (Long)session.save(entry);
        System.out.println("messae ID:" + msgId);
        tx.commit();
        session.close();

        // Second unit of work
        Session newSession = this.hibernateFactory.openSession();
        Transaction newTx = newSession.beginTransaction();

        List<?> entries = newSession.createQuery("from Entry m order by m.text asc").list();
        System.out.println(entries.size() + " message(s) found:");

        for (Iterator<?> iter = entries.iterator(); iter.hasNext();) {
            Entry loadedMsg = (Entry)iter.next();
            System.out.println("id:" + loadedMsg.getId() + ",Message" + loadedMsg.getText());
            System.out.println("component test:" + loadedMsg.getComponent().getComponentText());
        }
        newTx.commit();
        newSession.close();

    }
}
