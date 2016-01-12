package org.commmstudy.hibernate.jpa;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.commmstudy.hibernate.jpa.relationship.refernoid.dao.PersonDao;
import org.commmstudy.hibernate.jpa.relationship.refernoid.entity.Person;
import org.commmstudy.hibernate.jpa.relationship.refernoid.entity.PersonName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa_not_id_ref.xml")
public class NotRefIdTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testSave() {

        Person person = new Person();
        person.setNameFlag("testName");

        PersonName pName1 = new PersonName();
        pName1.setMeaning("pName1Meaning");

        PersonName pName2 = new PersonName();

        pName2.setMeaning("pName2Meaning");

        ArrayList<PersonName> pNameList = new ArrayList<PersonName>();
        pNameList.add(pName1);
        pNameList.add(pName2);
        person.setNames(pNameList);

        personDAO.savePerson(person);
    }

    @Resource(name = "personDao")
    private PersonDao personDAO;
}
