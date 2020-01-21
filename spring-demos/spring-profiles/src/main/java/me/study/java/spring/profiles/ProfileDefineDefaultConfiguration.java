package me.study.java.spring.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class ProfileDefineDefaultConfiguration {


    @Bean("defineDefault")
    public String defaultValue() {
        return "default";
    }

    @Bean("Foo")
    public String getFooValue() {
        return "Foo from default";
    }

}
