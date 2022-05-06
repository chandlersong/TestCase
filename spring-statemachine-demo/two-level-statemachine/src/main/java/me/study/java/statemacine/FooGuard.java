package me.study.java.statemacine;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class FooGuard implements Guard<States, Events> {

    private final int match;

    public FooGuard(int match) {
        this.match = match;
    }

    @Override
    public boolean evaluate(StateContext<States, Events> context) {
        Object foo = context.getExtendedState().getVariables().get("foo");
        return !(foo == null || !foo.equals(match));
    }
}
