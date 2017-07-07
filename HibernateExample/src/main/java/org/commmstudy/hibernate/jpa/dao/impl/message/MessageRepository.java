package org.commmstudy.hibernate.jpa.dao.impl.message;

import java.io.Serializable;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hilatest.hibernate.inaction.chapter1.Message;

@Repository("messageRepository")
public interface MessageRepository extends org.springframework.data.repository.CrudRepository<Message, Serializable> {

    List<Message> findByText(String text);

    @Cacheable(value = "org.hibernate.cache.spi.UpdateTimestampsCache")
    List<Message> findByTag(String tag);

    @Modifying
    @Query("update #{#entityName} message set message.tag = ?2 where message.tag = ?1")
    void updateByTag(String oldTag, String newTag);
}
