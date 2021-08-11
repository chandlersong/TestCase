package me.study.smhello;


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


    @ShellMethod("publish E1")
    public String E1() {
        stateMachine.sendEvent(Mono.just(MessageBuilder
                                                 .withPayload(Events.E1).build()));
        return "e1 send";
    }

    @ShellMethod("publish E2")
    public String E2() {
        stateMachine.sendEvent(Mono.just(MessageBuilder
                                                 .withPayload(Events.E2).build()));
        return "e2 send";
    }
}
