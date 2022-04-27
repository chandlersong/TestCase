package org.example.producer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class K8SProducerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(K8SProducerApplication.class)
                .run(args);
    }
}
