package me.study.springdata.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PersonRepository extends MongoRepository<Person, BigInteger> {

    Person findByName(String name);
}
