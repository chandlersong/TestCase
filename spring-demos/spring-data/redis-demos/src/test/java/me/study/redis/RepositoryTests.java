package me.study.redis;

import lombok.extern.log4j.Log4j2;
import me.study.redis.entry.Person;
import me.study.redis.repositories.PersonRepository;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@Log4j2
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RedisTemplate<String, String> template;


    /**
     * it will create two key
     * one is for the information of object
     * another is the object list
     */
    @Test
    public void testSavePerson() {
        long id = Math.abs(RandomUtils.nextLong());
        log.info("id is {}", id);
        Person p = new Person(id, "chandler");
        personRepository.save(p);

        Set<String> keys = template.keys("*");

        log.info("all keys in redis,{}", keys);


        assert keys != null;
        template.delete(keys);
    }
}
