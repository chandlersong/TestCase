package me.study.springdata.mongodb;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * exclude class {MongoReactiveDataAutoConfiguration.class, MongoAutoConfiguration.class, MongoReactiveAutoConfiguration.class, MongoDataAutoConfiguration.class}
 */
@Configuration
@Profile("solution1")
@EnableReactiveMongoRepositories(basePackages = "me.study.springdata.mongodb.repository")
public class MongoDBReactiveConfiguration extends AbstractReactiveMongoConfiguration {


    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create("mongodb://chandler:abc@192.168.3.189:27017/admin");
    }

    @Override
    protected String getDatabaseName() {
        return "study";
    }

    @Bean
    public com.mongodb.client.MongoClient mongoClient() {
        return com.mongodb.client.MongoClients.create("mongodb://chandler:abc@192.168.3.189:27017/admin");
    }

}
