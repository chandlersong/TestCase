package me.study.java.kafka.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Data
public class HelloWordTopicListener {

    private CountDownLatch latch;

    @KafkaListener(id = "helloWorldListener", topics = "helloWorld")
    public void listen1(String name) {
        log.info("receive message:{}", name);
        Optional.ofNullable(latch).ifPresent(CountDownLatch::countDown);
    }

    @KafkaListener(id = "helloWorldPause", topics = "helloWorldPause")
    public void listenPause(String name) throws InterruptedException {
        log.info("receive message in pause:{}", name);
        Thread.sleep(2000);
        Optional.ofNullable(latch).ifPresent(CountDownLatch::countDown);
    }

}
