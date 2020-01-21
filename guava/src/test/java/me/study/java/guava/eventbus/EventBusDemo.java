package me.study.java.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class EventBusDemo {

    @Test
    public void testSimpleBusEvent() {

        log.info("main process thread id:{}", Thread.currentThread().getId());
        EventBus bus = new EventBus();

        bus.register(new EventListener());
        bus.register(new EventListener());

        bus.post(new TestEvent());

    }


}


@Slf4j
class EventListener {

    @Subscribe
    public void handlerEvent(TestEvent e) {
        log.info("Thread id:{}", Thread.currentThread().getId());
    }
}

@Data
class TestEvent {
}
