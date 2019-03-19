package me.study.java.kafka;

import lombok.extern.slf4j.Slf4j;
import me.study.java.kafka.listener.HelloWordTopicListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldTest {

    @Autowired
    private KafkaTemplate<Object, Object> template;


    @Autowired
    private HelloWordTopicListener listener;

    /**
     * need add kafka server to you host
     */
    @Test
    public void testTestMessage() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        listener.setLatch(latch);
        template.send("helloWorld", "chandler");

        latch.await(10, TimeUnit.SECONDS);
    }
}
