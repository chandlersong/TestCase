package org.example.smhelloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

@ShellComponent
public class HelloWorldCommand {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @ShellMethod("trigger E1")
    public int toE1() {
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E1).build())).subscribe();
        return 0;
    }

    @ShellMethod("trigger E2")
    public int toE2() {
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E2).build())).subscribe();
        return 0;
    }

}
