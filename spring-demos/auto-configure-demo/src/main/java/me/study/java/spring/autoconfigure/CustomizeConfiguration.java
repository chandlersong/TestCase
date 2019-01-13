package me.study.java.spring.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import me.study.java.spring.autoconfigure.beans.BeanA;
import me.study.java.spring.autoconfigure.beans.BeanB;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootConfiguration
@EnableConfigurationProperties(CustomProperties.class)
public class CustomizeConfiguration {

    private CustomProperties properties;

    public CustomizeConfiguration(CustomProperties properties) {

        log.info("properties loaded,test value is {},enabled value is:{}", properties.getTest(), properties.getEnabled());
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "test", name = "enabled", havingValue = "true")
    public BeanA crateBeanA() {
        log.info("bean A has been initialed");
        return new BeanA();
    }

    @Bean
    @ConditionalOnBean(BeanA.class)
    public BeanB createBeanB() {
        return new BeanB();
    }
}
