package me.study.java.statemacine;

import me.study.statemachinecommon.AbstractStateMachineCommands;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import reactor.core.publisher.Mono;

@ShellComponent
public class Command extends AbstractStateMachineCommands<States, Events> {


    @ShellMethod(value = "Add two integers together.", key = "sum")
    public String event(@ShellOption(value = {"", "event"}, help = "The event") final Events event) {
        getStateMachine()
                .sendEvent(Mono.just(MessageBuilder
                                             .withPayload(event).build()))
                .subscribe();
        return "Event " + event + " send";
    }
}
