package org.commmstudy.hibernate.jpa.dao.impl.message;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.hilatest.hibernate.inaction.chapter1.Message;

@Repository("messageRepository")
public interface MessageRepository extends org.springframework.data.repository.Repository<Message, Serializable> {

    void save(Message m);
}
