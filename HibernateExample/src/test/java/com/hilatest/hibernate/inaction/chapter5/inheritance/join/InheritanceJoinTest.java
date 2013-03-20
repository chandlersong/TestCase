
package com.hilatest.hibernate.inaction.chapter5.inheritance.join;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * NOTES: <br>
 * 1) if you want special super class' discriminator, you can set discriminator-value in super class
 * 
 * @author chandler.song
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter5_inheritance_join.xml")
public class InheritanceJoinTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    private Random r = new Random();

    @Test
    public void testBasic() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        Message message = new Message("I'm super class");
        Long msgId = (Long)session.save(message);
        System.out.println("super message ID:" + msgId);

        RandomIntegerMessage randomIntegermessage = new RandomIntegerMessage();
        randomIntegermessage.setText("I'm super RandomIntegerMessage");
        randomIntegermessage.setRandomInteger(r.nextInt());
        msgId = (Long)session.save(randomIntegermessage);
        System.out.println("randomIntegermessage messae ID:" + msgId);

        RandomStringMessage randomStringmessage = new RandomStringMessage();
        randomStringmessage.setText("I'm super RandomStringMessage");
        randomStringmessage.setRandomString(RandomStringUtils.randomAlphanumeric(10));
        msgId = (Long)session.save(randomStringmessage);
        System.out.println("randomStringmessage message ID:" + msgId);

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

            if (loadedMsg instanceof RandomIntegerMessage) {
                System.out.println("I'm a random interger Message, mine random integer:"
                        + ((RandomIntegerMessage)loadedMsg).getRandomInteger());

            }

            if (loadedMsg instanceof RandomStringMessage) {
                System.out.println("I'm a random String Message, mine random String:"
                        + ((RandomStringMessage)loadedMsg).getRandomString());

            }
        }
        newTx.commit();
        newSession.close();

    }

}
