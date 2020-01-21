package me.study.java.kafka;

import me.study.java.kafka.listener.HelloWordTopicListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaDemoConfiguration {

    @Bean
    public HelloWordTopicListener createHelloWorld() {
        return new HelloWordTopicListener();
    }
}
