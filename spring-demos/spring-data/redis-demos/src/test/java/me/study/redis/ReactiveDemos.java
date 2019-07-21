package me.study.redis;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j2
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReactiveDemos {

    @Autowired
    private ReactiveRedisTemplate<String, String> template;

    @Test
    public void testSaveExample() {
        ReactiveValueOperations<String, String> ops = template.opsForValue();
        String key = "reactive";
        String value = RandomStringUtils.randomAlphanumeric(10);
        log.info("key is {} and value is {}", key, value);
        Boolean success = ops.set(key, value).block();
        log.info("save success {}", success);

        String actual = ops.get(key).block();

        log.info("read value is {}", actual);
        Assert.assertEquals(value, actual);

        template.delete(key).block();

    }
}
