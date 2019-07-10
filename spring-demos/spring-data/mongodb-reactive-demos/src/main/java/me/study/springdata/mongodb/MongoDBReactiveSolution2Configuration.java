package me.study.springdata.mongodb;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoClientFactoryBean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoDBReactiveSolution2Configuration {


    @Bean
    public  ReactiveMongoClientFactoryBean mongoClient() {
        ReactiveMongoClientFactoryBean clientFactory = new ReactiveMongoClientFactoryBean();
        clientFactory.setConnectionString("mongodb://chandler:abc@192.168.3.189:27017/admin");
        return clientFactory;
    }

    @Autowired
    MongoClient mongoClient;

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, "study");
    }
}
