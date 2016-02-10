package org.commmstudy.hibernate.jpa;

import javax.annotation.Resource;

import org.commmstudy.hibernate.jpa.dao.MessageDao;
import org.commmstudy.hibernate.jpa.dao.impl.message.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilatest.hibernate.inaction.chapter1.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa_hibernate.xml")
public class HibernateTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testSave() {
        Message m1 = new Message();
        m1.setText("hibernateTest");
        dao.saveMessage(m1);
    }

    @Test
    public void testSaveBySpringData() {
        Message m1 = new Message();
        m1.setText("hibernateTest");
        repository.save(m1);
    }

    @Resource(name = "messageDao")
    private MessageDao dao;

    @Resource(name = "messageRepository")
    private MessageRepository repository;
}
