package org.commmstudy.hibernate.jpa;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.commmstudy.hibernate.jpa.dao.PersonDao;
import org.commmstudy.hibernate.jpa.dao.impl.PersonRepository;
import org.commmstudy.hibernate.jpa.entity.EmailAddress;
import org.commmstudy.hibernate.jpa.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa_simple.xml")
public class SimpleTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testOK() {
        for (int i = 0; i < 10; i++) {
            EmailAddress address = new EmailAddress();
            address.setName(RandomStringUtils.randomAlphanumeric(10));
            address.setEmailAddress(RandomStringUtils.randomAlphanumeric(10));
            Person person = new Person();
            person.setName(RandomStringUtils.randomAlphanumeric(10));
            person.setAddress(address);
            address.setPerson(person);
            personDAO.savePerson(person);
        }
    }

    @Test
    public void testSpringData() {
        for (int i = 0; i < 10; i++) {
            EmailAddress address = new EmailAddress();
            address.setName(RandomStringUtils.randomAlphanumeric(10));
            address.setEmailAddress(RandomStringUtils.randomAlphanumeric(10));
            Person person = new Person();
            person.setName(RandomStringUtils.randomAlphanumeric(10));
            person.setAddress(address);
            address.setPerson(person);
            repository.save(person);
        }
    }

    @Resource(name = "PersonRepositoryXX")
    private PersonRepository repository;

    @Resource(name = "personDao")
    private PersonDao personDAO;
}
