package me.study.springdata.mongodb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "me.study.springdata.mongodb.repository")
@SpringBootApplication
public class StudyMongoDBApplication {
}
