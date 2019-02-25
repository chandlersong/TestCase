package me.study.java.spring.profiles;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
public class SimpleProfilesCases {

    @Test
    public void testHelloWorld() {
        SpringApplicationBuilder server = new SpringApplicationBuilder(ProfilesApplication.class);
        server.properties(String.format("spring.profiles.active", "foo"));
        ConfigurableApplicationContext context = server.run();
        log.info(context.getBean("defaultProfiles"));

    }

    @Test
    public void testWithDefine() {
        SpringApplicationBuilder server = new SpringApplicationBuilder(ProfilesApplication.class);
        server.properties(String.format("spring.profiles.active", "foo"));
        ConfigurableApplicationContext context = server.run();
        log.info(context.getBean("defineDefault"));

    }
}
