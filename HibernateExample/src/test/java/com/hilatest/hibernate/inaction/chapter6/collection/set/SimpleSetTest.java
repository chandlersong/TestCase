
package com.hilatest.hibernate.inaction.chapter6.collection.set;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 1) if the object in set is value object. and don't association with other object in hibernate<br>
 * it will create record which link to the object
 * 
 * @author chandler.song
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter6_collection_set.xml")
public class SimpleSetTest {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void testBasic() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        SetMessage message = new SetMessage("I'm super class");
        message.addTags("test1");
        message.addTags("test2");
        Long msgId = (Long)session.save(message);
        System.out.println("super message ID:" + msgId);

        tx.commit();
        session.close();

        // Second unit of work
        Session newSession = this.hibernateFactory.openSession();
        Transaction newTx = newSession.beginTransaction();

        List<?> messages = newSession.createQuery("from SetMessage m order by m.text asc").list();
        System.out.println(messages.size() + " message(s) found:");

        Iterator<?> iter = messages.iterator();
        while (iter.hasNext()) {
            SetMessage msg = (SetMessage)iter.next();
            System.out.println("id:" + msg.getId() + ",Message" + msg.getText());
            System.out.println("tags:");
            Iterator<String> tagIter = msg.getTags().iterator();
            while (tagIter.hasNext()) {
                String tag = tagIter.next();
                System.out.println(tag);
            }

        }
        newTx.commit();
        newSession.close();

    }
}
