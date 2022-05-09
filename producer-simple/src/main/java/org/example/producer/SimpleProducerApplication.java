package org.example.producer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "org.example.producer")
public class SimpleProducerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SimpleProducerApplication.class)
                .run(args);
    }
}
