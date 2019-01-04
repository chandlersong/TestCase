package me.study.java.guava.collections;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ForwardingExamples {

    @Test
    public void helloWorld() {
        MyList<String> list = new MyList<>();

        list.add("abc");
        list.add(1, "print log");
    }
}


class MyList<E> extends ForwardingList<E> {

    private static final Logger logger = getLogger(MyList.class);
    private ArrayList<E> delegate = Lists.newArrayList();

    @Override
    protected List<E> delegate() {
        return delegate;
    }


    @Override
    public void add(int index, E element) {
        logger.info("my list--add,index {}, element {}", index, element);
        super.add(index, element);
    }
}
