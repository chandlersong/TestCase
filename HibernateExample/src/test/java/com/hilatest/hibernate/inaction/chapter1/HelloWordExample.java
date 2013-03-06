
package com.hilatest.hibernate.inaction.chapter1;

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
@ContextConfiguration(locations = "classpath:inAction_chapter1_helloworld.xml")
public class HelloWordExample extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void helloWorld() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        Message message = new Message("Hello World");
        Long msgId = (Long)session.save(message);
        System.out.println("messae ID:" + msgId);
        tx.commit();
        session.close();

        // Second unit of work
        Session newSession = this.hibernateFactory.openSession();
        Transaction newTx = newSession.beginTransaction();

        List<?> messages = newSession.createQuery("from Message m order by m.text asc").list();
        System.out.println(messages.size() + " message(s) found:");

        for (Iterator<?> iter = messages.iterator(); iter.hasNext();) {
            Message loadedMsg = (Message)iter.next();
            System.out.println("id:" + loadedMsg.getId() + ",Message" + loadedMsg.getText());
        }
        newTx.commit();
        newSession.close();

    }
}
