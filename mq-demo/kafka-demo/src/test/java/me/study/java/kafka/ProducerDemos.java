package me.study.java.kafka;

import lombok.extern.slf4j.Slf4j;
import me.study.java.kafka.listener.HelloWordTopicListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerDemos {

    @Autowired
    private KafkaTemplate<Object, Object> template;


    @Autowired
    private HelloWordTopicListener listener;

    /**
     * need add kafka server to you host
     */
    @Test
    public void sendSyncMessage() throws InterruptedException, ExecutionException {

        CountDownLatch latch = new CountDownLatch(1);

        listener.setLatch(latch);

        ListenableFuture<SendResult<Object, Object>> send = template.send("helloWorld", "chandler");
        latch.await(10, TimeUnit.SECONDS);

        log.info("send result:{}", send.get());
    }


    /**
     * need add kafka server to you host
     */
    @Test
    public void sendASyncMessage() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        listener.setLatch(latch);
        ListenableFuture<SendResult<Object, Object>> send = template.send("helloWorldPause", "chandler");

        send.addCallback(result -> log.info("success {}", result), ex -> log.info("failed {}", ex));

        latch.await(10, TimeUnit.SECONDS);

    }

    /**
     * need add kafka server to you host
     */
    @Test
    public void sendASyncMessageMainStopFirst() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        listener.setLatch(latch);
        ListenableFuture<SendResult<Object, Object>> send = template.send("helloWorld", "chandler");

        send.addCallback(result -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.error("error happened", e);
            }
            log.info("success {}", result);
        }, ex -> log.info("failed {}", ex));
    }
}
