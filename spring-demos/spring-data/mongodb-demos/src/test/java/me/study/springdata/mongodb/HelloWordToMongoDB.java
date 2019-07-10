package me.study.springdata.mongodb;

import lombok.extern.slf4j.Slf4j;
import me.study.springdata.mongodb.repository.Person;
import me.study.springdata.mongodb.repository.PersonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Random;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWordToMongoDB {

    private Random r = new Random();

    @Autowired
    private PersonRepository repository;

    @Test
    public void testSimpleSave() {

        Person p = createPerson(RandomStringUtils.randomAlphanumeric(10));
        repository.insert(p);
    }

    @Test
    public void testFindByName() {

        String expectedName = RandomStringUtils.randomAlphanumeric(10);
        Person p = createPerson(expectedName);
        repository.insert(p);
        Person byName = repository.findByName(expectedName);
        Assert.assertEquals(expectedName, byName.getName());
    }



    private Person createPerson(String name) {
        return createPerson(name, "noTitle");
    }

    private Person createPerson(String name, String title) {
        Person p = new Person();
        p.setId(BigInteger.valueOf(Math.abs(r.nextLong())));
        p.setName(name);
        p.setTitle(title);
        return p;
    }


}
