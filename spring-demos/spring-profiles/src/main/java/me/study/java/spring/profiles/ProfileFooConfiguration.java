package me.study.java.spring.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("Foo")
public class ProfileFooConfiguration {

    @Bean("Foo")
    public String defaultValue() {
        return "default";
    }
}
