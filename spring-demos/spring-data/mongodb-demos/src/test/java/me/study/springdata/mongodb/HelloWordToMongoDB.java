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
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

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
        repository.insert(p).subscribe();
    }

    @Test
    public void testFindByName() {

        String expectedName = RandomStringUtils.randomAlphanumeric(10);
        Person p = createPerson(expectedName);
        repository.insert(p).subscribe();
        Person byName = repository.findByName(expectedName);
        Assert.assertEquals(expectedName, byName.getName());
    }


    @Test
    public void testFindByStream() throws InterruptedException {

        String title = RandomStringUtils.randomAlphanumeric(10);

        for (int i = 0; i < 10; i++) {
            String expectedName = RandomStringUtils.randomAlphanumeric(10);
            Person p = createPerson(expectedName, title);
            repository.insert(p).subscribe();
        }
        Flux<Person> people = repository.findByTitle(title);


        Disposable subscribe = people.doOnNext(p -> {
            Assert.assertEquals(title, p.getTitle());
            log.info("name {}", p.getName());
        }).subscribe();

        while (!subscribe.isDisposed()) {
            Thread.sleep(1000);
        }
    }

    private Person createPerson(String name) {
        return createPerson(name, "noTitle");
    }

    private Person createPerson(String name, String title) {
        Person p = new Person();
        p.setId(BigInteger.valueOf(Math.abs(r.nextLong())));
        p.setName(name);
        return p;
    }


}
