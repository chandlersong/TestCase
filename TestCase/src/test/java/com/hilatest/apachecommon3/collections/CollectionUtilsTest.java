
package com.hilatest.apachecommon3.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

public class CollectionUtilsTest {

    @Test
    public void testDisjunction() {

        List<String> s1 = new LinkedList<String>();

        s1.add("c");
        s1.add("f");

        List<String> s2 = new LinkedList<String>();
        s2.add("a");
        s2.add("b");
        s2.add("c");
        s2.add("d");
        s2.add("e");
        s2.add("f");
        s2.add("g");
        s2.add("h");

        @SuppressWarnings("unchecked")
        List<String> s3 = (List<String>)CollectionUtils.disjunction(s1, s2);

        Iterator<String> iter = s3.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
