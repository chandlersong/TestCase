package org.commmstudy.hibernate.relationship.onetoone;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.commmstudy.hibernate.relationship.onetoone.dao.PersonDao;
import org.commmstudy.hibernate.relationship.onetoone.entity.Address;
import org.commmstudy.hibernate.relationship.onetoone.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernateTest_one_to_one_map1.xml")
public class Map1Test extends AbstractJUnit4SpringContextTests {

    @Test
    public void testOK() {
        // First unit of work
        Session session = this.hibernateFactory.openSession();

        Transaction tx = session.beginTransaction();

        for (int i = 0; i < 10; i++) {
            Address address = new Address();
            address.setName(RandomStringUtils.randomAlphanumeric(10));
            Person person = new Person();
            person.setName(RandomStringUtils.randomAlphanumeric(10));
            person.setAddress(address);
            address.setPerson(person);
            session.save("Person", person);
        }
        tx.commit();
        session.close();

    }

    @Test
    public void testSpringVersion() {

        for (int i = 0; i < 10; i++) {
            Address address = new Address();
            address.setName(RandomStringUtils.randomAlphanumeric(10));
            Person person = new Person();
            person.setName(RandomStringUtils.randomAlphanumeric(10));
            person.setAddress(address);
            address.setPerson(person);
            persondao.savePerson(person);
        }

    }

    @Resource(name = "PersonDao")
    private PersonDao persondao;
    @Resource(name = "sessionFactory")
    private SessionFactory hibernateFactory;
}
