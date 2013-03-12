
package com.hilatest.hibernate.inaction.chapter2;

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

import com.hilatest.hibernate.inaction.chapter2.placeholder.ItemPlaceHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter2_placeholder.xml")
public class PlaceHolderTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void testPlaceHolder() {

        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        ItemPlaceHolder message = new ItemPlaceHolder();
        message.setName("Hello World!");
        Long msgId = (Long)session.save(message);
        System.out.println("messae ID:" + msgId);
        tx.commit();
        session.close();

        // Second unit of work
        Session newSession = this.hibernateFactory.openSession();
        Transaction newTx = newSession.beginTransaction();

        @SuppressWarnings("unchecked")
        List<ItemPlaceHolder> messages = (List<ItemPlaceHolder>)newSession.createQuery(
                "from ItemPlaceHolder item order by item.name asc").list();

        System.out.println(messages.size() + " message(s) found:");

        for (Iterator<ItemPlaceHolder> iter = messages.iterator(); iter.hasNext();) {
            ItemPlaceHolder loadedMsg = (ItemPlaceHolder)iter.next();
            System.out.println("id:" + loadedMsg.getId() + ",Message:" + loadedMsg.getName());
        }
        newTx.commit();
        newSession.close();

    }
}
