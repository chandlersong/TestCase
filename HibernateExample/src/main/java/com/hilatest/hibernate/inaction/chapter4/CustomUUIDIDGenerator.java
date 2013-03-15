
package com.hilatest.hibernate.inaction.chapter4;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomUUIDIDGenerator implements IdentifierGenerator {

    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return UUID.randomUUID().toString();
    }

}
