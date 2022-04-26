package org.example.producer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubbo(scanBasePackages = "org.example.producer")
public class SimpleProducerConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SimpleProducerConfiguration.class, args);
    }
}
