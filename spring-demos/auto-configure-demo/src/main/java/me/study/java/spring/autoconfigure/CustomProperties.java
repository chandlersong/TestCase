package me.study.java.spring.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "test")
public class CustomProperties {
    private String test;

    private Boolean enabled;
}
