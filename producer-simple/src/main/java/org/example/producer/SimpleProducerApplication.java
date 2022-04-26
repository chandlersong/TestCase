package org.example.producer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SimpleProducerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SimpleProducerApplication.class)
                .run(args);
    }
}
