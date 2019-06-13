package me.chandlersong.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WebFluxConfiguration {

    @Bean
    public Object createOtherBeanInConfiguration(){

        log.info("bean in configuration has been initialized");
        return new Object();
    }

}
