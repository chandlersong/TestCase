package org.commmstudy.hibernate.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.commmstudy.hibernate.jpa.dao.MessageDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hilatest.hibernate.inaction.chapter1.Message;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveMessage(Message m) {
        entityManager.persist(m);
    }

}
