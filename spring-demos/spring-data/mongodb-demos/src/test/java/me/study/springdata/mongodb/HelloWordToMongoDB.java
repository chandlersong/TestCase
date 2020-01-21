package me.study.springdata.mongodb;

import lombok.extern.slf4j.Slf4j;
import me.study.springdata.mongodb.repository.Address;
import me.study.springdata.mongodb.repository.AddressRepository;
import me.study.springdata.mongodb.repository.Person;
import me.study.springdata.mongodb.repository.PersonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWordToMongoDB {

    private Random r = new Random();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testSimpleSave() {

        Person p = createPerson(RandomStringUtils.randomAlphanumeric(10));
        personRepository.insert(p);
    }

    @Test
    public void createPersonWithStreet() {

        Address address = new Address();
        address.setStreet(RandomStringUtils.randomAlphanumeric(10));
        address.setId(BigInteger.valueOf(Math.abs(r.nextLong())));
        addressRepository.insert(address);
        Person p = createPerson(RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.random(10),
                                Lists.newArrayList(address));
        personRepository.insert(p);
    }

    @Test
    public void testFindByName() {

        String expectedName = RandomStringUtils.randomAlphanumeric(10);
        Person p = createPerson(expectedName);
        personRepository.insert(p);
        Person byName = personRepository.findByName(expectedName);
        Assert.assertEquals(expectedName, byName.getName());
    }


    private Person createPerson(String name) {
        return createPerson(name, "noTitle", Lists.newArrayList());
    }

    private Person createPerson(String name, String title, List<Address> addressList) {
        Person person = new Person();

        person.setId(BigInteger.valueOf(Math.abs(r.nextLong())));
        person.setName(name);
        person.setTitle(title);
        person.setAddressList(addressList);
        return person;
    }


}
