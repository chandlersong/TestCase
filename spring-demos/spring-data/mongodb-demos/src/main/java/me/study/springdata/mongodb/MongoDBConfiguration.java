package me.study.springdata.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoDBConfiguration extends AbstractMongoConfiguration {


    private MongoDbFactory mongoDbFactory;

    private MongoMappingContext mongoMappingContext;

    @Override
    public MongoClient mongoClient() {

        ServerAddress serverAddress = new ServerAddress("192.168.3.189", 27017);
        MongoCredential credential = MongoCredential
                .createScramSha1Credential("chandler", "admin", "abc".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();
        return new MongoClient(serverAddress, credential, options);
    }

    @Override
    protected String getDatabaseName() {
        return "study";
    }


    @Bean
    public MappingMongoConverter mappingMongoConverter() {


        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    @Autowired
    public void setMongoDbFactory(MongoDbFactory mongoDbFactory) {
        this.mongoDbFactory = mongoDbFactory;
    }

    @Autowired
    public void setMongoMappingContext(MongoMappingContext mongoMappingContext) {
        this.mongoMappingContext = mongoMappingContext;
    }
}
