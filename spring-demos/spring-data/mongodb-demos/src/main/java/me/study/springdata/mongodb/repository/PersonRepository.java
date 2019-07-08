package me.study.springdata.mongodb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PersonRepository extends CrudRepository<Person, BigInteger> {
}
