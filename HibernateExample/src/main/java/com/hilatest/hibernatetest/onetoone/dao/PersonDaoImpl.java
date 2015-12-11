package com.hilatest.hibernatetest.onetoone.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hilatest.hibernatetest.onetoone.entity.Person;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

    public void savePerson(Person p) {
        getHibernateTemplate().save("Person", p);
    }
}
