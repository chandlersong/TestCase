
package com.hilatest.common;

import org.junit.Assert;
import org.junit.Test;

public class RegTest {

    @Test
    public void testMatchAllChar() {
        String regExp = ".*";

        Assert.assertTrue("basbfsabf".matches(regExp));
        Assert.assertTrue("http://sfbfsabfsb:12431243".matches(regExp));
    }
}
