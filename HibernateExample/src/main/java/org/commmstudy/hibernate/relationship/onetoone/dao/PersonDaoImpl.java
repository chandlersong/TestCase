package org.commmstudy.hibernate.relationship.onetoone.dao;

import org.commmstudy.hibernate.relationship.onetoone.entity.Person;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

    public void savePerson(Person p) {
        getHibernateTemplate().save("Person", p);
    }
}
