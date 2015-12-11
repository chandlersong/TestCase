package com.hilatest.hibernateexample.jta;

import javax.annotation.Resource;
import javax.resource.NotSupportedException;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.SystemException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:basic-hibernate-jta-transaction.xml")
public class HibernateJtaBasicTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;

    @Resource(name = "&sessionFactory")
    private LocalSessionFactoryBean springFactory;

    @Test
    public void helloWorld() {
        System.out.println("hibernate factory:" + this.hibernateFactory);
        System.out.println("spring factory:" + this.springFactory);
    }

    /**
     * 这个例子失败的原因是hibernate需要把DB的连接，包装成是一个jndi
     * 
     * @throws SecurityException
     * @throws IllegalStateException
     * @throws RollbackException
     * @throws HeuristicMixedException
     * @throws HeuristicRollbackException
     * @throws SystemException
     * @throws NotSupportedException
     */
    @Test
    public void AddOneMessage() throws SecurityException, IllegalStateException, SystemException, NotSupportedException {
        /*
         * Session session = hibernateFactory.openSession();
         * Transaction tx = session.beginTransaction();
         * 
         * Message m = new Message("hibernate:" + RandomStringUtils.randomAlphanumeric(10));
         * m.setId(new Random().nextLong());
         * tx.begin();
         * 
         * Long id = m.getId();
         * System.out.println("id:" + id);
         * System.out.println("message:" + m.getText());
         * session.save(m);
         * 
         * System.out.println("==============================");
         * System.out.println("print transaction:" + tx);
         * session.flush();
         * session.close();
         * tx.commit();
         * 
         * Session verifySession = hibernateFactory.openSession();
         * Transaction verifyTx = verifySession.beginTransaction();
         * Object o = verifySession.get(Message.class, id);
         * System.out.println("get item:" + o);
         * verifySession.close();
         * 
         * verifyTx.commit();
         */
    }
}
