
package com.hilatest.hibernate.inaction.chapter6.collection.map;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

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
@ContextConfiguration(locations = "classpath:inAction_chapter6_collection_map.xml")
public class SimpleMapTest {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void testBasic() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        MapMessage message = new MapMessage("I'm super class");
        message.addComments("test1", "comments1");
        message.addComments("test2", "comments2");
        Long msgId = (Long)session.save(message);
        System.out.println("super message ID:" + msgId);

        tx.commit();
        session.close();

        // Second unit of work
        Session newSession = this.hibernateFactory.openSession();
        Transaction newTx = newSession.beginTransaction();

        List<?> messages = newSession.createQuery("from MapMessage m order by m.text asc").list();
        System.out.println(messages.size() + " message(s) found:");

        Iterator<?> iter = messages.iterator();
        while (iter.hasNext()) {
            MapMessage msg = (MapMessage)iter.next();
            System.out.println("id:" + msg.getId() + ",Message" + msg.getText());
            System.out.println("tags:");
            Iterator<Entry<String, String>> commentsIter = msg.getComments().entrySet().iterator();
            while (commentsIter.hasNext()) {
                Entry<String, String> entry = commentsIter.next();
                System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
            }

        }
        newTx.commit();
        newSession.close();

    }
}
