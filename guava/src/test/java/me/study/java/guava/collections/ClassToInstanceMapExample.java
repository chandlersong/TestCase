package me.study.java.guava.collections;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.junit.Assert;
import org.junit.Test;

public class ClassToInstanceMapExample {


    @Test
    public void testHowToCreate() {
        ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, 0);

        Assert.assertEquals(0, numberDefaults.get(Integer.class));
        Assert.assertEquals(null, numberDefaults.get(Double.class));
    }
}
