
package com.hilatest.hibernate.inaction.chapter5.customertype.usertypeExample;

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

/**
 * NOTE: <br>
 * 1)there are two ways to define one a customer type.<br>
 * a)like the example do. use typedef to define a custome type.(I think it's a good way) <br>
 * b)use full path name in property's type. even you have define package in hibernate-mapping<br>
 * 
 * @author chandler.song
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter5_customerType_usertype.xml")
public class UserTypeTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void testBasic() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        Message message = new Message("user type");
        StringSize ss = new StringSize("user type");
        message.setStringSize(ss);
        Long msgId = (Long)session.save(message);

        System.out.println("super message ID:" + msgId);

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
            System.out.println("String Size:" + loadedMsg.getStringSize().getSize());
        }
        newTx.commit();
        newSession.close();

    }

    /**
     * use debug to test
     */
    @Test
    public void testNull() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        Message message = new Message("user type");

        Long msgId = (Long)session.save(message);

        System.out.println("super message ID:" + msgId);

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
            if (loadedMsg.getStringSize() != null) {
                System.out.println("String Size:" + loadedMsg.getStringSize().getSize());
            }
        }
        newTx.commit();
        newSession.close();

    }

}
