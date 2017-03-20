package com.hilatest.common;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

public class ObserableTest {

    @Test
    public void testHelloWorld() {
        observableItem.run();
    }

    @Before
    public void setup() {

        o1 = new ObserverOne();

        o2 = new ObserverTwo();

        observableItem = new ObservableItem();
        observableItem.addObserver(o1);
        observableItem.addObserver(o2);
    }

    private ObservableItem observableItem;

    private ObserverOne o1;

    private ObserverTwo o2;
}

class ObservableItem extends Observable {

    public ObservableItem() {

    }

    public void run() {
        System.out.println("I am ObservableItem");
        System.out.println("I am:" + this);
        setChanged();
        notifyObservers(new Object());
    }
}

class ObserverOne implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("I am ObserverOne");
        System.out.println("Observable:" + o);
        System.out.println("arg:" + arg);

    }

}

class ObserverTwo implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("I am ObserverTwo");
        System.out.println("Observable:" + o);
        System.out.println("arg:" + arg);

    }

}
