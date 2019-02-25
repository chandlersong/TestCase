package me.study.java.spring.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileDefaultConfiguration {


    @Bean("defaultProfiles")
    public String defaultValue() {
        return "default";
    }

}
