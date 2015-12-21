package org.commmstudy.hibernate.jpa.dao.impl;

import org.commmstudy.hibernate.jpa.entity.Person;
import org.springframework.stereotype.Repository;

@Repository("PersonRepositoryXX")
public interface PersonRepository extends MyBaseRepository<Person, String> {

    @Override
    Person save(Person p);
}
