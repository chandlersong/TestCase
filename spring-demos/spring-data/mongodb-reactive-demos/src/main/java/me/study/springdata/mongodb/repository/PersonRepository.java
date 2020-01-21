package me.study.springdata.mongodb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, BigInteger> {

    Mono<Person> findByName(String name);

    Flux<Person> findByTitle(String title);
}
