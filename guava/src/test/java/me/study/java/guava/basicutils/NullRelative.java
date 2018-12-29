package me.study.java.guava.basicutils;

import com.google.common.base.MoreObjects;
import org.junit.Assert;
import org.junit.Test;

public class NullRelative {

    @Test(expected = NullPointerException.class)
    public void someStaticMethods() {
        Assert.assertEquals("abc", MoreObjects.firstNonNull(null, "abc"));
        MoreObjects.firstNonNull(null, null); //it will throw NullPointerException
    }
}
