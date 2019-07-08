package me.study.springdata.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Override
    public MongoClient mongoClient() {

        ServerAddress serverAddress = new ServerAddress("192.168.3.189", 27017);
        MongoCredential credential = MongoCredential
                .createScramSha1Credential("chandler", "study", "abc".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();
        return new MongoClient(serverAddress, credential, options);
    }

    @Override
    protected String getDatabaseName() {
        return "study";
    }

}
