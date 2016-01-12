package org.commmstudy.hibernate.jpa.relationship.refernoid.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.commmstudy.hibernate.jpa.relationship.refernoid.dao.PersonDao;
import org.commmstudy.hibernate.jpa.relationship.refernoid.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("personDao")
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void savePerson(Person p) {

		entityManager.persist(p);
	}

}
