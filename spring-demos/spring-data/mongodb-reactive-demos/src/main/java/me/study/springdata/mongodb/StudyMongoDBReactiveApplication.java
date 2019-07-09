package me.study.springdata.mongodb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

@SpringBootApplication(exclude = {MongoReactiveDataAutoConfiguration.class, MongoAutoConfiguration.class, MongoReactiveAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class StudyMongoDBReactiveApplication {
}
