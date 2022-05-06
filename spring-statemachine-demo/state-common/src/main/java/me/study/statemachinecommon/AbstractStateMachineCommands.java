package me.study.statemachinecommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class AbstractStateMachineCommands<S, E> {

    @Autowired
    private StateMachine<S, E> stateMachine;

    protected StateMachine<S, E> getStateMachine() {
        return stateMachine;
    }


    @ShellMethod(key = "sm state", value = "get statemachine status")
    public String state() {
        State<S, E> state = stateMachine.getState();
        if (state != null) {
            return StringUtils.collectionToCommaDelimitedString(state.getIds());
        } else {
            return "No state";
        }
    }

    @ShellMethod(key = "sm start", value = "start statemachine")
    public String start() {
        stateMachine.startReactively().subscribe();
        return "State machine started";
    }

    @ShellMethod(key = "sm stop", value = "stop statemachine")
    public String stop() {
        stateMachine.stopReactively().subscribe();
        return "State machine stopped";
    }


    @ShellMethod(key = "sm variables", value = "get statemachine variables")
    public String variables() {
        StringBuilder buf = new StringBuilder();
        Set<Map.Entry<Object, Object>> entrySet = stateMachine.getExtendedState().getVariables().entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entrySet.iterator();
        if (entrySet.size() > 0) {
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> e = iterator.next();
                buf.append(e.getKey() + "=" + e.getValue());
                if (iterator.hasNext()) {
                    buf.append("\n");
                }
            }
        } else {
            buf.append("No variables");
        }
        return buf.toString();
    }

}
