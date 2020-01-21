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
        ConfigurableApplicationContext context = server.run();
        log.info(context.getBean("defaultProfiles"));
        log.info(context.getBean("Foo"));
    }

    @Test
    public void testWithDefine() {
        SpringApplicationBuilder server = new SpringApplicationBuilder(ProfilesApplication.class);
        server.properties(String.format("spring.profiles.active=%1s", "Foo"));
        ConfigurableApplicationContext context = server.run();
        log.info(context.getBean("Foo"));

    }
}
