package me.study.java.kafka.message;

import lombok.Data;

@Data
public class SimpleMessage {

    private String messageContext;

    private String user;

    public SimpleMessage(String messageContext, String user) {
        this.messageContext = messageContext;
        this.user = user;
    }
}
