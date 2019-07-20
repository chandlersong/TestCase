package me.study.redis;

import lombok.extern.log4j.Log4j2;
import me.study.redis.entry.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j2
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldDemos {

    @Autowired
    private RedisTemplate<String, String> template;

    @Autowired
    private RedisTemplate<String, Person> personRedisTemplate;

    @Test
    public void testHelloWorld() {
        ListOperations<String, String> listOps = template.opsForList();
        log.info("template:{}", template);
        listOps.leftPush("hello", "chandler");
    }

    @Test
    public void testSaveCustomObject(){
        ListOperations<String, Person> listOps = personRedisTemplate.opsForList();

        log.info("template:{}", template);
        listOps.leftPush("object", new Person("chandler"));
    }
}
