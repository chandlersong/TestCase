
package com.hilatest.hibernate.inaction.chapter3;

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

import com.hilatest.hibernate.inaction.chapter3.duplicateConfig.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter3_duplicateConfiguration.xml")
public class duplicateConfigurationTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void testPlaceHolder() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        Message message = new Message("Hello World!");
        Long msgId = (Long)session.save("MESSAGE_1", message);
        System.out.println("messae ID:" + msgId);
        tx.commit();
        session.close();

        // Second unit of work
        Session newSession = this.hibernateFactory.openSession();
        Transaction newTx = newSession.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Message> messages = (List<Message>)newSession.createQuery("from MESSAGE_1 msg order by msg.text asc")
                .list();

        System.out.println(messages.size() + " message(s) found:");

        for (Iterator<Message> iter = messages.iterator(); iter.hasNext();) {
            Message loadedMsg = iter.next();
            System.out.println("id:" + loadedMsg.getId() + ",Message:" + loadedMsg.getText());
        }
        newTx.commit();
        newSession.close();

        // First unit of work
        Session session3 = this.hibernateFactory.openSession();

        Transaction tx3 = session3.beginTransaction();

        message = new Message("Hello World!");
        msgId = (Long)session3.save("MESSAGE_2", message);
        System.out.println("messae ID:" + msgId);
        tx3.commit();
        session3.close();

        // Second unit of work
        Session newSession3 = this.hibernateFactory.openSession();
        Transaction newTx3 = newSession3.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Message> newMessages = (List<Message>)newSession3.createQuery("from MESSAGE_2 msg order by msg.text asc")
                .list();

        System.out.println(newMessages.size() + " message(s) found:");

        for (Iterator<Message> iter = newMessages.iterator(); iter.hasNext();) {
            Message loadedMsg = iter.next();
            System.out.println("id:" + loadedMsg.getId() + ",Message:" + loadedMsg.getText());
        }
        newTx3.commit();
        newSession3.close();

    }
}
