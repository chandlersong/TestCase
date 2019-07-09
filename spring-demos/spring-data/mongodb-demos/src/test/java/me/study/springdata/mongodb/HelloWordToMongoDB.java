package me.study.springdata.mongodb;

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

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWordToMongoDB {

    @Autowired
    private PersonRepository repository;

    @Test
    public void testSimpleSave() {

        Random r = new Random();
        Person p = new Person();
        p.setId(BigInteger.valueOf(Math.abs(r.nextLong())));
        p.setName(RandomStringUtils.randomAlphanumeric(10));
        repository.insert(p);
    }


    @Test
    public void testFindByName() {

        Random r = new Random();
        Person p = new Person();
        String expectedName = RandomStringUtils.randomAlphanumeric(10);
        p.setId(BigInteger.valueOf(Math.abs(r.nextLong())));
        p.setName(expectedName);
        repository.insert(p);


        Person byName = repository.findByName(expectedName);

        Assert.assertEquals(expectedName,byName.getName());
    }


}
