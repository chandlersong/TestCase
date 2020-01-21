package me.study.springdata.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "me.study.springdata.mongodb.repository")
@Configuration
public class MongoDBConfigurationDemo2 extends AbstractMongoClientConfiguration {

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://chandler:abc@192.168.3.189:27017/admin");
    }

    @Override
    protected String getDatabaseName() {
        return "study";
    }
}
