package me.study.redis;

import me.study.redis.entry.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories(basePackages = {"me.study.redis.repositories"})
public class RedisConfiguration {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(@Value("${spring.redis.host}") String server,
                                                           @Value("${spring.redis.port}") int port) {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(
                server,
                port));
    }

    @Bean
    @Autowired
    public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean(name="reactiveFactory")
    public ReactiveRedisConnectionFactory connectionFactory(@Value("${spring.redis.host}") String server,
                                                            @Value("${spring.redis.port}") int port) {
        return new LettuceConnectionFactory(server, port);
    }

    @Bean
    @Autowired
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(@Qualifier("reactiveFactory") ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
    }
}
