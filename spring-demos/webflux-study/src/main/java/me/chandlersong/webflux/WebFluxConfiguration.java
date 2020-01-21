package me.chandlersong.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Slf4j
@Configuration
@EnableWebFlux
public class WebFluxConfiguration {

    public Object createOtherBeanInConfiguration(){

        log.info("bean in configuration has been initialized");
        return new Object();
    }

    @Bean
    public MyErrorAttribute<Throwable> createMyAttribute(){
        return new MyErrorAttribute<>();
    }

}
