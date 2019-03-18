package me.study.java.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class HelloWordTopicListener {

    @KafkaListener(id = "helloWorldListener", topics = "helloWorld")
    public void listen1(String name) {
        log.info("receive message:{}", name);
    }

}
