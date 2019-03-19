package me.study.java.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class HelloWordTopicListener {

    private CountDownLatch latch;

    @KafkaListener(id = "helloWorldListener", topics = "helloWorld")
    public void listen1(String name) {
        log.info("receive message:{}", name);
        Optional.ofNullable(latch).ifPresent(CountDownLatch::countDown);
    }


    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
