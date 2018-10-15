package me.study.security.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ImportResource("classpath:application-beans.xml")
@EnableWebSecurity
public class SecurityRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityRestfulApplication.class, args);
    }
}
