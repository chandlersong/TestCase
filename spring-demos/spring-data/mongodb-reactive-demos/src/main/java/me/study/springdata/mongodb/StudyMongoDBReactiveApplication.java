package me.study.springdata.mongodb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

@SpringBootApplication(exclude = {MongoReactiveAutoConfiguration.class, MongoReactiveDataAutoConfiguration.class, MongoAutoConfiguration.class})
public class StudyMongoDBReactiveApplication {
}
