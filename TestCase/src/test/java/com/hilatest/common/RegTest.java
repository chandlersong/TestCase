
package com.hilatest.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class RegTest {

    @Test
    public void testMatchAllChar() {
        String regExp = ".*";

        Assert.assertTrue("basbfsabf".matches(regExp));
        Assert.assertTrue("http://sfbfsabfsb:12431243".matches(regExp));
    }

    @Test
    public void testGroup() {
        String line = "This order was placed for QT3000! OK?";
        Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println("group 1: " + matcher.group(1));
            System.out.println("group 2: " + matcher.group(2));
            System.out.println("group 3: " + matcher.group(3));
        }
    }
}
