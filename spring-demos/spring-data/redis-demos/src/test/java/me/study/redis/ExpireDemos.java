package me.study.redis;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Log4j2
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpireDemos {

    @Autowired
    private RedisTemplate<String, String> template;

    @Test
    public void setExpireByKey() throws InterruptedException {

        ValueOperations<String, String> ops = template.opsForValue();

        String key = RandomStringUtils.randomAlphabetic(10);
        String value = RandomStringUtils.randomAlphabetic(10);
        ops.set(key, value);
        template.expire(key, 2, TimeUnit.SECONDS);

        Thread.sleep(3000);
        Assert.assertNull(ops.get(key));

        ops.set(key, RandomStringUtils.randomAlphanumeric(10), Duration.ofSeconds(2));
        Thread.sleep(3000);

        Assert.assertTrue(Objects.requireNonNull(template.keys(key)).isEmpty());


    }


    @Test
    public void setExpireWillDisableWhenRefresh() throws InterruptedException {

        ValueOperations<String, String> ops = template.opsForValue();

        String key = RandomStringUtils.randomAlphabetic(10);
        String value = RandomStringUtils.randomAlphabetic(10);
        ops.set(key, value);
        template.expire(key, 2, TimeUnit.SECONDS);

        value = RandomStringUtils.randomAlphabetic(10);
        ops.set(key, value);
        Thread.sleep(5000);

        Assert.assertEquals(value, ops.get(key));

        template.delete(key);
    }
}
