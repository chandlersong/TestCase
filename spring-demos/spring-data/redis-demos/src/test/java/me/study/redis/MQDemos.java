package me.study.redis;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.Disposable;

import java.util.concurrent.CountDownLatch;

@Log4j2
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MQDemos {

    @Autowired
    private ReactiveRedisTemplate<String, String> template;

    /**
     * subscribe need to wait for some time
     * @throws InterruptedException nothing
     */
    @Test
    public void testSendAndListen() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        String channel = "test";
        Disposable test = template.listenToChannel(channel).doOnNext(msg -> {
            log.info("receive msg:{}", msg);
            latch.countDown();
        }).subscribe();

        Thread.sleep(500);

        template.convertAndSend(channel, "hello world").block();


        latch.await();
        while (!test.isDisposed()){
            Thread.sleep(2000);
            test.dispose();
        }

    }
}
