
package com.hilatest.hibernate.inaction.chapter3;

import java.io.IOException;

import javax.annotation.Resource;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilatest.hibernate.inaction.chapter1.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inAction_chapter1_helloworld.xml")
public class XMLOutputExample extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Test
    public void basicTest() {

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
        Session dom4jSession = newSession.getSession(EntityMode.DOM4J);

        Transaction newTx = newSession.beginTransaction();

        Element userXML = (Element)dom4jSession.load(Message.class, msgId);
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(System.out, format);
            writer.write(userXML);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        newTx.commit();
        newSession.close();

    }
}
